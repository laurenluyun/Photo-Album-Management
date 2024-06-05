package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type My next listener.
 */
public class MyNextListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      GraphicalView.drawPanel.nextSnapshot();
    } catch (Exception exception) {
      GraphicalView.showMessage("End of the photo album. No snapshot to show beyond this one.");
    }
  }

}
