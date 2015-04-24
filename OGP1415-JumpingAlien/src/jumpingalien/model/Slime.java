package jumpingalien.model;
 
import jumpingalien.util.Sprite;
import java.util.Random;
 
public class Slime extends Entities{
        private int damage;
        private double acceleration,velocity,period;
        private School school;
 
        public Slime(int pixelLeftX, int pixelBottomY, Sprite[] sprites,School school) {
                super(pixelLeftX, pixelBottomY, sprites);
                Random rnd = new Random();
                this.setPeriod(rnd.nextInt(41)/10+2);
                this.setSnowBallTime(0);
                this.setdamage(50);
                this.setHitPoints(100);
                this.setAcceleration(0.7);     
                this.setVelocity(0);
                this.setJumpAcceleration(0);
                this.setXPos(pixelLeftX);
                this.setYPos(pixelBottomY);
                this.setSchool(school);
        }
        public void setSchool(School school){
                this.school = school;
        }
        public School getSchool(){
                return this.school;
        }
        public void removeslime(){
                this.getSchool().getslimes().remove(this);
        }
       
        public void setAcceleration(double acceleration){
                this.acceleration = acceleration;
        }
        public double getAcceleration(){
                return this.acceleration;
        }
        @Override
        public void setVelocity(double velocity){
                if(velocity >2.5){
                        velocity = 2.5;
                }
                else if(velocity<-2.5){
                        velocity = -2.5;
                }
                this.velocity = velocity;
        }
        public double getvelocity(){
                return this.velocity;
        }
        public void setPeriod(double period){
                if (period>6){
                        period = 6;
                }
                if (period<2){
                        period = 2;
                }
                this.period = period;
        }
        public double getPeriod(){
                return this.period;
        }
        public void setdamage(int damage){
                this.damage= damage;
        }
        public int getdamage(){
                return this.damage;
        }
       
        @Override
        public void advanceTime(double dt,World world){
                this.setSnowBallTime(this.getSnowBallTime()+dt);// TODO school
                if (this.getSnowBallTime() >=this.getPeriod()){// TODO collidesside
                        Random rnd = new Random();
                        this.setPeriod(rnd.nextInt(41)/10+2);
                        this.setSnowBallTime(0);
                       
                        if (Math.random()>0.5){
                                this.setVelocity(this.getVelocity()*-1);
                                this.setAcceleration(this.getAcceleration()*-1);
                        }
                }
                if (!this.collidesBottom(world)){
                        this.setJumpAcceleration(-10);
                }
                else{
                        this.setJumpAcceleration(0);
                        this.setJumpVelocity(0);
                }
                double x =  this.getXPos()+100*(this.getvelocity()*dt+this.getAcceleration()*dt*dt/2);
                this.setVelocity(this.getvelocity()+this.getAcceleration()*dt);
                double y =  this.getYPos()+100*(this.getJumpVelocity()*dt+this.getJumpAcceleration()*dt*dt/2);
                this.setJumpVelocity(this.getJumpVelocity()+this.getJumpAcceleration()*dt);
                setYPos(y);
                setXPos(x);
                setYCord((int)getYPos());
                setXCord((int)getXPos());
                decideSprite();
               
        }
        @Override
        public void decideSprite() {
                if (this.getvelocity() <0){
                        setSprite(this.getSprites()[0]);
                }
                else{
                        setSprite(this.getSprites()[1]);
                }
               
        }
		@Override
		void startJump() {
			// TODO Auto-generated method stub
			
		}
 
}