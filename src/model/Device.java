package model;

/**
 * The Class Device.
 * Contain device category, name, safe range.
 */
class Device {
  
  /** The category. */
  private String category;
  
  /** The name. */
  private String name;
  
  /** The safe range. */
  private SafeRange safeRange;
  
  /**
   * Instantiates a new device.
   *
   * @param category the category
   * @param name the name
   * @param safeRangeLowerBound the safe range lower bound
   * @param safeRangeUpperBound the safe range upper bound
   */
  public Device(String category, String name, int safeRangeLowerBound, int safeRangeUpperBound) {
    this.category = category;
    this.name = name;
    safeRange = new SafeRange(safeRangeLowerBound, safeRangeUpperBound);
  }
  
  /**
   * Category accessor methods.
   *
   * @return the string
   */
  public String category() {
    return this.category;
  }
  
  /**
   * Name accessor methods.
   *
   * @return the string
   */
  public String name() {
    return this.name;
  }
  
  /**
   * Safe range accessor methods.
   *
   * @return the safe range
   */
  public SafeRange safeRange() {
    return this.safeRange;
  }
}
