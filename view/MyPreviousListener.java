package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type My previous listener.
 */
public class MyPreviousListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      GraphicalView.drawPanel.previousSnapshot();
    } catch (Exception exception) {
      GraphicalView.showMessage("First page of the photo album. No snapshot to show before this one.");
    }
  }
}
