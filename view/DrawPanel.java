package view;

import java.awt.*;
import javax.swing.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IPhotoAlbum;
import model.IShape;
import model.ISnapshot;
import utility.Util;

/**
 * The type Draw panel.
 */
public class DrawPanel extends JPanel {

  private IPhotoAlbum photoAlbum;
  private Map<String, String> snapshotsMap;
  private List<String> snapshotIDList;
  private List<IShape> shapes;

  private int index;

  private String currentSnapshotID;

  /**
   * Instantiates a new Draw panel.
   *
   * @param photoAlbum the photo album
   */
  public DrawPanel(IPhotoAlbum photoAlbum) {
    // Set the preferred size of the draw panel
    setPreferredSize(new Dimension(GraphicalView.WIDTH,
            GraphicalView.HEIGHT - 2 * GraphicalView.HEIGHT_SMALL));
    this.photoAlbum = photoAlbum;
    this.snapshotsMap = photoAlbum.getSnapshotsMap();
    this.snapshotIDList = photoAlbum.getAllSnapshotsIDList();
    this.shapes = photoAlbum.getAllShapes();
    this.index = 0;
    this.currentSnapshotID = snapshotIDList.get(index);
  }

  /**
   * Sets current snapshot id by index.
   *
   * @param index the index
   */
  public void setCurrentSnapshotIDByIndex(int index) {
    this.currentSnapshotID = this.snapshotIDList.get(index);
  }

  /**
   * Sets current snapshot id by id.
   *
   * @param snapshotID the snapshot id
   */
  public void setCurrentSnapshotIDByID(String snapshotID) {
    this.currentSnapshotID = snapshotID;
  }

  /**
   * Next snapshot.
   *
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  public void nextSnapshot() throws IndexOutOfBoundsException {
    if (index < snapshotIDList.size() - 1) {
      index++;
      setCurrentSnapshotIDByIndex(index);
      repaint();
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Previous snapshot.
   *
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  public void previousSnapshot() throws IndexOutOfBoundsException {
    if (index > 0) {
      index--;
      setCurrentSnapshotIDByIndex(index);
      repaint();
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Select snapshot.
   *
   * @param snapshotID the snapshot id
   */
  public void selectSnapshot(String snapshotID) {
    setCurrentSnapshotIDByID(snapshotID);
    repaint();
  }

  /**
   * paint component.
   *
   * @param g the snapshot graphics g
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.translate(0, 0);
    setBackground(Color.BLUE);
    if (currentSnapshotID != null && snapshotsMap.containsKey(currentSnapshotID)) {
      String snapshotString = snapshotsMap.get(currentSnapshotID);
      ISnapshot currentSnapshot = Util.fromString(snapshotString);
      g.setFont(new Font("Arial", Font.BOLD, 14));
      g.setColor(Color.WHITE);
      // Set font and color for text
      // Draw description
      g.drawString(currentSnapshot.getDescription(), 5, HEIGHT - 20);
      // Draw timestamp
      g.drawString(currentSnapshot.getSnapshotID(), 5, HEIGHT - 25);
      for (IShape shape : currentSnapshot.getShapes()) {
        shape.draw(g);
      }
    }
  }
}


