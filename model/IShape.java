package model;

import java.awt.*;
import java.util.Map;

/**
 * The interface Shape.
 */
public interface IShape {

  /**
   * Sets location.
   *
   * @param location the location
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setLocation(Point2D location) throws IllegalArgumentException;

  /**
   * Sets name.
   *
   * @param name the name
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setName(String name) throws IllegalArgumentException;

  /**
   * Sets shape type.
   *
   * @param shapeType the shape type
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setShapeType(String shapeType) throws IllegalArgumentException;

  /**
   * Sets color.
   *
   * @param color the color
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setColor(CustomColor color) throws IllegalArgumentException;

  /**
   * Sets width.
   *
   * @param width the width
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setWidth(double width) throws IllegalArgumentException;

  /**
   * Sets height.
   *
   * @param height the height
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setHeight(double height) throws IllegalArgumentException;

  /**
   * Sets property.
   *
   * @param key   the key
   * @param value the value
   * @throws IllegalArgumentException the illegal argument exception
   */
  void setProperty(String key, Object value) throws IllegalArgumentException;

  /**
   * Gets location.
   *
   * @return the location
   */
  Point2D getLocation();

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets shape type.
   *
   * @return the shape type
   */
  String getShapeType();

  /**
   * Gets width.
   *
   * @return the width
   */
  double getWidth();

  /**
   * Gets height.
   *
   * @return the height
   */
  double getHeight();

  /**
   * Gets color.
   *
   * @return the color
   */
  CustomColor getColor();

  /**
   * Gets property.
   *
   * @return the property
   */
  Map<String, Object> getProperty();

  String toString();

  void draw(Graphics g);

  String toSVG();
}
