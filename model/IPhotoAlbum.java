package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The interface Photo album.
 */
public interface IPhotoAlbum {

  /**
   * Create shape.
   *
   * @param location  the location
   * @param name      the name
   * @param shapeType the shape type
   * @param color     the color
   * @param x         the x
   * @param y         the y
   */
  void createShape(Point2D location, String name, String shapeType, CustomColor color,
                   double x, double y);

  /**
   * Remove shape.
   *
   * @param shapeName the shape name
   * @throws IllegalArgumentException the illegal argument exception
   */
  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Gets all shapes.
   *
   * @return the all shapes
   */
  List<IShape> getAllShapes();

  /**
   * Change name.
   *
   * @param shapeName the shape name
   * @param name      the name
   * @throws IllegalArgumentException the illegal argument exception
   */
  void changeName(String shapeName, String name) throws IllegalArgumentException;

  /**
   * Change shape type.
   *
   * @param shapeName the shape name
   * @param shapeType the shape type
   * @throws IllegalArgumentException the illegal argument exception
   */
  void changeShapeType(String shapeName, String shapeType) throws IllegalArgumentException;

  /**
   * Change properties.
   *
   * @param shapeName the shape name
   * @param key       the key
   * @param value     the value
   * @throws IllegalArgumentException the illegal argument exception
   */
  void changeProperties(String shapeName, String key, Object value) throws IllegalArgumentException;

  /**
   * Take snapshot.
   *
   * @param description the description
   * @param time        the time
   * @throws IllegalArgumentException the illegal argument exception
   */
  void takeSnapshot(String description, LocalDateTime time) throws IllegalArgumentException;

  /**
   * Gets all snapshots id list.
   *
   * @return the all snapshots id list
   */
  List<String> getAllSnapshotsIDList();

  /**
   * Reset snapshots.
   */
  void resetSnapshots();

  /**
   * Gets snapshots map.
   *
   * @return the snapshots map
   */
  Map<String, String> getSnapshotsMap();

  /**
   * Print snapshots string.
   *
   * @return the string
   */
  String printSnapshots();
}
