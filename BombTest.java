import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BombTest {

	@Test
	public void testWind() {
		Bomb bomb = new Bomb(0, 0, 0, 0, null, 0, 45, 10);
		assertEquals(bomb.getWindDirection(), 45);
		assertEquals(bomb.getWindSpeed(), 10);
	}
	
	@Test
	public void testRelease() {
		Bomb bomb = new Bomb(100, 200, 300, 10, null, 0, 0, 0);
		assertEquals(bomb.getReleaseCoordinates().getX(),100);
		assertEquals(bomb.getReleaseCoordinates().getY(), 200);
		assertEquals(bomb.getReleaseAltitude(), 300);
		assertEquals(bomb.getDescentSpeed(), 10);
	}
	
	@Test
	public void testError() {
		//NONE error type
		Bomb.E_ErrorType error = Bomb.E_ErrorType.NONE;
		Bomb bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(bomb.getErrorType(), Bomb.E_ErrorType.NONE);
		assertEquals(bomb.getErrorRange(), 10);
		//UNIFORM error type
		error = Bomb.E_ErrorType.UNIFORM;
		bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(bomb.getErrorType(), Bomb.E_ErrorType.UNIFORM);
		assertEquals(bomb.getErrorRange(), 10);
		//GAUSSIAN
		error = Bomb.E_ErrorType.GAUSSIAN;
		bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(bomb.getErrorType(), Bomb.E_ErrorType.GAUSSIAN);
		assertEquals(bomb.getErrorRange(), 10);
	}
	
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
