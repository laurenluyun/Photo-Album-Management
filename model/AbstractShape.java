package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  private Point2D location;
  private String name;
  private String shapeType;
  private CustomColor color;
  private double width;
  private double height;
  private Map<String, Object> properties;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param location  the location
   * @param name      the name
   * @param shapeType the shape type
   * @param color     the color
   * @param width         the x
   * @param height         the y
   */
  public AbstractShape(Point2D location, String name, String shapeType, CustomColor color,
                       double width, double height) throws IllegalArgumentException {
    if (location == null) {
      throw new IllegalArgumentException("Invalid shape location.");
    } else if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape name.");
    } else if (shapeType == null || shapeType.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape type.");
    } else if (color == null) {
      throw new IllegalArgumentException("Invalid shape color.");
    }
    try {
      this.location = location;
      this.name = name;
      this.shapeType = shapeType;
      this.color = color;
      this.width = width;
      this.height = height;
      this.properties = new HashMap<>();

      this.setProperty("width", width);
      this.setProperty("height", height);
      this.setProperty("color", color);
      this.setProperty("location", location);

    } catch (Exception e) {
      // catch potential IllegalArgumentException error from CustomColor
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public void setLocation(Point2D location) throws IllegalArgumentException {
    if (location == null || location.getClass() != Point2D.class) {
      throw new IllegalArgumentException("Invalid location provided.");
    }
    this.location = location;
  }

  @Override
  public void setName(String name) throws IllegalArgumentException {
    if ((name == null || name.isEmpty())) {
      throw new IllegalArgumentException("Invalid shape name.");
    }
    this.name = name;
  }

  @Override
  public void setShapeType(String shapeType) throws IllegalArgumentException {
    if ((shapeType == null || shapeType.isEmpty())) {
      throw new IllegalArgumentException("Invalid type name.");
    }
    this.shapeType = shapeType;
  }

  @Override
  public void setColor(CustomColor color) throws IllegalArgumentException {
    if (color == null || color.getClass() != CustomColor.class) {
      throw new IllegalArgumentException("Invalid color provided.");
    }
    this.color = color;
  }

  @Override
  public void setWidth(double width) throws IllegalArgumentException {
    this.width = width;
  }

  @Override
  public void setHeight(double height) throws IllegalArgumentException {
    this.height = height;
  }

  @Override
  public void setProperty(String key, Object value) throws IllegalArgumentException {
    if (key == null || key.isEmpty()) {
      throw new IllegalArgumentException("Invalid shape property.");
    } else if (value == null) {
      throw new IllegalArgumentException("Invalid property value.");
    }
    try {
      switch (key.toLowerCase()) {
        case "location":
          this.setLocation((Point2D) value);
          break;
        case "color":
          this.setColor((CustomColor) value);
          break;
        case "width":
          this.setWidth((double) value);
          break;
        case "height":
          this.setHeight((double) value);
          break;
        default:
          // throw an exception for unknown keys
          throw new IllegalArgumentException("Unknown property key: " + key);
      }
      properties.put(key.toLowerCase(), value);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public CustomColor getColor() {
    return this.color;
  }
  @Override
  public Map<String, Object> getProperty() {
    return this.properties;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("name: %s\ntype: %s\n", this.getName(),
            this.getShapeType()));
    for (Map.Entry<String, Object> entry : properties.entrySet()) {
      stringBuilder.append(String.format("%s: %s, ", entry.getKey(), entry.getValue()));
    }
    // remove the ending "," if the string is not empty
    if (!this.properties.isEmpty()) {
      stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractShape that = (AbstractShape) o;
    return Double.compare(that.width, width) == 0 && Double.compare(that.height, height) == 0
            && Objects.equals(location, that.location) && Objects.equals(name, that.name)
            && Objects.equals(shapeType, that.shapeType)
            && color.toString().equals(that.color.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, name, shapeType, color, width, height);
  }


  @Override
  public void draw(Graphics g) {
    g.setColor(this.getColor());
    g.drawOval((int) this.getLocation().getX(), (int) this.getLocation().getY(),
              (int) this.getWidth(), (int) this.getHeight());
    g.setColor(this.getColor());
    g.fillOval((int) this.getLocation().getX(), (int) this.getLocation().getY(),
            (int) this.getWidth(), (int) this.getHeight());
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
