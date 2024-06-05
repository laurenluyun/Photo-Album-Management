package view;

/**
 * The interface Photo album viewer.
 */
public interface IPhotoAlbumView {

  void setUpKeyBindings();

  /**
   * Enable next button.
   *
   * @param enable the enable
   */
  void enableNextButton(boolean enable);

  /**
   * Enable previous button.
   *
   * @param enable the enable
   */
  void enablePreviousButton(boolean enable);

  /**
   * Enable select button.
   *
   * @param enable the enable
   */
  void enableSelectButton(boolean enable);

  void display();
}
