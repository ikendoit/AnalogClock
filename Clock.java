import java.time.LocalTime;
import java.time.LocalDate;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.*;

/** Analog Clockprogram, displays an analog clock, automate the clock with real time.
 *  @Author         Trung Kien Nguyyen
 *  @ID             100284963
 *  @Professor      Jeremy Hilliker
 *  @version        1.0
 *  @honorableMention StackOverFlow: shows how to handle graphics, LocalTime Library.
 *  
 */
public class Clock {

    public final static double RADIANS = 2*Math.PI;
    public final static int SCALE = 200;
    public final static int CENTRE = SCALE/2;
    public final static int RADIUS = CENTRE;

    private final static int NUM_TICKS = 12;
//    private final static int NUM_FAT_TICKS = 4;
//    private final static int TICK_LENGTH = SCALE / 10;

    private LocalTime time;
    private LocalDate date;

    public Clock() {
        this(LocalTime.now(),LocalDate.now());
    }
    public Clock(LocalTime aTime,LocalDate aDate) {
        time = aTime;
        date = aDate;
    }

    /**draw the clock, hands and other features
     * @param g     the graphics2D used to draw a clock
     */
    public void draw(Graphics2D g) {

        //draw clock
        g.setColor(Color.CYAN );
        g.setColor(Color.BLACK);
        g.fill(new Ellipse2D.Double(0,0,SCALE,SCALE));
        g.setColor(Color.CYAN);
        //draw ticks
        for (int i = 1 ; i <=NUM_TICKS;i++){
            if (time.getHour() == i || time.getHour() == i - 1  || time.getHour() == i + 12 || time.getHour() == i +12 -1) {
                g.setColor(Color.MAGENTA );
            } 
            if (i%3 ==0){
                g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL ));
                double xsource = RADIUS+Math.sin(Math.toRadians(i*30))*(RADIUS-13);
                double ysource = RADIUS-Math.cos(Math.toRadians(i*30))*(RADIUS-13); 
                double xend    = RADIUS+Math.sin(Math.toRadians(i*30))*(RADIUS-7);
                double yend    = RADIUS-Math.cos(Math.toRadians(i*30))*(RADIUS-7);
                g.draw(new Line2D.Double(xsource,ysource,xend,yend));
            } else {
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.CAP_SQUARE));
            double xsource = RADIUS+Math.sin(Math.toRadians(i*30))*(RADIUS-8);
            double ysource = RADIUS-Math.cos(Math.toRadians(i*30))*(RADIUS-8); 
            double xend    = RADIUS+Math.sin(Math.toRadians(i*30))*(RADIUS-3);
            double yend    = RADIUS-Math.cos(Math.toRadians(i*30))*(RADIUS-3);
            g.draw(new Line2D.Double(xsource,ysource,xend,yend));
            }
            g.setColor(Color.CYAN);
        }
        //draw dates
        g.setColor(Color.GREEN );
        g.drawString(date.getDayOfMonth()+","+date.getMonth()+","+date.getYear(),(int) (RADIUS*5/8),(int) (RADIUS *3/4));
        g.drawString(date.getDayOfWeek()+"",(int) (RADIUS *13/16),(int) (RADIUS *7/8));       
        g.setColor(Color.CYAN );
        //draw number 3 6 9 12
        for (int i = 1 ; i<=4 ; i++ ){
            if (time.getHour() == i*3  || time.getHour() == i*3-1 || time.getHour() == i*3 +12 || time.getHour() == i*3+12 -1){
                g.setColor(Color.MAGENTA );
            }
            g.drawString(i*3+"", (int) (RADIUS-4+Math.sin(Math.toRadians(i*90))*(RADIUS-20)), (int) (RADIUS+5-Math.cos(Math.toRadians(i*90))*(RADIUS-20)));
            g.setColor(Color.CYAN);
        }
        //draw time in middle
        g.drawString(formatTime(time.getHour()) +":" + formatTime(time.getMinute())+":"+formatTime(time.getSecond()),(int) (RADIUS*3/4) , (int) (RADIUS*4/3));

        //draw hands
        for (Hand i : getHands()){
            i.draw(g);
        }
        //draw circle root of hands
        g.setColor(Color.LIGHT_GRAY);
        g.fill(new Ellipse2D.Double(RADIUS*97/100,RADIUS*97/100,SCALE/30,SCALE/30));
    }

    /** create array of Hand Objects
     * @return hands    array of Hand Objects
     */
    public Hand[] getHands() {
        Hand[] hands = new Hand[3]; 
        hands[0] = new Hand(Hand.TimeUnit.HOUR,time);
        hands[1] = new Hand(Hand.TimeUnit.MINUTE,time);
        hands[2] = new Hand(Hand.TimeUnit.SECOND,time);
        return hands;
    }

    public String formatTime(int time){
        if (time < 10){
            return "0"+time;
        }
        return time+"";
    }
}
