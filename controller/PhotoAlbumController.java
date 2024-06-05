package controller;

import model.IPhotoAlbum;
import model.PhotoAlbum;
import utility.CommandFileReader;
import view.GraphicalView;
import view.IPhotoAlbumView;
import view.WebView;

/**
 * The type Photo album controller.
 */
public class PhotoAlbumController implements IPhotoAlbumController {

  private IPhotoAlbum model;
  private IPhotoAlbumView view;
  private String viewType;

  @Override
  public void go(String inputFile, String outputFile, String viewType, int viewWidth, int viewHeight) {
    // Create the photo album model
    model = new PhotoAlbum();
    this.viewType = viewType;
    // Set up the viewer
    setUpViewer(inputFile, outputFile, viewWidth, viewHeight);
  }

  /**
   * Set up viewer
   *
   * @param inputFile, outputFile, viewWidth, viewHeight
   */
  private void setUpViewer(String inputFile, String outputFile, int viewWidth, int viewHeight) {
    // Load commands from the input file
    CommandFileReader reader = new CommandFileReader(this.model);
    reader.loadCommands(inputFile);
    // Set view-specific parameters (output file, view dimensions)
    if (this.viewType.equals("graphical")) {
      view = new GraphicalView(model, viewWidth, viewHeight);
      view.display();
    } else if (this.viewType.equals("web")) {
      view = new WebView(model, outputFile);
    } else {
      System.out.println("Invalid view type. Supported types: graphical, web");
      System.exit(1);
    }
  }
}
