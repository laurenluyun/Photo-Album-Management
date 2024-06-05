package model;

import java.awt.Color;

/**
 * The type Custom color.
 */
public class CustomColor extends Color {

  private float r;
  private float g;
  private float b;

  /**
   * Instantiates a new Custom color.
   *
   * @param r the r
   * @param g the g
   * @param b the b
   */
  public CustomColor(float r, float g, float b) throws IllegalArgumentException {
    super(r, g, b);
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public float getR() {
    return r;
  }

  public float getG() {
    return g;
  }

  public float getB() {
    return b;
  }
  @Override
  public String toString() {
    // getRed(), getGreen(), and getBlue() methods in the Color class return integer values
    // in the range of 0 to 255
    return String.format("(%.1f,%.1f,%.1f)", getRed() / 255.0, getGreen() / 255.0,
            getBlue() / 255.0);
  }
}
