package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * The type Snapshot.
 */
public class Snapshot implements ISnapshot {
  private String snapshotID;
  private LocalDateTime timestamp;
  private String description;
  private List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description
   * @param shapes      the shapes
   * @param time        the time
   */
  public Snapshot(String description, List<IShape> shapes, LocalDateTime time) {
    this.description = description;
    this.shapes = shapes;
    this.timestamp = time;
    this.snapshotID = time.toString();
  }

  /**
   * Gets snapshot id.
   *
   * @return the snapshot id
   */
  public String getSnapshotID() {
    return this.snapshotID;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets time stamp.
   *
   * @return the time stamp
   */
  public String getTimeStamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return this.timestamp.format(formatter);
  }

  public List<IShape> getShapes() {
    return Collections.unmodifiableList(this.shapes);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Snapshot ID: %s\n", this.getSnapshotID()));
    stringBuilder.append(String.format("Timestamp: %s\n", this.getTimeStamp()));
    stringBuilder.append(String.format("Description: %s\nShape Information:\n",
            this.getDescription()));
    for (IShape shape : this.getShapes()) {
      stringBuilder.append(shape.toString()).append("\n\n");
    }
    return stringBuilder.toString();
  }
}
