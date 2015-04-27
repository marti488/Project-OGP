package jumpingalien.model;
 
import jumpingalien.util.Sprite;
import java.util.Random;
 
public class Slime extends Entities{
        private int damage;
        private double acceleration,velocity,period;
        private School school;
        /**
         * initialize a new slime with the position(pixelLeftX and pixelBottomY) and the sprite
        @param pixelLeftX
         *              the x-coördinate of the slime
         * @param pixelBottomY
         *              the Y-coordinate of the slime
         * @param sprites
         *              the list of sprites of the slime
         * @post
         *              the velocity will be set to 0,the damage will be set to 50, the accelaration will be set to 0.7,
         *              and so on for every variable you can see
         *@post if the pixelLeftX is in range of 0 and the worldsize in width than the new xcoordinate and XPos is equal to pixelLeftX
         *               if the pixelLeftX is smaller than zero than the xcoordinate and Xpos is equal to zero
         *               if the pixelLeftX is larger than the world size in width than the xcoordinate is equal to the maximum xcoordinate - 1.
         *               the same system occurs for pixelBottomY
         *               |if (pixelLeftX> 0 && pixelLeftX<this.getworldsizeinPixels()[0])
         *               |              then new.getXCord() == pixelLeftX and new.getXPos() = pixelLeftX
         *       |else if (pixelLeftX < 0)
         *       |              then new.getXCord() == 0 and new.getXPos() = 0
         *       |else if (pixelLeftX > this.getWorldSizeInPixels()[1])
         *       |              then new.getXCord() == this.getWorldsizeinPixels()[0]-1 and new.getXPos() = this.getWorldsizeinPixels()[0]-1
         *@note the new period will be equal a random number in between 2 and 6. to realise this we take a random integer
         *              from 0 to 40 and divide it by 10 and then add a 2. this way we're certain that the minimum
         *              is at least 2
         */
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
        /**
         *set a new velocity
         *
         *@param velocity
         *      the velocity that will be set
         *@post if the velocity is smaller than 2.5 and bigger than 2.5, the velocity will be the velocity
         *              if the velocity is bigger than 2.5, it is equal to 2.5
         *              if the velocity is smaller than -2.5, it is equal to -2.5
         *              |if (velocity<2.5 && velocity>-2.5)
         *              | then new.getvelocity() == velocity
         *              |else if (velocity>2.5)
         *              | then new.getvelocity() == 2.5
         *              |else if (velocity <-2.5)
         *              | then new.getvelocity() == -2.5
         */
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
        /**
         *set a new period
         *
         *@param period
         *      the period that will be set
         *@post if the period is in between 2 and 6, the period will be 6
         *               if the period is smaller than 2, it is equal to 2
         *               if the period is bigger than 6, it is equal to 6
         *              |if (period>2 and period<6)
         *              | then new.getperiod() == period
         *              |else if (period<2)
         *              |       then new.getPeriod() == 2
         *              |else (if period>6)
         *              | then new.getPeriod() == 6
         */
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
        /**
         * start the action that is described
         * @param action
         *                      the action that will be executed
         * @post if the action equals JUMP then the jumpaccelaration is set to -10,
         *               if the action equals MOVELEFT, your accelaration will be set to -0.7
         *               if the action equals MOVERIGHT, your accelaration will be set to 0.7
         *               |if (action == Action.JUMP)
         *               | then new.getJumpAcceleration() == -10
         *               |else if (action == Action.MOVELEFT)
         *               | then new.getAcceleration() == -0.7;
         *               |else if (action == Action.MOVERIGHT)
         *               | then new.getAcceleration() == 0.7;
         *               |
         */
        public void start(Action action){
                if (action == Action.MOVELEFT){
                        this.setAcceleration(-0.7);
                }
                else if(action == Action.MOVERIGHT){
                        this.setAcceleration(0.7);
                }
                else if(action == Action.JUMP){
                        this.setJumpAcceleration(-10);
                }
        }
        /**
         * end the action that is described
         * @param action
         *              the action that will be finished
         * @post if the action is equal to MOVELEFT or MOVERIGHT, the velocity will be set to zero
         *               if the action is equal to jump, the jumpvelocity and jumpaccelaration wil be set to 0
         *       |if (action == Action.MOVELEFT||action == Action.MOVERIGHT)
         *       |then new.getVelocity() == 0
         *       |else if (action == Action.JUMP)
         *       |      then new.getVelocity() == 0 and new.getAcceLaration() == 0
         */
        public void end(Action action){
                if (action == Action.MOVELEFT||action == Action.MOVERIGHT){
                        this.setVelocity(0);
                }
                else if(action == Action.JUMP){
                        this.setJumpAcceleration(0);
                        this.setJumpVelocity(0);
                }
        }
        @Override
        public void advanceTime(double dt){
                this.setSnowBallTime(this.getSnowBallTime()+dt);// TODO school
                if (this.getSnowBallTime() >=this.getPeriod()){// TODO collidesside
                        Random rnd = new Random();
                        this.setPeriod(rnd.nextInt(41)/10+2);
                        this.setSnowBallTime(0);
                       
                        if (Math.random()>0.5){
                                if (this.getVelocity()<0){
                                        end(Action.MOVELEFT);
                                        start(Action.MOVERIGHT);
                                }
                                else{
                                        end(Action.MOVERIGHT);
                                        start(Action.MOVELEFT);
                                }
                        }
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
        /**
         * decide the new sprite depending on the velocity
         * @effect if the velocity is smaller than zero than the slime will be facing the left direction aka sprites[0]
         *                 else the plant will be facing the right direction
         *                      |if (this.getVelocity()<0){
         *                      |then new.getSprite() = this.getSprites()[0]
         *                      |else new.getSprite() = this.getSprites()[1]
         */                    
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
		@Override
		public void endJump() {
			// TODO Auto-generated method stub
			
		}
 
}