package model;

/**
 * The Class Patient.
 * Contain patient name and sensor device period
 */
class Patient {
  
  /** The name. */
  private String name;
  
  /** The period of sensor device. */
  private int period;
  
  /**
   * Instantiates a new patient.
   *
   * @param name the name
   * @param period the period
   */
  public Patient(String name, int period) {
    this.name = name;
    this. period = period;
  }
  
  /**
   * Name of patient.
   *
   * @return the string
   */
  public String name() {
    return this.name;    
  }
  
  /**
   * Period for sensor device of patient.
   *
   * @return the int
   */
  public int period() {
    return this.period;
  }
}
