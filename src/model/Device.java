package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class Device. Contain device category, name, safe range.
 */
class Device {

  /** The category. */
  private String category;

  /** The name. */
  private String name;

  /** The dataset file path. */
  private String datasetFilePath;

  /** The factor dataset. */
  private FactorDataset factorDataset;

  /** The dataset amount. */
  private int datasetAmount;

  /** The timestamp. */
  private ArrayList<Integer> timestamp;

  /** The dataset. */
  private ArrayList<Double> dataset;

  /** The safe range. */
  private SafeRange safeRange;

  /**
   * Instantiates a new device.
   *
   * @param category the category
   * @param name the name
   * @param datasetFilePath the dataset file path
   * @param safeRangeLowerBound the safe range lower bound
   * @param safeRangeUpperBound the safe range upper bound
   * @throws FileNotFoundException the file not found exception
   */
  public Device(String category, String name, String datasetFilePath, double safeRangeLowerBound,
      double safeRangeUpperBound) throws FileNotFoundException {
    this.category = category;
    this.name = name;
    this.datasetFilePath = datasetFilePath;
    this.factorDataset = new FactorDataset(this.datasetFilePath);
    this.datasetAmount = 0;
    this.timestamp = new ArrayList<Integer>();
    this.dataset = new ArrayList<Double>();
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
   * Dataset file path.
   *
   * @return the string
   */
  public String datasetFilePath() {
    return this.datasetFilePath;
  }

  /**
   * Factor dataset.
   *
   * @return the factor dataset
   */
  public FactorDataset factorDataset() {
    return this.factorDataset;
  }

  /**
   * Dataset amount.
   *
   * @return the int
   */
  public int datasetAmount() {
    return this.datasetAmount;
  }

  /**
   * Timestamp.
   *
   * @param index the index
   * @return the int
   */
  public int timestamp(int index) {
    int timestamp = -1;
    if (index < this.timestamp.size()) {
      timestamp = this.timestamp.get(index);
    }
    return timestamp;
  }

  /**
   * Dataset.
   *
   * @param index the index of target dataset
   * @return the content in dataset of target index
   */
  public double dataset(int index) {
    return this.dataset.get(index);
  }

  /**
   * Display factor dataset of target index.
   *
   * @param index the index of target dataset
   * @return the string
   */
  public String factorDatasetStringify(int index) {
    return "[" + this.timestamp(index) + "] " + this.dataset(index);
  }

  /**
   * Safe range accessor methods.
   *
   * @return the safe range of device
   */
  public SafeRange safeRange() {
    return this.safeRange;
  }

  /**
   * Read factor value.
   *
   * @param timestamp the timestamp
   * @return the index of last dataset.
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public int readFactorValue(int timestamp) throws IOException {
    double factorValue = this.factorDataset.readData();
    this.timestamp.add(timestamp);
    this.dataset.add(factorValue);
    return this.datasetAmount++;

  }

  /**
   * Check record state.
   *
   * @param index the index
   * @return 1 if read factors exceed the safe ranges, -1 if device falls, 0 if is in safe ranges.
   */
  public int checkRecordState(int index) {
    int state = 0;
    if (this.dataset.get(index) == -1) {
      state = -1;
    } else if (this.dataset.get(index) > this.safeRange.upperBound()
        || this.dataset.get(index) < this.safeRange.lowerBound()) {
      state = 1;
    }
    return state;
  }

  /**
   * Disable.
   *
   * @return true, if successful
   */
  public boolean disable() {
    boolean result = false;
    try {
      this.factorDataset.closeFile();
      result = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Display record.
   */
  public String displayRecord() {
    String output = "";
    for (int i = 0; i < this.datasetAmount; i++) {
      // System.out.println(this.factorDatasetStringify(i));
      output += this.factorDatasetStringify(i) + "\n";
    }
    return output;
  }
}
