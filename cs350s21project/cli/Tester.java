// Team 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;
import org.junit.jupiter.api.Test;

public class DefineCommandHandlerTest {
    @Test
    public void testDefineShip() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define ship ship1 with munitions (munition1)",
                "define ship ship1 with munitions (munition1)".split(" +"));
    }

    @Test
    public void testDefineThermal() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor thermal       SENSOR_THERMAL1      with field of view 31          sensitivity 2.5",
                "define sensor thermal       SENSOR_THERMAL1      with field of view 31          sensitivity 2.5".split(" +"));
    }

    @Test
    public void testDefineTorpedo() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define munition torpedo      MUNITION_TORPEDO1     with sensor SENSOR_ACOUSTIC1     fuze FUZE_ACOUSTIC1     arming time 1.1",
                "define munition torpedo      MUNITION_TORPEDO1     with sensor SENSOR_ACOUSTIC1     fuze FUZE_ACOUSTIC1     arming time 1.1".split(" +"));
    }

    @Test
    public void testDefineRadar() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor radar radar1 with field of view 30 power 20 sensitivity 10.5",
                "define sensor radar radar1 with field of view 30 power 20 sensitivity 10.5".split(" +"));
    }

    @Test
    public void testAcousticSensor() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor acoustic acoustic1 with sensitivity 15.5", "define sensor acoustic acoustic1 with sensitivity 15.5".split(" +"));
    }

    private void evaluate(String commandText) {
        System.out.println("executing: " + commandText);
        CommandInterpreter interpreter = new CommandInterpreter();
        interpreter.evaluate(commandText);
    }

    @Test
    public void fullTest() {
        testSensorCommands();
    }

    private void testBuild1() {
        this.evaluate("define munition bomb MUNITION_BOMB1");
        this.evaluate("define airplane ACTOR_AIRPLANE1 with munition (MUNITION_BOMB1)");
        this.evaluate("create actor MY_AIRPLANE1 from ACTOR_AIRPLANE1 at 49*39'30\"/117*25'33\"/0 with course 225 speed 50");
    }

    private void testCreateAirplane1() {
        this.evaluate("define sensor radar         SENSOR_RADAR1        with field of view 30 power 21 sensitivity 1.5");
        this.evaluate("define sensor thermal       SENSOR_THERMAL1      with field of view 31          sensitivity 2.5");
        this.evaluate("define sensor acoustic      SENSOR_ACOUSTIC1     with                           sensitivity 3.5");
        this.evaluate("define sensor sonar active  SENSOR_SONARACTIVE1  with                  power 32 sensitivity 4.5");
        this.evaluate("define sensor sonar passive SENSOR_SONARPASSIVE1 with                           sensitivity 5.5");
        this.evaluate("define sensor depth         SENSOR_DEPTH1        with                                            trigger depth    -200");
        this.evaluate("define sensor distance      SENSOR_DISTANCE1     with                                            trigger distance   20");
        this.evaluate("define sensor time          SENSOR_TIME1         with                                            trigger time      23.1");
        this.evaluate("define sensor radar         FUZE_RADAR1        with field of view 32 power 23 sensitivity 10.5");
        this.evaluate("define sensor thermal       FUZE_THERMAL1      with field of view 33          sensitivity 10.5");
        this.evaluate("define sensor acoustic      FUZE_ACOUSTIC1     with                           sensitivity 15.5");
        this.evaluate("define sensor sonar active  FUZE_SONARACTIVE1  with                  power 34 sensitivity 15");
        this.evaluate("define sensor sonar passive FUZE_SONARPASSIVE1 with                           sensitivity 14");
        this.evaluate("define sensor depth         FUZE_DEPTH1        with                                            trigger depth    -250");
        this.evaluate("define sensor distance      FUZE_DISTANCE1     with                                            trigger distance   25");
        this.evaluate("define sensor time          FUZE_TIME1         with                                            trigger time      25.1");
        this.evaluate("define munition bomb         MUNITION_BOMB1");
        this.evaluate("define munition shell        MUNITION_SHELL1");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE1                                  with fuze FUZE_ACOUSTIC1");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE2                                  with fuze FUZE_SONARACTIVE1");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE3                                  with fuze FUZE_SONARPASSIVE1");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE4                                  with fuze FUZE_DEPTH1");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE5                                  with fuze FUZE_TIME1");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO1     with sensor SENSOR_ACOUSTIC1     fuze FUZE_ACOUSTIC1     arming time 1.1");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO2     with sensor SENSOR_ACOUSTIC1     fuze FUZE_SONARACTIVE1  arming time 1.2");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO3     with sensor SENSOR_ACOUSTIC1     fuze FUZE_SONARPASSIVE1 arming time 1.3");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO4     with sensor SENSOR_ACOUSTIC1     fuze FUZE_DISTANCE1     arming time 1.4");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO5     with sensor SENSOR_ACOUSTIC1     fuze FUZE_TIME1         arming time 1.5");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO6     with sensor SENSOR_SONARACTIVE1  fuze FUZE_ACOUSTIC1     arming time 2.1");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO7     with sensor SENSOR_SONARACTIVE1  fuze FUZE_SONARACTIVE1  arming time 2.2");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO8     with sensor SENSOR_SONARACTIVE1  fuze FUZE_SONARPASSIVE1 arming time 2.3");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO9     with sensor SENSOR_SONARACTIVE1  fuze FUZE_DISTANCE1     arming time 2.4");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO10    with sensor SENSOR_SONARACTIVE1  fuze FUZE_TIME1         arming time 2.5");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO11    with sensor SENSOR_SONARPASSIVE1 fuze FUZE_ACOUSTIC1     arming time 3.1");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO12    with sensor SENSOR_SONARPASSIVE1 fuze FUZE_SONARACTIVE1  arming time 3.2");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO13    with sensor SENSOR_SONARPASSIVE1 fuze FUZE_SONARPASSIVE1 arming time 3.3");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO14    with sensor SENSOR_SONARPASSIVE1 fuze FUZE_DISTANCE1     arming time 3.4");
        this.evaluate("define munition torpedo      MUNITION_TORPEDO15    with sensor SENSOR_SONARPASSIVE1 fuze FUZE_TIME1         arming time 3.5");
        this.evaluate("define munition missile      MUNITION_MISSILE1     with sensor SENSOR_RADAR1   fuze FUZE_RADAR1   arming distance 1");
        this.evaluate("define munition missile      MUNITION_MISSILE2     with sensor SENSOR_RADAR1   fuze FUZE_THERMAL1 arming distance 2");
        this.evaluate("define munition missile      MUNITION_MISSILE3     with sensor SENSOR_THERMAL1 fuze FUZE_RADAR1   arming distance 3");
        this.evaluate("define munition missile      MUNITION_MISSILE4     with sensor SENSOR_THERMAL1 fuze FUZE_THERMAL1 arming distance 4");
        this.evaluate("define airplane ACTOR_AIRPLANE1 with munition (MUNITION_BOMB1 MUNITION_SHELL1 MUNITION_DEPTHCHARGE1 MUNITION_DEPTHCHARGE2 MUNITION_DEPTHCHARGE3 MUNITION_DEPTHCHARGE4 MUNITION_DEPTHCHARGE5 MUNITION_TORPEDO1 MUNITION_TORPEDO2 MUNITION_TORPEDO3 MUNITION_TORPEDO4 MUNITION_TORPEDO5 MUNITION_TORPEDO6 MUNITION_TORPEDO7 MUNITION_TORPEDO8 MUNITION_TORPEDO9 MUNITION_TORPEDO10 MUNITION_TORPEDO11 MUNITION_TORPEDO12 MUNITION_TORPEDO13 MUNITION_TORPEDO14 MUNITION_TORPEDO15 MUNITION_MISSILE1 MUNITION_MISSILE2 MUNITION_MISSILE3 MUNITION_MISSILE4)");
        this.evaluate("create actor MY_AIRPLANE1  from ACTOR_AIRPLANE1  at 49*39'30\"/117*25'33\"/0 with course 300 speed 100");
    }

    private void testCreateAirplane2() {
        this.evaluate("define munition bomb MUNITION_BOMB1");
        this.evaluate("define airplane ACTOR_AIRPLANE1 with munition (MUNITION_BOMB1)");
        this.evaluate("create actor MY_AIRPLANE1 from ACTOR_AIRPLANE1 at 49*39'30\"/117*25'33\"/1000 with course 300 speed 100");
        this.evaluate("set MY_AIRPLANE1 load munition MUNITION_BOMB1");
        this.evaluate("set MY_AIRPLANE1 deploy munition MY_AIRPLANE1.MUNITION_BOMB1");
    }

    private void testCreateAirplane3() {
        this.evaluate("define sensor acoustic FUZE_ACOUSTIC1 with sensitivity 15.5");
        this.evaluate("define munition depth_charge MUNITION_DEPTHCHARGE1 with fuze FUZE_ACOUSTIC1");
        this.evaluate("define airplane ACTOR_AIRPLANE1 with munition (MUNITION_DEPTHCHARGE1)");
        this.evaluate("create actor MY_AIRPLANE1 from ACTOR_AIRPLANE1 at 49*39'30\"/117*25'33\"/1000 with course 300 speed 100");
        this.evaluate("set MY_AIRPLANE1 load munition MUNITION_DEPTHCHARGE1");
        this.evaluate("set MY_AIRPLANE1 deploy munition MY_AIRPLANE1.MUNITION_DEPTHCHARGE1");
    }

    private void testCreateAirplane4() {
        this.evaluate("define sensor acoustic SENSOR_ACOUSTIC1 with sensitivity 3.5");
        this.evaluate("define sensor acoustic FUZE_ACOUSTIC1 with sensitivity 15.5");
        this.evaluate("define munition torpedo MUNITION_TORPEDO1 with sensor SENSOR_ACOUSTIC1 fuze FUZE_ACOUSTIC1 arming time 1.1");
        this.evaluate("define airplane ACTOR_AIRPLANE1 with munition (MUNITION_TORPEDO1)");
        this.evaluate("create actor MY_AIRPLANE1 from ACTOR_AIRPLANE1 at 49*39'30\"/117*25'33\"/1000 with course 300 speed 100");
        this.evaluate("set MY_AIRPLANE1 load munition MUNITION_TORPEDO1");
        this.evaluate("set MY_AIRPLANE1 deploy munition MY_AIRPLANE1.MUNITION_TORPEDO1");
    }

    private void testMiscCommands() {
        this.evaluate("@load \"my file\"");
        this.evaluate("@pause");
        this.evaluate("@resume");
        this.evaluate("@set update 0.1");
        this.evaluate("@wait 5");
    }

    private void testMunitionCommands() {
        this.evaluate("define munition bomb bomb1");
        this.evaluate("define munition shell shell1");
        this.evaluate("define munition depth_charge depth_charge1 with fuze fuze1");
        this.evaluate("define munition torpedo torpedo1 with sensor sensor1 fuze fuze1 arming time 3.5");
        this.evaluate("define munition missile missile1 with sensor sensor1 fuze fuze1 arming distance 1");
    }

    private void testSensorCommands() {
        this.evaluate("define sensor radar radar1 with field of view 30 power 20 sensitivity 10.5");
        this.evaluate("define sensor thermal thermal1 with field of view 30 sensitivity 10.5");
        this.evaluate("define sensor acoustic acoustic1 with sensitivity 15.5");
        this.evaluate("define sensor sonar active sonarA1 with power 30 sensitivity 15");
        this.evaluate("define sensor sonar passive sonar11 with sensitivity 14");
        this.evaluate("define sensor depth depth1 with trigger depth -200");
        this.evaluate("define sensor distance distance1 with trigger distance 20");
        this.evaluate("define sensor time time1 with trigger time 2.1");
        this.evaluate("undefine sensor time1");
    }

    private void testViewCommands() {
        this.evaluate("create window wTop top view with 200 (49*39'32\" 0*10'0\" 0*0'30\") (117*25'30\" 0*10'0\" 0*0'30\")");
        this.evaluate("lock window wTop on a1");
        this.evaluate("unlock window wTop");
    }
}
