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
 
 
public class slimetest {
 
 
        @Test
        public void starttest() {
                IFacadePart2 facade = new Facade();
                Slime slime = facade.createSlime(0, 0, spriteArrayForSize(3, 3),new School());
                slime.start(Action.JUMP);
                assertTrue(slime.getJumpAcceleration() == -10);
                slime.start(Action.MOVERIGHT);
                assertTrue(slime.getAcceleration() == 0.7);
                slime.start(Action.MOVELEFT);
                assertTrue(slime.getAcceleration() == -0.7);
               
        }
        @Test
        public void endtest() {
                IFacadePart2 facade = new Facade();
                Slime slime = facade.createSlime(0, 0, spriteArrayForSize(3, 3),new School());
                slime.end(Action.JUMP);
                assertTrue(slime.getJumpAcceleration() == 0);
                assertTrue(slime.getJumpVelocity() == 0);
                slime.end(Action.MOVERIGHT);
                assertTrue(slime.getVelocity() == 0);
                slime.end(Action.MOVELEFT);
                assertTrue(slime.getVelocity() == 0);
        }
        @Test
        public void decidetest() {
                IFacadePart2 facade = new Facade();
                World world = facade.createWorld(5, 4, 3, 1, 1, 1, 1);
                Slime slime= facade.createSlime(0, 0, spriteArrayForSize(3, 3),new School());
                slime.setVelocity(3);
                slime.decideSprite();
                assertEquals(slime.getSprite(), slime.getSprites()[1]);
                slime.setVelocity(-3);
                slime.decideSprite();
                assertEquals(slime.getSprite(), slime.getSprites()[0]);
        }
       
}