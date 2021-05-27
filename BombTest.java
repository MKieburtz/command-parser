
public class BombTest {
	
	public void UniformErrorNoWind() {
		// double releaseX, double releaseY, double releaseAltitude, double descentSpeed, E_ErrorType errorType, double errorRange, double windDirection, double windSpeed
		Bomb bomb = new Bomb(200.00, 300.00, 1500.00, 100.00, Bomb.E_ErrorType.UNIFORM, 150.00, 0, 0);
		for(int i = 0;i < 100;i++) {
			Bomb.Coordinates c = bomb.drop();
			System.out.println(c.getX() + "\t" + c.getY());
		}
		System.out.println();
	}
	
	public void GaussianErrorNoWind() {
		// double releaseX, double releaseY, double releaseAltitude, double descentSpeed, E_ErrorType errorType, double errorRange, double windDirection, double windSpeed
		Bomb bomb = new Bomb(200.00, 300.00, 1500.00, 100.00, Bomb.E_ErrorType.GAUSSIAN, 150.00, 0, 0);
		for(int i = 0;i < 100;i++) {
			Bomb.Coordinates c = bomb.drop();
			System.out.println(c.getX() + "\t" + c.getY());
		}
		System.out.println();
	}
}
