package jumpingalien.model;
 
import jumpingalien.util.Sprite;
 
 
 
 
 
import java.util.Random;
public class Shark extends Entities {
       
                private final double baseAcceleration;
               
        private int damage;
        private double acceleration,velocity,period,damagetime = 0;
       
        public int timeTillJump = 0;
        public boolean abovewater = false, jumping = false, falling = false;
       
        /**
         * initialize a new shark with the position(pixelLeftX and pixelBottomY) and the sprite
        @param pixelLeftX
         *              the x-coördinate of the shark
         * @param pixelBottomY
         *              the Y-coordinate of the shark
         * @param sprites
         *              the list of sprites of the shark
         * @post
         *              the velocity will be set to 0,the damage will be set to 50, the accelaration will be set to 1.5,
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
         * @note the new period will be equal a random number in between 1 and 4. to realise this we take a random integer
         *              from 0 to 30 and divide it by 10 and then add a 1. this way we're certain that the minimum
         *              is at least 1
         */
        public Shark(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {    
                super(pixelLeftX, pixelBottomY, sprites);
                Random rnd =new Random();
                this.setVelocity(0);
                this.baseAcceleration = 1.5;
                this.setAcceleration(this.baseAcceleration);
                this.setDamage(50);
                this.setHitPoints(100);
                this.setPeriod((double)rnd.nextInt(31)/10 + 1);
                this.setSnowBallTime(0);
                this.setJumpAcceleration((double)rnd.nextInt(41)/100 - 0.2);
                this.setJumpVelocity(0);
                this.setXPos(pixelLeftX);
                this.setYPos(pixelBottomY);
                this.setMaxVelocity(4);
               
                this.setJumpStartAcceleration(-10);
                this.setJumpStartVelocity(2);
        }
       
        /**
         * start the action that is described
         * @param action
         *                      the action that will be executed
         * @post if the action equals JUMP then the floatingvelocity is equal to the jumpvelocity,
         *               the floatingaccelaration is set to 0 and the variabele jumping is equal to 1
         *               if the action equals MOVELEFT, then your velocity will be set to zero and you accelaration
         *                      will be set to -1.5
         *               if the action equals MOVERIGHT, then your velocity will be set to zero and you accelaration
         *                      will be set to 1.5
         *               |if (action == Action.JUMP)
         *               | then new.getfloatingvel() == this.getJumpVelocity(), new.getfloatingacc() == 0 and new.getJumping() == 1
         *               |else if (action == Action.MOVELEFT)
         *               | then new.getVelocity() == 0 and new.getAcceleration() == -1.5;
         *               |else if (action == Action.MOVERIGHT)
         *               | then new.getVelocity() == 0 and new.getAcceleration() == 1.5;
         *               |
         */
        public void start(Action action){
                if (action == Action.JUMP){
                        startJump();
                }
                else if (action == Action.MOVELEFT){
                        this.setAcceleration(-1.5);
                }
                else if (action == Action.MOVERIGHT){
                        this.setAcceleration(1.5);
                }
               
        }
        /**
         * end the action that is described
         * @param action
         *                      the action that will be executed
         *@post if the action equals JUMP then the floatingvelocity is set to 0,
         *               the floatingaccelaration is set to a random accelaration in between -0.2 and 0.2 and the variabele jumping is equal to 0
         *               if the action equals MOVELEFT or MOVERIGHT, then your velocity will be set to zero and you accelaration
         *                      will be set to 0
         *               |if (action == Action.JUMP)
         *               | then new.getfloatingvel() == 0, new.getfloatingacc() == rnd.nextInt(41)/100 - 0.2 and new.getJumping() == 0
         *               |else if (action == Action.MOVELEFT||action == Action.MOVERIGHT)
         *               | then new.getVelocity() == 0 and new.getAcceleration() == -1.5;
         */
        public void end(Action action){
                if (action == Action.JUMP){
                        endJump();                    
                }
                else if (action == Action.MOVELEFT||action == Action.MOVERIGHT){
                        this.setVelocity(0);
                        this.setAcceleration(0);
                }
               
        }
       
            public void setFalling(boolean a){
            this.falling = a;
            }
            public boolean getFalling(){
                    return this.falling;
            }
        public void setJumping(boolean a){
            this.jumping = a;
            this.setJumpVelocity(this.getJumpStartVelocity());
            }
            public boolean getJumping(){
                    return this.jumping;
            }
        public void setTimeTillJump(int a){
            this.timeTillJump = a;
            if (this.getTimeTillJump() < 0)
                this.setTimeTillJump(0);
            }
            public int getTimeTillJump(){
                    return this.timeTillJump;
            }
        public void setDamage(int damage){
                this.damage = damage;
        }
        public int getDamage(){
                return this.damage;
        }
        public void setDamageTime(double damage){
                this.damagetime = damage;
        }
        public double getDamageTime(){
                return this.damagetime;
        }
        
        public void setabovewater(boolean abovewater){
                this.abovewater = abovewater;
        }
        public boolean getabovewater(){
                return this.abovewater;
        }
        /**
         *set a new period
         *
         *@param period
         *      the period that will be set
         *@post if the period is in between 1 and 4, the period will be 4
         *               if the period is smaller than 1, it is equal to 1
         *               if the period is bigger than 4, it is equal to 4
         *              |if (period>1 and period<4)
         *              | then new.getperiod() == period
         *              |else if (period<1)
         *              |       then new.getPeriod() == 1
         *              |else (if period>4)
         *              | then new.getPeriod() == 4
         */
        public void setPeriod(double period){
                if (period>4){
                        period = 4;
                }
                if (period<1){
                        period = 1;
                }
                this.period = period;
        }
        public double getPeriod(){
                return this.period;
        }
       
        /**
         *set a new velocity
         *
         *@param velocity
         *      the velocity that will be set
         *@post if the velocity is in between -4 and 4, the velocity will be velocity
         *              if the velocity is bigger than 4, it is equal to 4
         *              if the velocity is smaller than -4, it is equal to -4
         *              |if (velocity>-4 and velocity<4)
         *              | then new.getvelocity() == velocity
         *              |else if (velocity>4)
         *              | then new.getvelocity() == 4
         *              |else if(velocity<-4)
         *              | then new.getvelocity() == -4
         */
        @Override
        public void setVelocity(double velocity){
                if (velocity>4){
                        velocity = 4;
                }
                else if(velocity<-4)
                        velocity = -4;
                this.velocity = velocity;
        }
               
        public double getVelocity(){
                return this.velocity;
        }
        public void setAcceleration(double acceleration){
                this.acceleration = acceleration;
        }
        public double getAcceleration(){
                return this.acceleration;
        }
        /**
         * decide the new sprite depending on the velocity
         * @effect if the velocity is smaller than zero than the plant will be facing the left direction aka sprites[0]
         *                 else the plant will be facing the right direction
         *                      |if (this.getVelocity()<0){
         *                      |then new.getSprite() = this.getSprites()[0]
         *                      |else new.getSprite() = this.getSprites()[1]
         */                    
        @Override
        void decideSprite() {
                if (getVelocity() <0){
                        setSprite(this.getSprites()[0]);
                }
                else{
                        setSprite(this.getSprites()[1]);
                }
            }
       
       
        @Override
        void advanceTime(double time) {
               
                double vel = this.getVelocity();
                double acc = this.getAcceleration();
                double jvel = this.getJumpVelocity();
                double jacc = this.getJumpAcceleration();
               
                double x = (this.getXPos()+ 100*(vel * time + acc*time*time/2));
                   
                vel = (this.getVelocity() + this.getAcceleration()*time);
                   
                this.decideSprite();
                   
                double y =(this.getYPos()+ 100*(jvel * time + jacc*time*time/2));
                this.setJumpVelocity(jvel + jacc*time);
               
                if (Math.abs(vel) > this.getMaxVelocity())
                    this.setVelocity(this.getMaxVelocity());
                else
                    this.setVelocity(vel);
               
                this.setPos(x,y);
               
            this.setSnowBallTime(this.getSnowBallTime() + time);
           
        }
       
        @Override
        public boolean collidesSide(World world){
            int posX = this.getXCord();
                int posY = this.getYCord();
                if (this.getVelocity()>0)
                posX = posX + getSizeX();
                int heigth = getSizeY();
                for (int y = 1; y < heigth-1; y++){
                if (world.getGeologicalFeature(posX, y + posY) == 1){
                    return true;
                }
                }
                return false;
        }
       
                @Override
                public void startJump(){
                this.setJumping(true);
	            this.setJumpVelocity(this.getJumpStartVelocity());
	            this.decideSprite();
        }
               
                public void endJump(){
	                this.setJumping(false);
	                if (this.getJumpVelocity() > 0){
	                	this.setJumpVelocity(0);	
            }
            this.decideSprite();
        }
}