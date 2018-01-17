package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Class InputProcess.
 */
public class InputProcess {

  /** The input file. */
  private FileInputStream inputFile;

  /** The bufferedreader. */
  private BufferedReader bufferedreader;

  /** The monitor. */
  private Monitor monitor;

  /** The setting. */
  private ArrayList<String[]> setting;

  /**
   * Instantiates a new input process.
   *
   * @param filePath the file path
   * @throws FileNotFoundException the file not found exception
   */
  public InputProcess(String filePath) throws FileNotFoundException {
    this.inputFile = new FileInputStream(filePath);
    this.bufferedreader = new BufferedReader(new InputStreamReader(this.inputFile));
    this.monitor = null;
    this.setting = new ArrayList<String[]>();
    try {
      this.readFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Read file.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void readFile() throws IOException {
    String input;
    while ((input = this.bufferedreader.readLine()) != null) {
      this.setting.add(input.split(" "));
    }
    this.inputFile.close();
  }

  /**
   * Sets the from input.
   *
   * @return the monitor
   * @throws FileNotFoundException the file not found exception
   */
  public Monitor setFromInput() throws FileNotFoundException {
    int monitorPeriod = 0;
    try {
      monitorPeriod = Integer.parseInt(setting.get(0)[0]);
    } catch (NumberFormatException e) {
      System.out.println("Input format error.");
      e.printStackTrace();
    }
    if (monitorPeriod != 0) {
      this.monitor = new Monitor(monitorPeriod);
      this.setMonitor();
    }
    return this.monitor;
  }

  /**
   * Sets the monitor.
   *
   * @throws FileNotFoundException the file not found exception
   */
  private void setMonitor() throws FileNotFoundException {
    int patientAmount = this.monitor.patient().size();
    int patientIndex = 0;
    Patient patient = null;
    String patientName = "";
    int patientPeriod = 0;
    String deviceCategory = "";
    String deviceName = "";
    String datasetFilePath = "";
    double safeRangeLowerBound = 0.0;
    double safeRangeUpperBound = 0.0;
    for (int i = 1; i < this.setting.size(); i++) {
      if (setting.get(i).length == 3) {
        patientName = this.setting.get(i)[1];
        patient = this.monitor.getPatient(patientName);
        if(patient == null) {
          patientPeriod = Integer.parseInt(this.setting.get(i)[2]);
          this.monitor.addPatient(patientName, patientPeriod);
          patientAmount++;
          patientIndex = patientAmount - 1;
        }else {
          patientIndex = patient.id();
        }
      } else {
        deviceCategory = this.setting.get(i)[0];
        deviceName = this.setting.get(i)[1];
        datasetFilePath = this.setting.get(i)[2];
        safeRangeLowerBound = Double.parseDouble(this.setting.get(i)[3]);
        safeRangeUpperBound = Double.parseDouble(this.setting.get(i)[4]);
        this.monitor.getPatient(patientIndex).addDevice(deviceCategory, deviceName, datasetFilePath,
            safeRangeLowerBound, safeRangeUpperBound);
      }
    }
  }
}
