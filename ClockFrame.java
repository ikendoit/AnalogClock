import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/** Analog Clockprogram, displays an analog clock, automate the clock with real time.
 *  @Author         Trung Kien Nguyyen
 *  @ID             100284963
 *  @Professor      Jeremy Hilliker
 *  @version        1.0
 *  @honorableMention StackOverFlow: shows how to handle graphics, LocalTime Library.
 *  
 */

public class ClockFrame {

  private final static int WIDTH = 500;
  private final static int HEIGHT = 500;

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame();
    frame.setBackground(Color.DARK_GRAY);
    frame.setSize(WIDTH, HEIGHT);
    frame.setTitle("A Clock");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ClockComponent clock = new ClockComponent();
    frame.add(clock);
    frame.setVisible(true);
    
// automating the clock
    while (true){
      Thread.sleep(1000);
      frame.remove(clock);
      clock = new ClockComponent();
      frame.add(clock);
      frame.setVisible(true);
    }
    
  }
}
