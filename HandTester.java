
import java.time.LocalTime;

/** Analog Clockprogram, displays an analog clock, automate the clock with real time.
 *  @Author         Trung Kien Nguyyen
 *  @ID             100284963
 *  @Professor      Jeremy Hilliker
 *  @version        1.0
 *  @honorableMention StackOverFlow: shows how to handle graphics, LocalTime Library.
 *  
 */

public class HandTester {

    private final static double RADIANS = Math.PI*2;
    private final static double EPSILON = 1E-12;

    public static void main(String[] args) {
        test_0();
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
        System.err.println("PASS");
    }

    private static void test_0() {
        LocalTime time = LocalTime.MIN;
        Hand hours = new Hand(Hand.TimeUnit.HOUR, time);
        assert 0 == hours.angle;
    }

    /** test 1 : check if angle of minute hand is correct at noon
     * 
     */
    private static void test_1() { 
        LocalTime time = LocalTime.NOON; 
        Hand minute = new Hand(Hand.TimeUnit.MINUTE, time); 
        assert 0 == minute.angle;
    }

    /**test 2 : check if at 3:00 am, angle of hour hand is correct
     * 
     */
    private static void test_2() { 
        Hand hour = new Hand(Hand.TimeUnit.HOUR, LocalTime.of(3, 00) ) ; 
        assert Math.toRadians(90) == hour.angle;
    }

    /**test 3 : check at 21:00, angle of hour hand is correct
     * 
     */
    private static void test_3() { 
        Hand hour = new Hand(Hand.TimeUnit.HOUR, LocalTime.of(21,00)) ; 
        assert Math.toRadians(270) == hour.angle; 
    }

    /**test 4 : check at 6:30:5 pm to see if 180 rad is wrong for minute hand
     * 
     */
    private static void test_4() { 
        Hand minute = new Hand(Hand.TimeUnit.MINUTE, LocalTime.of(18, 30, 5));
        assert Math.toRadians(180) != minute.angle;
    }

    /**test 5: check at 4:30:30 am, if 180 rad is correct for second hand.
     * 
     */
    private static void test_5() { 
        Hand second = new Hand(Hand.TimeUnit.SECOND, LocalTime.of(04, 30, 30) );
        assert Math.toRadians(180) == second.angle;
    }
    // ** Do Not Change Below this line ** //

    private static void assertEquals(double exp, double act) {
        assert Math.abs(exp - act) <= EPSILON :
            "\nexp: " + exp + "\nact: " + act + "\ndif: " + (exp-act);
    }

    static {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (!assertsEnabled) {
            throw new RuntimeException("Asserts must be enabled!!! java -ea");
        }
    }
}
