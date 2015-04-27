package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import static jumpingalien.tests.util.TestUtils.intArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jumpingalien.model.Action;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import static org.junit.Assert.*;

public class worldtest {

	
	@Test
	public void hasplayerwontest() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(15, 5, 5, 2, 2, 0, 0);
		world.setMazub(facade.createMazub(0, 0, spriteArrayForSize(3, 3)));
		assertTrue(world.hasplayerwon());
		world.setMazub(facade.createMazub(35, 35, spriteArrayForSize(3, 3)));
		assertFalse(world.hasplayerwon());
	}
	@Test
	public void addsharktest() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(0, 0, 0, 0, 0, 0, 0);
		Shark shark = facade.createShark(0, 0,spriteArrayForSize(3, 3));
		world.addShark(shark);
		assertTrue(world.getsharks().contains(shark));
	}
	@Test
	public void addplanttest() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(0, 0, 0, 0, 0, 0, 0);
		Plant plant = facade.createPlant(0, 0,spriteArrayForSize(3, 3));
		world.addPlants(plant);
		assertTrue(world.getPlants().contains(plant));
	}
	@Test
	public void addslimetest() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(0, 0, 0, 0, 0, 0, 0);
		Slime slime = facade.createSlime(0, 0,spriteArrayForSize(3, 3),new School());
		world.addSlimes(slime);
		assertTrue(world.getSlimes().contains(slime));
	}
	@Test
	public void gameovertest(){
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(15, 5, 5, 2, 2, 0, 0);
		world.setMazub(facade.createMazub(0, 0, spriteArrayForSize(3, 3)));
		assertTrue(facade.isGameOver(world));//could be this test cannot function to solve this go to entities and make the method sethitoints public
		world.setMazub(facade.createMazub(35, 35, spriteArrayForSize(3, 3)));
		world.getMazub().setHitPoints(0);
		assertTrue(facade.isGameOver(world));
	}
	

}