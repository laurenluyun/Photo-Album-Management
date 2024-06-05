package view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.IPhotoAlbum;
import model.IShape;
import model.ISnapshot;
import utility.Util;

/**
 * The type Web view.
 */
public class WebView implements IPhotoAlbumView {
  private String outputFile;

  /**
   * Instantiates a new Web view.
   *
   * @param photoAlbum the photo album
   * @param outputFile the output file
   */
  public WebView(IPhotoAlbum photoAlbum, String outputFile) {
    this.outputFile = outputFile;
    // Generate HTML + SVG markup
    String htmlContent = generateHTML(photoAlbum);
    // Write to a file
    writeToFile(htmlContent, this.outputFile);
  }

  @Override
  public void setUpKeyBindings() {}

  @Override
  public void enableNextButton(boolean enable) {}

  @Override
  public void enablePreviousButton(boolean enable){}

  @Override
  public void enableSelectButton(boolean enable) {}

  @Override
  public void display() {}

  private static String generateHTML(IPhotoAlbum photoAlbum) {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>\n");
    htmlBuilder.append("<html>\n");
    htmlBuilder.append("<head>\n");
    htmlBuilder.append("<style>\n");
    htmlBuilder.append("    .snapshot {\n");
    htmlBuilder.append("        border: 5px outset red;\n");
    htmlBuilder.append("        background-color: lightblue;\n");
    htmlBuilder.append("        margin-bottom: 20px;\n");
    htmlBuilder.append("    }\n");
    htmlBuilder.append("</style>\n");
    htmlBuilder.append("</head>\n");
    htmlBuilder.append("<body>\n");
    htmlBuilder.append("<h1>Web View for Photo Album with SVG</h1>\n");
    // Retrieve snapshots from the photo album
    for (String snapshotID : photoAlbum.getAllSnapshotsIDList()) {
      String snapshot = photoAlbum.getSnapshotsMap().get(snapshotID);
      ISnapshot currentSnapshot = Util.fromString(snapshot);
      htmlBuilder.append("<div class=\"snapshot\">\n");
      htmlBuilder.append("    <h2 style=\"font-size: 24px; font-weight: bold;\">Timestamp: ").append(currentSnapshot.getSnapshotID()).append("</h2>\n");
      htmlBuilder.append("    <h2 style=\"font-size: 20px; font-weight: normal;\">").append(currentSnapshot.getDescription()).append("</h2>\n");
      htmlBuilder.append("    <svg width=\"1000\" height=\"1000\">\n");

      // Iterate over shapes in the snapshot and generate SVG markup
      for (IShape shape : currentSnapshot.getShapes()) {
        htmlBuilder.append("        ").append(shape.toSVG());
      }
      htmlBuilder.append("    </svg>\n");
      htmlBuilder.append("</div>\n");
    }

    htmlBuilder.append("</body>\n");
    htmlBuilder.append("</html>");

    return htmlBuilder.toString();
  }

  private static void writeToFile(String content, String outputFile) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      writer.write(content);
      System.out.println("HTML file generated successfully: " + outputFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
