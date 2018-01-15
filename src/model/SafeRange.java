package model;

/**
 * The Class SafeRange.
 * Contain safe range of device.
 */
class SafeRange {
  
  /** The lower bound. */
  private int lowerBound;
  
  /** The upper bound. */
  private int upperBound;
  
  /**
   * Instantiates a new safe range.
   *
   * @param lowerBound the lower bound
   * @param upperBound the upper bound
   */
  public SafeRange(int lowerBound, int upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }
  
  /**
   * Lower bound accessor methods.
   *
   * @return the int
   */
  public int lowerBound() {
    return this.lowerBound;
  }
  
  /**
   * Upper bound accessor methods.
   *
   * @return the int
   */
  public int upperBound() {
    return this.upperBound;
  }
  
  
  
}
