package model;

import java.awt.*;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {

  /**
   * Instantiates a new Oval.
   *
   * @param location  the location
   * @param name      the name
   * @param shapeType the shape type
   * @param color     the color
   * @param width   the radius width
   * @param height   the radius height
   */
  public Oval(Point2D location, String name, String shapeType, CustomColor color, double width,
              double height) {
    super(location, name, shapeType, color, width, height);
  }

  @Override
  public String toSVG() {
    return String.format("<ellipse id=\"%s\" cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"%s\">\n        </ellipse>\n",
            this.getName(), this.getLocation().getX(), this.getLocation().getY(), this.getWidth(), this.getHeight(), toHexColor(this.getColor()));
  }

  private String toHexColor(CustomColor color) {
    return String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
  }
}

