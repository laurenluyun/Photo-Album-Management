package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.CustomColor;
import model.IShape;
import model.ISnapshot;
import model.Oval;
import model.Point2D;
import model.Rectangle;
import model.Snapshot;

public class Util {
  public static ISnapshot fromString(String snapshotString) {
    String[] lines = snapshotString.split("\n");

    // Extracting information from the string
    String snapshotID = lines[0].replace("Snapshot ID: ", "").trim();
    String timestampString = lines[1].replace("Timestamp: ", "").trim();
    String description = lines[2].replace("Description: ", "").trim();

    // Parsing the timestamp
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime timestamp = LocalDateTime.parse(timestampString, formatter);

    // Extracting shape information
    List<IShape> shapes = new ArrayList<>();
    int shapeStartIndex = 4; // Assuming shape information starts from line 4
    for (int i = shapeStartIndex; i < lines.length; i += 4) {
      String shapeInfo = lines[i] + ", " + lines[i + 1] + ", " + lines[i + 2];
      IShape shape = parseShape(shapeInfo); // Implement parseShape method
      shapes.add(shape);
    }
    // Creating the Snapshot object
    return new Snapshot(description, shapes, timestamp);
  }

  // Helper method to parse each shape from its string representation
  private static IShape parseShape(String shapeInfo) {
    String[] properties = shapeInfo.split(", ");

    String name = properties[0].replace("name: ", "").trim();
    String type = properties[1].replace("type: ", "").trim();
    // Extracting location
    String minCorner = properties[4].replace("location: ", "").trim();
    String[] coordinates = minCorner.substring(1, minCorner.length() - 1).split(",");
    double x = Double.parseDouble(coordinates[0].trim());
    double y = Double.parseDouble(coordinates[1].trim());
    Point2D location = new Point2D(x, y);

    // Extracting color
    String colorString = properties[2].replace("color: (", "").replace(")", "").trim();
    String[] colorComponents = colorString.split(",");
    double red = Double.parseDouble(colorComponents[0].trim());
    double green = Double.parseDouble(colorComponents[1].trim());
    double blue = Double.parseDouble(colorComponents[2].trim());
    CustomColor color = new CustomColor((float) red, (float) green, (float) blue);

    // Extracting width and height
    double width = Double.parseDouble(properties[3].replace("width: ", "").trim());
    double height = Double.parseDouble(properties[5].replace("height: ", "").trim());

    // Create and return the appropriate shape based on the type
    return createShape(location, name, type, color, width, height);
  }


  // Helper method to create new shape
  public static IShape createShape(Point2D location, String name, String shapeType,
      CustomColor color, double width, double height) throws IllegalArgumentException {
    try {
      switch (shapeType.toLowerCase()) {
        case "rectangle":
          return new Rectangle(location, name, shapeType.toLowerCase(), color, width, height);
        case "oval":
        case "circle":
          return new Oval(location, name, shapeType.toLowerCase(), color, width, height);
        default:
          throw new IllegalArgumentException("Invalid shapeType: " + shapeType);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static void main(String [] args) {
    String info = "Snapshot ID: 2022-04-10T11:08:30\n"
            + "Timestamp: 10-04-2022 11:08:30\nDescription: First selfie\nShape Information:\n"
            + "Name: R\nType: rectangle\nLocation: (100.0,300.0), Color: (0.0,1.0,0.0), "
            + "Width: 100.0, Height: 25.0\n\nName: C\nType: circle\nLocation: (50.0,50.0), "
            + "Color: (1.0,0.0,0.0), Width: 25.0, Height: 25.0\n\nName: O\nType: oval\n"
            + "Location: (500.0,100.0), Color: (0.0,0.0,1.0), Width: 60.0, Height: 30.0\n\n";
    System.out.println(fromString(info).toString());
  }
}
