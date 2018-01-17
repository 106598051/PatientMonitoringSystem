import java.io.FileNotFoundException;
import java.io.IOException;
import model.InputProcess;
import model.Monitor;

/**
 * The Class Main with main method.
 */

/**
 * @author Jeffrey
 *
 */
public class Main {

  /**
   * The main method.
   *
   * @param args the arguments
   * 
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      // System.out.println("args: " + args[0]);
      InputProcess process = null;
      try {
        process = new InputProcess(args[0]);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      if (process != null) {
        Monitor monitor;
        try {
          monitor = process.setFromInput();
          if (monitor != null) {
            try {
              monitor.start();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        } catch (FileNotFoundException e) {
          System.out.println("Input file not found. Target path = " + args[0]);
          e.printStackTrace();
        }
      }
    } else {
      System.out.println("No input.");
    }
  }

}
