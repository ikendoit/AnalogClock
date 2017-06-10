
import java.awt.*;
import java.awt.geom.Line2D;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
/** Analog Clockprogram, displays an analog clock, automate the clock with real time.
 *  @Author         Trung Kien Nguyyen
 *  @ID             100284963
 *  @Professor      Jeremy Hilliker
 *  @version        1.0
 *  @honorableMention StackOverFlow: shows how to handle graphics, LocalTime Library.
 *  
 */
public class Hand {

    public final static double RADIANS = Clock.RADIANS;

    public enum TimeUnit {
        MILLS(ChronoField.MILLI_OF_SECOND, 0 , 0),
        SECOND(ChronoField.SECOND_OF_MINUTE, 80, 0.5f, Color.RED),
        MINUTE(ChronoField.MINUTE_OF_HOUR, 90, 1,Color.YELLOW),
        HOUR(ChronoField.HOUR_OF_AMPM, 60, 2, Color.WHITE);

        public final ChronoField cf;
        public final int length;
        public final BasicStroke stroke;
        public final Color color;
        TimeUnit(ChronoField aCF, int aLength, float aWidth) {
            this(aCF, aLength, aWidth, Color.BLACK);
        }
        TimeUnit(ChronoField aCF, int aLength, float aWidth, Color aColor) {
            cf = aCF;
            length = (Clock.SCALE * aLength) / (100 * 2);
            stroke = new BasicStroke(aWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            color = aColor;
        }
    }

    private final TimeUnit unit; // ie: hour, minute, second, etc
    public final double angle; // the rotation of the hand in radians from 12 o'clock

    public Hand(TimeUnit aUnit, LocalTime aTime) {
            this(aUnit, aTime, null);
    }

    /** Contructor Hand : determines the angles for the hands of the clock.
     *  @param aUnit    the time unit of the hand
     *  @param aTime    the local current time of the host machine
     *  @param prevHand the previous hand.
     */
    public Hand(TimeUnit aUnit, LocalTime aTime, Hand prevHand) {
        unit = aUnit;
        switch (aUnit){ 
            case SECOND : angle = Math.toRadians(6* aTime.getSecond()); break;  
            case MINUTE : angle = Math.toRadians(6* aTime.getMinute()+ 0.1*aTime.getSecond()); break; 
            case HOUR   : {
                            int hour = aTime.getHour();
                            if (hour > 12) {
                                hour -=12 ; 
                            } 
                            angle = Math.toRadians(30*hour+ 0.5* aTime.getMinute()+ (1/120)*aTime.getSecond()); break;
            }
            default     : angle = Double.NaN;
        }
    }

    /** function draw : draw a hand using graphic2D
     *  @param  g   Graphics2D that draws the hand
     * 
     */
    public void draw(Graphics2D g) {
        int radius = Clock.CENTRE;
        g.setColor(unit.color);
        g.setStroke(unit.stroke);
        g.draw(new Line2D.Double(radius,radius,radius+Math.sin(angle)*unit.length,radius-Math.cos(angle)*unit.length));

    }
}
