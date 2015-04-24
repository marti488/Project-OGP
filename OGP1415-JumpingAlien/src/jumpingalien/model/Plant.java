package jumpingalien.model;
 
import jumpingalien.util.Sprite;
 
public class Plant extends Entities{
        public Plant(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
                super(pixelLeftX, pixelBottomY, sprites);
                this.setVelocity(0.5);
                this.setAlternate(0);
                this.setDamage(-50);
                this.setMovement(0);
                this.setHitPoints(1);
                this.setXPos(pixelLeftX);
                this.setYPos(pixelBottomY);
               
        }
       
        private int damage,movement;
        private double alternate;              
       
        public void setDamage(int damage){
                this.damage = damage;
        }
        public int getDamage(){
                return this.damage;
        }
        public void setMovement(int movement){
                this.movement = movement;
        }
        public int getMovement(){
                return this.movement;
        }
 
 
 
       
        public void setAlternate(double Alternate){
                this.alternate = Alternate;
        }
        public double getAlternate(){
                return this.alternate;
        }
       
        @Override
        public void advanceTime(double dt, World world){
        this.setAlternate(this.getAlternate()+dt);
        if (this.getAlternate()>=0.5)
        {
                this.setAlternate(this.getAlternate()-0.5);
                this.setVelocity(this.getVelocity()*(-1));
                this.setMovement(Math.abs(this.getMovement()-1));
                this.decideSprite();           
        }
       
        double x = this.getXPos()+100*dt*this.getVelocity();
        this.setXPos(x);
        this.setXCord((int)this.getXPos());
        }
       
       
        @Override
        void decideSprite() {
                this.setSprite(this.getSprites()[this.getMovement()]);
               
        }
		@Override
		void startJump() {
			// TODO Auto-generated method stub
			
		}
       
}