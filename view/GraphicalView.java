package view;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;

import model.IPhotoAlbum;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame implements IPhotoAlbumView {
  private JButton prevButton;
  private JButton selButton;
  private JButton nextButton;
  private JButton quitButton;
  /**
   * The constant drawPanel.
   */
  protected static DrawPanel drawPanel;
  /**
   * The constant photoAlbum.
   */
  protected static IPhotoAlbum photoAlbum;
  /**
   * The constant WIDTH.
   */
  protected static int WIDTH;
  /**
   * The constant HEIGHT.
   */
  protected static int HEIGHT;
  /**
   * The constant HEIGHT_SMALL.
   */
  protected static int HEIGHT_SMALL;

  /**
   * Instantiates a new Graphical view.
   *
   * @param photoAlbum the photo album
   * @param width      the width
   * @param height     the height
   */
  public GraphicalView(IPhotoAlbum photoAlbum, Integer width, Integer height) {
    super();
    WIDTH = width;
    HEIGHT = height;
    HEIGHT_SMALL = 20;
    // set up frame properties
    super.setSize(WIDTH, HEIGHT);
    setTitle("Shapes Photo Album Viewer");
    // ignore the x button in window
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.photoAlbum = photoAlbum;
    // initialize Swing components
    prevButton = new JButton(" << Prev << ");
    selButton = new JButton(" ^^ Select ^^ ");
    nextButton = new JButton(" >> Next >> ");
    quitButton = new JButton(" xx Quit xx ");
    // add drawing
    drawPanel = new DrawPanel(this.photoAlbum);
    add(drawPanel);

    // make a button JPanel
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(prevButton);
    buttonPanel.add(selButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(quitButton);
    buttonPanel.setBackground(Color.ORANGE);
    buttonPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_SMALL));
    add(buttonPanel, BorderLayout.SOUTH);

    //set up listener for buttons
    prevButton.addActionListener(new MyPreviousListener());
    selButton.addActionListener(new MySelectListener());
    nextButton.addActionListener(new MyNextListener());
    quitButton.addActionListener(new MyQuitListener());

    // Set up key bindings
    setUpKeyBindings();

    pack();
  }

  @Override
  public void setUpKeyBindings() {
    // Set up key bindings for the buttons
    InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = getRootPane().getActionMap();

    // Key binding for the "Previous" button
    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "previous");
    actionMap.put("previous", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        prevButton.doClick();
      }
    });

    // Key binding for the "Next" button
    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "next");
    actionMap.put("next", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nextButton.doClick();
      }
    });

    // Key binding for the "Select" button
    inputMap.put(KeyStroke.getKeyStroke("UP"), "select");
    actionMap.put("select", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selButton.doClick();
      }
    });

    // Key binding for the "Quit" button
    inputMap.put(KeyStroke.getKeyStroke("X"), "quit");
    actionMap.put("quit", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        quitButton.doClick();
      }
    });
  }

  /**
   * Show message.
   *
   * @param message the message
   */
  protected static void showMessage(String message) {
    // display messages to the user
    JOptionPane.showMessageDialog(drawPanel, message);
  }

  @Override
  public void enableNextButton(boolean enable) {
    // enable or disable the "Next" button
    nextButton.setEnabled(enable);
  }

  @Override
  public void enablePreviousButton(boolean enable) {
    // enable or disable the "Previous" button
    prevButton.setEnabled(enable);
  }

  @Override
  public void enableSelectButton(boolean enable) {
    // enable or disable the "Select" button
    selButton.setEnabled(enable);
  }

  @Override
  public void display() {
    this.setVisible(true);
  }
}
