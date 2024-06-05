package utility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import model.CustomColor;
import model.IPhotoAlbum;
import model.Point2D;

/**
 * The type Command file reader.
 */
public class CommandFileReader {

  private IPhotoAlbum model;

  /**
   * Instantiates a new Command file reader.
   *
   * @param model the model
   */
  public CommandFileReader(IPhotoAlbum model) {
    this.model = model;
  }

  /**
   * Load commands.
   *
   * @param inputFile the input file
   */
  public void loadCommands(String inputFile) {
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Ignore comments (lines starting with #)
        if (!line.trim().startsWith("#")) {
          executeCommand(line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void executeCommand(String command) {
    // split("\\s+") method is used to split the string into an array of substrings using whitespace characters as delimiters. In regular expressions, \\s represents any whitespace character (e.g., space, tab, newline).
    String[] tokens = command.trim().split("\\s+");
    if (tokens.length > 0) {
      String commandType = tokens[0].toLowerCase();
      switch (commandType) {
        case "shape":
          executeShapeCommand(tokens);
          break;
        case "move":
          executeMoveCommand(tokens);
          break;
        case "color":
          executeColorCommand(tokens);
          break;
        case "resize":
          executeResizeCommand(tokens);
          break;
        case "remove":
          executeRemoveCommand(tokens);
          break;
        case "snapshot":
          executeSnapshotCommand(tokens);
          break;
        default:
          System.out.println("Unknown command type: " + commandType);
      }
    }
  }

  private void executeShapeCommand(String[] tokens) {
    // Extract relevant information from the tokens and call model.createShape()
    if (tokens.length == 10) {
      String name = tokens[1];
      String type = tokens[2];
      double x = Double.parseDouble(tokens[3]);
      double y = Double.parseDouble(tokens[4]);
      double width = Double.parseDouble(tokens[5]);
      double height = Double.parseDouble(tokens[6]);
      int red = Integer.parseInt(tokens[7]);
      int green = Integer.parseInt(tokens[8]);
      int blue = Integer.parseInt(tokens[9]);

      // Assuming model.createShape() takes these parameters
      model.createShape(new Point2D(x, y), name, type, new CustomColor(red / 255.0f,
              green / 255.0f, blue / 255.0f), width, height);
    } else {
      System.out.println("Invalid shape command: " + String.join(" ", tokens));
    }
  }

  private void executeMoveCommand(String[] tokens) {
    if (tokens.length == 4) {
      String name = tokens[1];
      double newX = Double.parseDouble(tokens[2]);
      double newY = Double.parseDouble(tokens[3]);

      // Assuming model.moveShape() takes these parameters
      model.changeProperties(name, "location", new Point2D(newX, newY));
    } else {
      System.out.println("Invalid move command: " + String.join(" ", tokens));
    }
  }

  private void executeColorCommand(String[] tokens) {
    if (tokens.length == 5) {
      String name = tokens[1];
      int red = Integer.parseInt(tokens[2]);
      int green = Integer.parseInt(tokens[3]);
      int blue = Integer.parseInt(tokens[4]);

      // Assuming model.changeShapeColor() takes these parameters
      model.changeProperties(name, "color", new CustomColor(red / 255.0f, green / 255.0f, blue / 255.0f));
    } else {
      System.out.println("Invalid color command: " + String.join(" ", tokens));
    }
  }

  private void executeResizeCommand(String[] tokens) {
    if (tokens.length == 4) {
      String name = tokens[1];
      double newWidth = Double.parseDouble(tokens[2]);
      double newHeight = Double.parseDouble(tokens[3]);

      // Assuming model.resizeShape() takes these parameters
      model.changeProperties(name, "width", newWidth);
      model.changeProperties(name, "height", newHeight);
    } else {
      System.out.println("Invalid resize command: " + String.join(" ", tokens));
    }
  }

  private void executeRemoveCommand(String[] tokens) {
    if (tokens.length == 2) {
      String name = tokens[1];
      // Assuming model.removeShape() takes this parameter
      model.removeShape(name);
    } else {
      System.out.println("Invalid remove command: " + String.join(" ", tokens));
    }
  }

  private void executeSnapshotCommand(String[] tokens) {
    String description = tokens.length > 1 ? String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)) : "";
    // Assuming model.takeSnapshot() takes a description parameter
    LocalDateTime timestamp = LocalDateTime.now();
    model.takeSnapshot(description, timestamp);
  }
}

