package controller;

import java.io.IOException;

import model.IPhotoAlbum;

/**
 * The interface Photo album controller.
 */
public interface IPhotoAlbumController {
  /**
   * Go.
   *
   * @param inputFile  the input file
   * @param outputFile the output file
   * @param viewType   the view type
   * @param viewWidth  the view width
   * @param viewHeight the view height
   * @throws IOException the io exception
   */
  void go(String inputFile, String outputFile, String viewType, int viewWidth, int viewHeight) throws IOException;

}
