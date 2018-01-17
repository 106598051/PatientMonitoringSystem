package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains patients to monitoring.
 */
public class Monitor {

  /** The moniter period. */
  private int monitorPeriod;

  /** The patient. */
  private ArrayList<Patient> patient;

  /** The timestamp. */
  private int timestamp;

  /**
   * Instantiates a new monitor.
   *
   * @param monitorPeriod the monitor period
   */
  public Monitor(int monitorPeriod) {
    this.monitorPeriod = monitorPeriod;
    this.patient = new ArrayList<Patient>();
    this.timestamp = 0;
  }

  /**
   * Moniter period.
   *
   * @return the int
   */
  public int monitorPeriod() {
    return this.monitorPeriod;
  }

  /**
   * Patient.
   *
   * @return the array list
   */
  public ArrayList<Patient> patient() {
    return this.patient;
  }

  /**
   * Timestamp.
   *
   * @return the int
   */
  public int timestamp() {
    return this.timestamp;
  }

  /**
   * Adds the patient.
   *
   * @param name the name
   * @param period the period
   */
  public void addPatient(String name, int period) {
    Patient patient = new Patient(name, period, this.patient.size());
    this.patient.add(patient);
  }

  /**
   * Gets the patient.
   *
   * @param index the index
   * @return the patient
   */
  public Patient getPatient(int index) {
    Patient patient = null;
    if (index < this.patient.size()) {
      patient = this.patient.get(index);
    }
    return patient;
  }

  /**
   * Gets the patient.
   *
   * @param name the name
   * @return the patient
   */
  public Patient getPatient(String name) {
    Patient patient = null;
    for (int i = 0; i < this.patient.size(); i++) {
      if (this.patient.get(i).name().equals(name)) {
        patient = this.patient.get(i);
      }
    }
    return patient;
  }

  /**
   * Initial.
   */
  public void initial() {
    this.timestamp = 0;
  }

  /**
   * Start.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void start() throws IOException {
    for (; timestamp <= this.monitorPeriod; timestamp++) {
      for (int i = 0; i < this.patient.size(); i++) {
        this.patient.get(i).checkDevice(timestamp);
      }
    }
    this.stop();
  }

  /**
   * Stop.
   */
  private void stop() {
    for (int i = 0; i < this.patient.size(); i++) {
      this.patient.get(i).displayFactorDatabase();
    }
  }
}
