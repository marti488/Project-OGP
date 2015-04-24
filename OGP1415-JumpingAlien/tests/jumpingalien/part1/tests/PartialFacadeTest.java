package jumpingalien.part1.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class PartialFacadeTest {

	@Test
	public void startMoveRightCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)

		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}

	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testWalkAnimationLastFrame() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveRight(alien);

		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}

		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testWalkAnimationAllFrames() {
		IFacade facade = new Facade();

		for (int m = 1; m <= 10; m++){
			Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
			Mazub alien = facade.createMazub(0, 0, sprites);
	
			facade.startMoveRight(alien);
	
			facade.advanceTime(alien, 0.005);
			for (int i = 0; i < m; i++) {
				facade.advanceTime(alien, 0.075);
			}
	
			assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
		}
	}
	@Test
	public void testDirectionLingering() {
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 1.5);
		facade.endMoveRight(alien);
		facade.advanceTime(alien, 0.95);
		
		assertEquals(sprites[2], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testDirectionLingering2() {
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 1.5);
		facade.endMoveRight(alien);
		facade.advanceTime(alien, 1.05);
		
		assertEquals(sprites[0], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testDirectionLingeringJUMP() {//Stop moving, then jump. Correct animations while jumping?
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 1.5);
		facade.endMoveRight(alien);
		facade.advanceTime(alien, 0.7); //0.7
		facade.startJump(alien);
		facade.advanceTime(alien, 0.2); //0.9
		
		assertEquals(sprites[4], facade.getCurrentSprite(alien));
		
		facade.advanceTime(alien, 0.15); //1.05
		
		assertEquals(sprites[0], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testDirectionLingeringJUMP2() {	//Start and stop moving whilst jumping, correct behavior afterwards.
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startJump(alien);
		facade.advanceTime(alien, 1); //1
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1); //1.1
		facade.endMoveRight(alien);
		facade.advanceTime(alien, 0.6165); //1.7165 is more or less the duration of a full jump.
		facade.advanceTime(alien, 0.35); //+- 0.95 seconds after endMoveRight
		
		assertEquals(sprites[2], facade.getCurrentSprite(alien));
		
		facade.advanceTime(alien, 0.1); //1.05
		
		assertEquals(sprites[0], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testIllegalJumps() {	//Try jumping multiple times while in the air.
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startJump(alien);
		facade.advanceTime(alien, 1); //Mazub is now mid-air
		facade.startJump(alien); //This should give an error!
	}
	
	@Test
	public void testCreateIllegalMazub() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(0,0,0)); //No sprites: expecting error
	}
	
	@Test
	public void testCreateIllegalMazub2() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(-3, -5, spriteArrayForSize(2, 2));
		
		assertArrayEquals(intArray(0,0), facade.getLocation(alien)); //Defensive programming: negative values => 0
	}
	
	@Test
	public void testMidAirSpawn() { //when spawned in the air, Mazub should have an immediate downwards acceleration.
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 20, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0, -10), facade.getAcceleration(alien), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testMidAirDucking() {
		IFacade facade = new Facade();
		
		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startJump(alien);
		facade.advanceTime(alien, 1); //Mazub is now mid-air
		facade.startDuck(alien); //this is impossible, thus error
	}
	
	@Test
	public void testOutOfBounds() {
		IFacade facade = new Facade();

		Sprite[] sprites = spriteArrayForSize(2, 2);
		Mazub alien = facade.createMazub(2000, 2000, sprites);
		
		assertArrayEquals(intArray(1023,767), facade.getLocation(alien)); //Defensive programming
	}
}
