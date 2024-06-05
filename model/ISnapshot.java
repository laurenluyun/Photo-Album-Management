package model;

import java.util.List;

public interface ISnapshot {
  /**
   * Gets snapshot id.
   *
   * @return the snapshot id
   */
  String getSnapshotID();

  /**
   * Gets description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets time stamp.
   *
   * @return the time stamp
   */
  String getTimeStamp();

  List<IShape> getShapes();

  String toString();
}
