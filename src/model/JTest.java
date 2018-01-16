package model;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class JTest {

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

  @Test
  void patientConstructor() {
    Patient patient = new Patient("Mark", 600);
    assertEquals("Mark", patient.name());
    assertEquals(600, patient.period());
  }

  @Test
  void patientAddDevice() throws Exception {
    Patient patient = new Patient("Mark", 600);
    patient.addDevice("BloodPressureSensor", "sensor1", "src/BloodPressureData1.dataset", 150, 200);
    assertEquals("BloodPressureSensor", patient.getDevice(0).category());
    assertEquals("sensor1", patient.getDevice(0).name());
    assertEquals("src/BloodPressureData1.dataset", patient.getDevice(0).datasetFilePath());
    assertEquals(150, patient.getDevice(0).safeRange().lowerBound());
    assertEquals(200, patient.getDevice(0).safeRange().upperBound());
    patient.getDevice(0).factorDataset().closeFile();
  }

  @Test
  void patientGetDevice() throws Exception {
    Patient patient = new Patient("Mark", 600);
    patient.addDevice("BloodPressureSensor", "sensor1", "src/BloodPressureData1.dataset", 150, 200);
    assertEquals("BloodPressureSensor", patient.getDevice(0).category());
    patient.getDevice(0).factorDataset().closeFile();
  }

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

  @Test
  void monitorConstructor() {
    int monitorPeriod = 3000;
    Monitor monitor = new Monitor(monitorPeriod);
    assertEquals(3000, monitor.monitorPeriod());
  }

  @Test
  void monitorAddPatient() {
    Monitor monitor = new Monitor(3000);
    String patientName = "Mark";
    int patientPeriod = 600;
    monitor.addPatient(patientName, patientPeriod);
    assertEquals("Mark", monitor.getPatient(0).name());
    assertEquals(600, monitor.getPatient(0).period());
  }

  @Test
  void monitorGetPatientByName() {
    Monitor monitor = new Monitor(3000);
    monitor.addPatient("Mark", 600);
    assertEquals(600, monitor.getPatient("Mark").period());
  }

  @Test
  void factorDatasetConstructorError() {
    Executable closureContainingCodeToTest = () -> {new FactorDataset("");};
    assertThrows(FileNotFoundException.class, closureContainingCodeToTest);
  }

}
