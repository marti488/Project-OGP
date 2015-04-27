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
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import static org.junit.Assert.*;
 
public class PlantTest {
 
        @Test
        public void startActiontest() {
                IFacadePart2 facade = new Facade();
                Plant plant= facade.createPlant(0, 0, spriteArrayForSize(3, 3));
                plant.start(Action.MOVELEFT);
                assertEquals((int)-0.5,(int)plant.getVelocity());
                plant.start(Action.MOVERIGHT);
                assertEquals((int)-0.5,(int)plant.getVelocity());
        }
       
       
       
 
}