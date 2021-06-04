import org.junit.jupiter.api.Test;

public class BombBehaviorTest {
    @Test
    public void NoErrorWithWind() {
        Bomb bomb = new Bomb(200, 300, 1500, 100, Bomb.E_ErrorType.NONE, 150, 60, 25);
        for(int i = 0;i < 100;i++) {
            Bomb.Coordinates c = bomb.drop();
            System.out.println(c.getX() + "\t" + c.getY());
        }
        System.out.println();
    }

    @Test
    public void UniformErrorNoWind() {
        Bomb bomb = new Bomb(200.00, 300.00, 1500.00, 100.00, Bomb.E_ErrorType.UNIFORM, 150.00, 0, 0);
        for(int i = 0;i < 100;i++) {
            Bomb.Coordinates c = bomb.drop();
            System.out.println(c.getX() + "\t" + c.getY());
        }
        System.out.println();
    }

    @Test
    public void GaussianErrorNoWind() {
        Bomb bomb = new Bomb(200.00, 300.00, 1500.00, 100.00, Bomb.E_ErrorType.GAUSSIAN, 150.00, 0, 0);
        for(int i = 0;i < 100;i++) {
            Bomb.Coordinates c = bomb.drop();
            System.out.println(c.getX() + "\t" + c.getY());
        }
        System.out.println();
    }

    @Test
    public void UniformErrorWithWind(){
        Bomb bomb = new Bomb(200, 300, 1500, 100, Bomb.E_ErrorType.UNIFORM, 150, 60, 25);
        for(int i = 0;i < 100;i++) {
            Bomb.Coordinates c = bomb.drop();
            System.out.println(c.getX() + "\t" + c.getY());
        }
        System.out.println();
    }

    @Test
    public void testNoErrorNoWind() {
        for (int i = 0; i < 100; i++) {
            Bomb bomb = new Bomb(200, 300, 1500, 100, Bomb.E_ErrorType.NONE, 0, 0, 0);
            Bomb.Coordinates coords = bomb.drop();
            System.out.println(coords.getX() + "\t" + coords.getY());
        }
        System.out.println();
    }

    @Test
    public void testGaussianErrorWind() {
        for (int i = 0; i < 100; i++) {
            Bomb bomb = new Bomb(200, 300, 1500, 100, Bomb.E_ErrorType.GAUSSIAN, 150, 60, 25);
            Bomb.Coordinates coords = bomb.drop();
            System.out.println(coords.getX() + "\t" + coords.getY());
        }
        System.out.println();
    }
}
