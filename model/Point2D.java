package model;

import java.util.Objects;

/**
 * The type Point 2 d.
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Instantiates a new Point 2 d.
   *
   * @param x the x
   * @param y the y
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.getX(), this.getY());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point2D point2D = (Point2D) o;
    return Double.compare(point2D.x, x) == 0 && Double.compare(point2D.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
