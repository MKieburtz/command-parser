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

        handler.handleDefineCommand("define ship ship1 with munitions (munition1)");
    }

    @Test
    public void testDefineThermal() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor thermal       SENSOR_THERMAL1      with field of view 31          sensitivity 2.5");
    }

    @Test
    public void testDefineTorpedo() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define munition torpedo      MUNITION_TORPEDO1     with sensor SENSOR_ACOUSTIC1     fuze FUZE_ACOUSTIC1     arming time 1.1");
    }

    @Test
    public void testDefineRadar() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor radar radar1 with field of view 30 power 20 sensitivity 10.5");
    }

    @Test
    public void testAcousticSensor() {
        DefineCommandHandler handler = new DefineCommandHandler();

        handler.handleDefineCommand("define sensor acoustic acoustic1 with sensitivity 15.5");
    }
}
