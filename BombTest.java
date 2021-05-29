import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BombTest {

	@Test
	public void testWind() {
		Bomb bomb = new Bomb(0, 0, 0, 0, null, 0, 45, 10);
		assertEquals(45, bomb.getWindDirection());
		assertEquals(10, bomb.getWindSpeed());
	}
	
	@Test
	public void testRelease() {
		Bomb bomb = new Bomb(100, 200, 300, 10, null, 0, 0, 0);
		assertEquals(100, bomb.getReleaseCoordinates().getX());
		assertEquals(200, bomb.getReleaseCoordinates().getY());
		assertEquals(300, bomb.getReleaseAltitude());
		assertEquals(10, bomb.getDescentSpeed());
	}

	@Test
	public void testError() {
		//NONE error type
		Bomb.E_ErrorType error = Bomb.E_ErrorType.NONE;
		Bomb bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(Bomb.E_ErrorType.NONE, bomb.getErrorType());
		assertEquals(10, bomb.getErrorRange());
		//UNIFORM error type
		error = Bomb.E_ErrorType.UNIFORM;
		bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(Bomb.E_ErrorType.UNIFORM, bomb.getErrorType());
		assertEquals(10, bomb.getErrorRange());
		//GAUSSIAN
		error = Bomb.E_ErrorType.GAUSSIAN;
		bomb = new Bomb(0, 0, 0, 0, error, 10, 0, 0);
		assertEquals(Bomb.E_ErrorType.GAUSSIAN, bomb.getErrorType());
		assertEquals(10, bomb.getErrorRange());
	}
}
