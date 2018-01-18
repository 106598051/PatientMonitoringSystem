package model;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * The Class JTest. Using JUnit 5.
 */
class JTest {

  /**
   * Device constructor.
   *
   * @throws Exception the exception
   */
  @Test
  void deviceConstructor() throws Exception {
    Device device =
        new Device("BloodPressureSensor", "sensor1", "src/BloodPressureData1.dataset", 150, 200);
    assertEquals("BloodPressureSensor", device.category());
    assertEquals("sensor1", device.name());
    assertEquals("src/BloodPressureData1.dataset", device.datasetFilePath());
    assertEquals(0, device.datasetAmount());
    assertEquals(150, device.safeRange().lowerBound());
    assertEquals(200, device.safeRange().upperBound());
    device.factorDataset().closeFile();
  }

  /**
   * Device constructor fail.
   */
  @Test
  void deviceConstructorFail() {
    // Executable closureContainingCodeToTest = () -> {
    // new Device("BloodPressureSensor", "sensor1", "", 150, 200);
    // };
    // assertThrows(FileNotFoundException.class, closureContainingCodeToTest);
    assertThrows(FileNotFoundException.class, () -> {
      new Device("BloodPressureSensor", "sensor1", "", 150, 200);
    });
  }

  /**
   * Patient constructor.
   */
  @Test
  void patientConstructor() {
    Patient patient = new Patient("Mark", 600, 0);
    assertEquals("Mark", patient.name());
    assertEquals(600, patient.period());
    assertEquals(0, patient.id());
  }

  /**
   * Patient add device.
   *
   * @throws Exception the exception
   */
  @Test
  void patientAddDevice() throws Exception {
    Patient patient = new Patient("Mark", 600, 0);
    patient.addDevice("BloodPressureSensor", "sensor1", "src/BloodPressureData1.dataset", 150, 200);
    assertEquals("BloodPressureSensor", patient.getDevice(0).category());
    assertEquals("sensor1", patient.getDevice(0).name());
    assertEquals("src/BloodPressureData1.dataset", patient.getDevice(0).datasetFilePath());
    assertEquals(150, patient.getDevice(0).safeRange().lowerBound());
    assertEquals(200, patient.getDevice(0).safeRange().upperBound());
    patient.getDevice(0).factorDataset().closeFile();
  }

  /**
   * Patient add device fail.
   */
  @Test
  void patientAddDeviceFail() {
    Patient patient = new Patient("Mark", 600, 0);
    // Executable closureContainingCodeToTest = () -> {
    // patient.addDevice("BloodPressureSensor", "sensor1", "", 150, 200);
    // };
    assertThrows(FileNotFoundException.class, () -> {
      patient.addDevice("BloodPressureSensor", "sensor1", "", 150, 200);
    });

  }

  /**
   * Patient get device.
   *
   * @throws Exception the exception
   */
  @Test
  void patientGetDevice() throws Exception {
    Patient patient = new Patient("Mark", 600, 0);
    patient.addDevice("BloodPressureSensor", "sensor1", "src/BloodPressureData1.dataset", 150, 200);
    assertEquals("BloodPressureSensor", patient.getDevice(0).category());
    assertEquals(null, patient.getDevice(1));
    patient.getDevice(0).factorDataset().closeFile();
  }

  /**
   * Factor dataset read line.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  void factorDatasetReadLine() throws IOException {
    FactorDataset factorDataset = new FactorDataset("src/BloodPressureData1.dataset");
    assertEquals(150, factorDataset.readData());
    assertEquals(123, factorDataset.readData());
    assertEquals(-1, factorDataset.readData());
    assertEquals(200, factorDataset.readData());
    assertEquals(-1, factorDataset.readData());
    factorDataset.closeFile();
  }

  /**
   * Factor dataset constructo fail.
   */
  @Test
  void factorDatasetConstructoFail() {
    Executable closureContainingCodeToTest = () -> {
      new FactorDataset("");
    };
    assertThrows(FileNotFoundException.class, closureContainingCodeToTest);
  }

  /**
   * Monitor constructor.
   */
  @Test
  void monitorConstructor() {
    int monitorPeriod = 3000;
    Monitor monitor = new Monitor(monitorPeriod);
    assertEquals(3000, monitor.monitorPeriod());
  }

  /**
   * Monitor add patient.
   */
  @Test
  void monitorAddPatient() {
    Monitor monitor = new Monitor(3000);
    String patientName = "Mark";
    int patientPeriod = 600;
    monitor.addPatient(patientName, patientPeriod);
    assertEquals("Mark", monitor.getPatient(0).name());
    assertEquals(600, monitor.getPatient(0).period());
    assertEquals(1, monitor.patient().size());
  }

  /**
   * Monitor get patient by name.
   */
  @Test
  void monitorGetPatientByName() {
    Monitor monitor = new Monitor(3000);
    monitor.addPatient("Mark", 600);
    assertEquals(600, monitor.getPatient("Mark").period());
  }

  /**
   * Monitor initial.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  void monitorInitial() throws IOException {
    Monitor monitor = new Monitor(3000);
    monitor.initial();
    assertEquals(0, monitor.timestamp());
  }

  // @Test
  // void monitorStart() throws IOException {
  // Monitor monitor = new Monitor(3000);
  // monitor.addPatient("Mark", 600);
  // }

}
