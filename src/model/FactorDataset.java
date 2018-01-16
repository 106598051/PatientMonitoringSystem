package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Auto-generated Javadoc
/**
 * The Class FactorDataset.
 */
public class FactorDataset {

  /** The factor data. */
  private FileInputStream factorData;

  /** The bufferedreader. */
  private BufferedReader bufferedreader;

  /**
   * Instantiates a new factor dataset file.
   *
   * @param filePath the file path
   * @throws FileNotFoundException the file not found exception
   */
  public FactorDataset(String filePath) throws FileNotFoundException {
    this.factorData = new FileInputStream(filePath);
    this.bufferedreader = new BufferedReader(new InputStreamReader(this.factorData));
  }

  /**
   * Factor data.
   *
   * @return the file input stream
   */
  public FileInputStream factorData() {
    return this.factorData;
  }

  /**
   * Read data.
   *
   * @return the int
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public double readData() throws IOException {
    double data = -1.0;
    String input = null;
    input = this.bufferedreader.readLine();
    if (input != null) {
      data = Double.parseDouble(input);
    }
    return data;
  }

  /**
   * Close file.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void closeFile() throws IOException {
    factorData.close();
  }
}