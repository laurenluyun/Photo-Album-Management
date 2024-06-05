package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.Util;

/**
 * The type Photo album.
 */
public class PhotoAlbum implements IPhotoAlbum {
  private List<IShape> shapes;
  private Map<String, String> snapshots;

  private List<String> snapshotIDList;

  /**
   * Instantiates a new Photo album.
   */
  public PhotoAlbum() {
    this.shapes = new ArrayList<>();
    this.snapshotIDList = new ArrayList<>();
    this.snapshots = new HashMap<>();
  }

  @Override
  public void createShape(Point2D location, String name, String shapeType, CustomColor color,
                          double width, double height) throws IllegalArgumentException {
    IShape shape = Util.createShape(location, name, shapeType, color, width, height);
    this.shapes.add(shape);
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (shapeName == null || shapeName.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape name.");
    }
    this.shapes.removeIf(shape -> shape.getName().equals(shapeName));
  }

  @Override
  public List<IShape> getAllShapes() {
    return this.shapes;
  }

  @Override
  public void changeName(String shapeName, String name) throws IllegalArgumentException {
    if (shapeName == null || shapeName.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape name.");
    }
    try {
      for (IShape shape : this.shapes) {
        if (shape.getName().equals(shapeName)) {
          shape.setName(name);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public void changeShapeType(String shapeName, String shapeType) throws IllegalArgumentException {
    if (shapeName == null || shapeName.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape name.");
    }
    try {
      for (IShape shape : this.shapes) {
        if (shape.getName().equals(shapeName)) {
          shape.setShapeType(shapeType);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
  @Override
  public void changeProperties(String shapeName, String key, Object value)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape name.");
    }
    try {
      for (IShape shape : this.shapes) {
        if (shape.getName().equals(shapeName)) {
          shape.setProperty(key, value);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


  @Override
  public void takeSnapshot(String description, LocalDateTime time) throws IllegalArgumentException {
    if (description == null || time == null) {
      throw new IllegalArgumentException("Invalid description.");
    }
    try {
      Snapshot snapshot = new Snapshot(description, this.shapes, time);
      this.snapshots.put(snapshot.getSnapshotID(), snapshot.toString());
      this.snapshotIDList.add(snapshot.getSnapshotID());
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public List<String> getAllSnapshotsIDList() {
    return this.snapshotIDList;
  }

  @Override
  public Map<String, String> getSnapshotsMap() {
    return this.snapshots;
  }

  @Override
  public void resetSnapshots() {
    this.snapshots.clear();
  }

  @Override
  public String printSnapshots() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Printing Snapshots\n");
    List<String> snapshotsList = new ArrayList<>(this.snapshots.values());
    Collections.reverse(snapshotsList);
    for (String snapshot : snapshotsList) {
      stringBuilder.append(snapshot.toString());
    }
    return stringBuilder.toString();
  }
}
