package jumpingalien.part1.facade;
 
 
import jumpingalien.util.Sprite;
import jumpingalien.model.Mazub;
 
public class Facade implements IFacade {
 
        @Override
        public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
                Mazub alien = new Mazub(pixelLeftX,pixelBottomY,sprites);
                return alien;
        }
 
        @Override
        public int[] getLocation(Mazub alien) {
                int[] pos= {alien.getXCord() , alien.getYCord()};
                return pos;
        }
 
        @Override
        public double[] getVelocity(Mazub alien) {
                double[] vel = {alien.getVelocity(),alien.getJumpVelocity()};
                return vel;
        }
 
        @Override
        public double[] getAcceleration(Mazub alien) {
                double[] acc = {alien.getAcceleration(),alien.getJumpAcceleration()};
                return acc;
        }
 
        @Override
        public int[] getSize(Mazub alien) {
                        int[] size = {alien.getSizeX(), alien.getSizeY()};
                return size;
        }
 
        @Override
        public Sprite getCurrentSprite(Mazub alien) {
                return alien.getSprite();
        }
 
        @Override
        public void startJump(Mazub alien) {
                alien.startJump();
        }
 
        @Override
        public void endJump(Mazub alien) {
                alien.endJump();              
        }
 
        @Override
        public void startMoveLeft(Mazub alien) {
                alien.startMove(1);
        }
 
        @Override
        public void endMoveLeft(Mazub alien) {
               alien.stopMove(1);      
        }
 
        @Override
        public void startMoveRight(Mazub alien) {
                alien.startMove(2);
        }
 
        @Override
        public void endMoveRight(Mazub alien) {
                        alien.stopMove(2);              
        }
 
        @Override
        public void startDuck(Mazub alien) {
                        alien.startDuck();    
        }
 
        @Override
        public void endDuck(Mazub alien) {
                        alien.endDuck();
        }
 
        @Override
        public void advanceTime(Mazub alien, double dt){
                        alien.advanceTime(dt);              
                }
       
 
}