package model;

/**
 * The Class SafeRange. Contain safe range of device.
 */
class SafeRange {

  /** The lower bound. */
  private double lowerBound;

  /** The upper bound. */
  private double upperBound;

  /**
   * Instantiates a new safe range.
   *
   * @param lowerBound the lower bound
   * @param upperBound the upper bound
   */
  public SafeRange(double lowerBound, double upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * Lower bound accessor methods.
   *
   * @return the int
   */
  public double lowerBound() {
    return this.lowerBound;
  }

  /**
   * Upper bound accessor methods.
   *
   * @return the int
   */
  public double upperBound() {
    return this.upperBound;
  }



}
