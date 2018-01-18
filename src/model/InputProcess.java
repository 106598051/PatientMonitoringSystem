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

  /** The buffered reader. */
  private BufferedReader bufferedReader;

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
    this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputFile));
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
    do {
      input = this.bufferedReader.readLine();
      if (input == null) {
        break;
      } else if (input.trim().equals("")) {
        continue;
      } else {
        this.setting.add(input.trim().split("\\s+"));
      }
    } while (true);
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
    boolean patientValid = false;
    String patientName = "";
    int patientPeriod = 0;
    String deviceCategory = "";
    String deviceName = "";
    String datasetFilePath = "";
    double safeRangeLowerBound = 0.0;
    double safeRangeUpperBound = 0.0;
    for (int i = 1; i < this.setting.size(); i++) {
      if (setting.get(i).length == 0) {
        continue;
      }
      if (this.setting.get(i).length == 3 && this.setting.get(i)[0].equals("patient")) {
        patientName = this.setting.get(i)[1];
        patient = this.monitor.getPatient(patientName);
        if (patient == null) {
          try {
            patientPeriod = Integer.parseInt(this.setting.get(i)[2]);
            this.monitor.addPatient(patientName, patientPeriod);
            patientAmount++;
            patientIndex = patientAmount - 1;
          } catch (NumberFormatException e) {
            continue;
          }
        } else {
          patientIndex = patient.id();
        }
        patientValid = true;
      } else if (this.setting.get(i).length == 3) {
        patientValid = false;
      } else if (this.setting.get(i).length == 5 && patientValid) {
        deviceCategory = this.setting.get(i)[0];
        deviceName = this.setting.get(i)[1];
        datasetFilePath = this.setting.get(i)[2];
        try {
          safeRangeLowerBound = Double.parseDouble(this.setting.get(i)[3]);
          safeRangeUpperBound = Double.parseDouble(this.setting.get(i)[4]);
          this.monitor.getPatient(patientIndex).addDevice(deviceCategory, deviceName,
              datasetFilePath, safeRangeLowerBound, safeRangeUpperBound);
        } catch (NumberFormatException e) {
          continue;
        }
      }
    }
  }
}
