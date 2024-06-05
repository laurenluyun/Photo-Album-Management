package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type My quit listener.
 */
public class MyQuitListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
