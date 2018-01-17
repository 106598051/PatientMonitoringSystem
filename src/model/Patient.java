package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class Patient. Contain patient name and sensor device period
 */
class Patient {

  private final int id;

  /** The name. */
  private String name;

  /** The period of sensor device. */
  private int period;

  /** The device. */
  private ArrayList<Device> device;

  /**
   * Instantiates a new patient.
   *
   * @param name the patient name
   * @param period the patient's period to check devices
   */
  public Patient(String name, int period, int id) {
    this.id = id;
    this.name = name;
    this.period = period;
    this.device = new ArrayList<Device>();
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

  /**
   * Gets the device.
   *
   * @param index the index
   * @return the device
   */
  public Device getDevice(int index) {
    Device device = null;
    if (index < this.device.size()) {
      device = this.device.get(index);
    }
    return device;
  }

  // /**
  // * Gets the device.
  // *
  // * @deprecated
  // *
  // * @param name the name
  // * @return the device
  // */
  // public Device getDevice(String name) {
  // Device device = null;
  // for (int i = 0; i < this.device.size(); i++) {
  // if (this.device.get(i).name() == name) {
  // device = this.device.get(i);
  // }
  // }
  // return device;
  // }

  /**
   * Adds the device.
   *
   * @param category the category
   * @param name the name
   * @param datasetFilePath the dataset file path
   * @param safeRangeLowerBound the safe range lower bound
   * @param safeRangeUpperBound the safe range upper bound
   * @return true, if successful
   * @throws FileNotFoundException the file not found exception
   */
  public boolean addDevice(String category, String name, String datasetFilePath,
      double safeRangeLowerBound, double safeRangeUpperBound) throws FileNotFoundException {
    boolean result = false;
    Device newDevice =
        new Device(category, name, datasetFilePath, safeRangeLowerBound, safeRangeUpperBound);
    this.device.add(newDevice);
    result = true;
    return result;
  }

  /**
   * Check device. Would print out abnormal situations.
   *
   * @param timestamp the timestamp
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void checkDevice(int timestamp) throws IOException {
    int index = 0;
    int state = -1;
    for (int i = 0; i < this.device.size(); i++) {
      if ((timestamp % this.period) == 0) {
        index = this.device.get(i).readFactorValue(timestamp);
        state = this.device.get(i).checkRecordState(index);
        if (state == -1) {
          System.out.print("[" + timestamp + "]");
          System.out.println(this.device.get(i).name() + " falls");
        } else if (state == 1) {
          System.out.print("[" + timestamp + "]");
          System.out.println(this.name + " is in danger! Cause: " + this.device.get(i).name() + " "
              + this.device.get(i).dataset(index));
        }
      }
    }
  }

  /**
   * Disable all device.
   */
  public void disableAllDevice() {
    for (int i = 0; i < this.device.size(); i++) {
      this.device.get(i).disable();
    }
  }

  /**
   * Display factor database.
   */
  public void displayFactorDatabase() {
    System.out.println("patient " + this.name);
    for (int i = 0; i < this.device.size(); i++) {
      System.out.println(this.device.get(i).category() + " " + this.device.get(i).name());
      this.device.get(i).displayRecord();
    }
  }

  public int id() {
    return this.id;
  }
}
