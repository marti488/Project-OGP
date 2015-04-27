package jumpingalien.part2.tests;
 
import static jumpingalien.tests.util.TestUtils.intArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.Action;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import static org.junit.Assert.*;
 
 
import org.junit.Test;
 
public class Sharktest {
 
       
 
        @Test
        public void Actionstarttest() {
                IFacadePart2 facade = new Facade();
                Shark shark = facade.createShark(0, 0, spriteArrayForSize(3, 3));
                shark.start(Action.JUMP);
                assertEquals(2,(int)shark.getJumpVelocity());
                assertEquals(0,(int)shark.getJumpAcceleration());
                assertEquals(true,shark.getJumping());
                shark.start(Action.MOVELEFT);
                assertEquals(0,(int)shark.getVelocity());
                assertEquals((int)-1.5,(int)shark.getAcceleration());
                shark.start(Action.MOVERIGHT);
                assertEquals(0,(int)shark.getVelocity());
                assertEquals((int)1.5,(int)shark.getAcceleration());
               
        }
        @Test
        public void Actionendtest() {
                IFacadePart2 facade = new Facade();
                Shark shark = facade.createShark(0, 0, spriteArrayForSize(3, 3));
                shark.end(Action.JUMP);
                assertEquals(0,(int)shark.getJumpVelocity());
                assertTrue(shark.getJumpAcceleration()>=-0.2 && shark.getJumpAcceleration()<=0.2);
                assertEquals(false,shark.getJumping());
                shark.end(Action.MOVELEFT);
                assertEquals(0,(int)shark.getVelocity());
                assertEquals((int)0,(int)shark.getAcceleration());
                shark.end(Action.MOVERIGHT);
                assertEquals(0,(int)shark.getVelocity());
                assertEquals((int)0,(int)shark.getAcceleration());
        }
        @Test
        public void periodtest(){
                IFacadePart2 facade = new Facade();
                Shark shark = facade.createShark(0, 0, spriteArrayForSize(3, 3));
                shark.setPeriod(5);
                assertEquals(4,(int)shark.getPeriod());
                shark.setPeriod(3);
                assertEquals(3,(int)shark.getPeriod());
                shark.setPeriod(-1);
                assertEquals(1,(int)shark.getPeriod());
               
        }
}