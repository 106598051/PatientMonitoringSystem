import java.io.IOException;
import model.InputProcess;
import model.Monitor;

/**
 * The Class Main with main method.
 */

/**
 * @author Jeffrey
 * @version v0.1.1
 */
public class Main {

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws IOException
   * 
   */
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      InputProcess process = null;
      process = new InputProcess(args[0]);
      if (process != null) {
        Monitor monitor;
        monitor = process.setFromInput();
        if (monitor != null) {
          monitor.start();
        }
      }
    } else {
      System.out.println("No input.");
    }
  }

}
