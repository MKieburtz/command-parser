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

}
