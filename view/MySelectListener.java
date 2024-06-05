package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

/**
 * The type My select listener.
 */
public class MySelectListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    // Implement the logic to show a pop-up window/menu with snapshot IDs
    // and handle the user's selection
    showSnapshotSelectionMenu();
  }

  private void showSnapshotSelectionMenu() {
    // Retrieve the snapshot IDs
    List<String> snapshotIDs = GraphicalView.photoAlbum.getAllSnapshotsIDList();

    // Create an array of options for the menu
    String[] options = snapshotIDs.toArray(new String[0]);

    // Display the pop-up menu
    String selectedSnapshotID = (String) JOptionPane.showInputDialog(
            GraphicalView.drawPanel,
            "Select a Snapshot",
            "Snapshot Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    // Check if a snapshot ID was selected
    if (selectedSnapshotID != null) {
      // Update canvas based on the selected snapshot
      GraphicalView.drawPanel.selectSnapshot(selectedSnapshotID);
    }
  }
}
