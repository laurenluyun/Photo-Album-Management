package model;

import java.awt.*;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {
  /**
   * Instantiates a new Rectangle.
   *
   * @param location  the location
   * @param name      the name
   * @param shapeType the shape type
   * @param color     the color
   * @param width     the width
   * @param height    the height
   */
  public Rectangle(Point2D location, String name, String shapeType, CustomColor color,
                   double width, double height) {
    super(location, name, shapeType, color, width, height);
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(this.getColor());
    g.drawRect((int) this.getLocation().getX(), (int) this.getLocation().getY(),
            (int) this.getWidth(), (int) this.getHeight());
    g.setColor(this.getColor());
    g.fillRect((int) this.getLocation().getX(), (int) this.getLocation().getY(),
            (int) this.getWidth(), (int) this.getHeight());
  }

  @Override
  public String toSVG() {
    return String.format("<rect id=\"%s\" x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" fill=\"%s\">\n        </rect>\n",
            this.getName(), this.getLocation().getX(), this.getLocation().getY(), this.getWidth(), this.getHeight(), toHexColor(this.getColor()));
  }

  private String toHexColor(CustomColor color) {
    return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
  }
}
