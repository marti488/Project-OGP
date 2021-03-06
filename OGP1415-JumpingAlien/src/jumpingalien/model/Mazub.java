package jumpingalien.model;
 
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;
 
public class Mazub extends Entities{
       
        private final double startVelocity, baseacceleration, maxVelocityStanding, maxVelocityDucking;
        private boolean isImmune;
        /**
         *
         * @param pixelLeftX
         *                the starting x-coordinate of mazub
         * @param pixelBottomY
         *                the starting x-coordinate of mazub
         * @param sprites
         *                an array containing all possible sprites mazub can have
         * @post  the sprite of this mazub is equal to the first sprite
         *                |new.getSprite() == sprites[0]
         * @post  the x-coordinate of this mazub is equal to the given pixelLeftX      
         *                |new.getXCord() == pixelLeftX
         * @post  the y-coordinate of this mazub is equal to the given pixelBottomY
         *                |new.getYCord() == pixelBottomY
         * @post  if the y-coordinate is not equal to the minimum y-coordinate, then we immediately
         *                set the jumpAcceleration to the jumpStartAcceleration
         *                                |if (new.getYCord() != this.getMinY())
         *                                |              new.getJumpAcceleration == this.getJumpStartAcceleration()
         * @throws ModelException
         *                 the pixelLeftX or/and pixelBottomY-coordinate is/are smaller than this.getMinX()/this.getMinY()
         *                 or bigger then the biggest possible X or Y-coordinate
         *                |(new.getYCord()<this.getMinY()||new.getYcord()>=new.getYBorder()||
         *                |             new.getXCord()<this.getMinX()||new.getXcord()>=this.getYBorder())
         *
         */
        public Mazub (int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws ModelException{
               
                        super(pixelLeftX, pixelBottomY, sprites);
               
                currentVelocity = 0; currentAcceleration = 0; currentJumpVelocity = 0;
                currentJumpAcceleration = 0; second = 0; snowballTime = 0; maxVelocity = 3;
               
                minX = 0; minY = 0; direction = 0; ducking = 0; walkInt = 0;
                previousDirection = 0; spriteDivider = 0; hitPoints = 100;
               
                this.startVelocity = 1;
                this.maxVelocityStanding = 3;
                this.maxVelocityDucking = 1;
                this.baseacceleration = 0.9;
                this.jumpStartAcceleration = -10;
                this.jumpStartVelocity = 8;
                if ((sprites.length)% 2 == 0 && (sprites.length) > 10){
                        this.setSprites(sprites);
                        spriteDivider = ((sprites.length)-7)/2;
                }
                else throw new ModelException("invalid sprite");
                this.setSprite(sprites[0]);
                this.setVelocity(0);
                this.setAcceleration(0);
                this.setMaxVelocity(maxVelocityStanding);
                System.out.println(pixelLeftX);
                if (pixelBottomY != this.getminY())
                        this.setJumpAcceleration(this.jumpStartAcceleration);
        }
        
        @Basic
        public boolean getIsImmune(){
        	return this.isImmune;
        }
        
        public void setIsImmune(boolean a){
        	this.isImmune = a;
        }
       
        @Basic
        public double getMaxVelocityStanding(){
                return this.maxVelocityStanding;
        }
        @Basic
        public double getMaxVelocityDucking(){
                    return this.maxVelocityDucking;
        }
       
        @Basic
        public double getStartVelocity(){
                return this.startVelocity;
        }
       
        @Basic
        public double getBaseAcceleration(){
                return this.baseacceleration;
        }
       
        @Basic
        public int getDucking(){
            return this.ducking;
        }
       
        public void setDucking(int a){
                this.ducking = a;
        }
       
       /**
        * @post the this.ducking variable is now set to 1
        *               |new.getDucking() == 1
        * @post the this.maxvelocity variable is set to the maximum velocity while ducking
        *               |new.getMaxVelocity() == this.getMaxVelocityDucking()
        */
        public void startDuck(){
            this.setDucking(1);
            this.setMaxVelocity(this.getMaxVelocityDucking());
        }
        
       /**
        * @post the this.ducking variable is now set to 2. This value will be interpreted in the next
        * 				advanceTime, which will check if the environment allows standing up.
        *               |new.getDucking() == 2
        */
        public void endDuck(){
              this.setDucking(2);
        }
       
        /**
        *
        * @throws ModelException
        * 				  if boolean onGround is false then Mazub is not
        *                 standing on solid ground, and thus cannot jump.
        *                 |(!onGround)
        *@post    the new jumpVelocity is equal to the start velocity
        *                 |(new.getCurrentVelocity() == this.getJumpStartVelocity)
        *@post    the new jumpAcceleration is equal to the startAcceleration
        *                 |new.getJumpAcceleration() == this.getStartJumpVelocity()
        */
        public void startJump() throws ModelException{
           if (!onGround){
                   throw new ModelException("Mazub cannot jump now.");
           }
           this.setJumpVelocity(this.getJumpStartVelocity());
           this.setJumpAcceleration(this.getJumpStartAcceleration());
           this.setSnowBallTime(0);
           this.decideSprite();
        }
       
       /**
        * @post   the new jumpVelocity is equal to 0
        *                 |new.getJumpVelocity() == 0
        */
       	public void endJump(){
           	if (this.getJumpVelocity() > 0){
                   this.setJumpVelocity(0);
           	}
       	}
          
    	public void advanceTime(double dt){
            newLocationAndVelocity(dt);
 
            this.setSnowBallTime(this.getSnowBallTime()+dt);
            this.seconddeduction(dt);
            if (this.getSnowBallTime() >= 0.075){            
                    this.decideSprite();
                    this.setSnowBallTime(this.getSnowBallTime()-0.075);}
        }
       
            /**
             *
             * @param dt
             * @post if the current velocity equals zero then the new second is equal to the current second
             *           minus dt
             *           |if (this.getVelocity() == 0)
             *           |new.getSecond() == this.getSecond() - dt
             */
            public void seconddeduction(double dt){
                 if (this.getVelocity() == 0)
                     this.setSecond(this.getSecond()-dt);      
            }
         
            /**
             *
             * @param time
             *
             * @post             If the value x is in range of 0 and this.getxborder(),
             *                       then the new x-coordinate is equal to x
             *                           |if (x>0 && x<this.getXBorder())
             *                           |      then new.getXCord() == x
             * @post             If the value y is in range of 0 and this.getyborder(),
             *                       then the new y-coordinate is equal to y
             *                           |if (y>0 && y<this.getyBorder())
             *                           |      then new.getyCord() == y
             * @post             if the value x is smaller than this.getMinX(), then the new x-coordinate is equal to this.getMinX()
             *               |if (x<this.getMinX())
             *               |  then new.getXCord()==this.getMinX()
             * @post             if the value x is bigger or equal to than this.getXBorder(),then the new x-coordinate is equal to this.getXborder()-1
             *               |if (x>=this.getXBorder())
             *               |  then new.getXCord()==this.getXBorder()-1
             * @post         if the value y is smaller than this.getMinY(), then the new y-coordinate is equal to this.getMinY()
             *                           and the JumpVelocity is set to zero  
             *                           |if (y<this.getMinY())
             *               |  then new.getYCord == this.getMinY() && new.getJumpVelocity == 0
             * @post             if the value y is bigger or equal to than this.getYBorder(),then the new y-coordinate is equal to this.getYborder()-1
             *               |if (y>=this.getYBorder())
             *               |  then new.getYCord() == new.getYBorder()-1
             * @post             if the value vel is bigger than this.getMaxvelocity() then the new velocity is equal to this.getMaxVelocity()
             *                           if not the velocity will be equal to the value vel
             *               |if (vel>this.getMaxVelocity()) then new.currentVelocity == this.getMaxVelocity()
             *                           |if (vel <= this.getMaxVelocity()) then new.currentVelocity == vel
             */
           public void newLocationAndVelocity(double time){
           int dir = this.getDirection();
           double vel = this.getVelocity();
           double acc = this.getAcceleration();
           if (dir == 1){
                   vel = -vel;                  
                   acc = -acc;}
           double x = (this.getXPos()+ 100*(vel * time + acc*time*time/2));
           vel = (this.getVelocity()+this.getAcceleration()*time);
           
           double y = (this.getYPos()+ 100*(this.getJumpVelocity() * time + this.getJumpAcceleration()*time*time/2));
           this.setJumpVelocity(this.getJumpVelocity()+this.getJumpAcceleration()*time);
           
           if (vel > this.getMaxVelocity())
               this.setVelocity(this.getMaxVelocity());
           else
               this.setVelocity(vel);
           
           if (!this.getOnGround() && this.getJumpVelocity() == 0 && this.getJumpAcceleration() == 0){
                   this.setJumpAcceleration(this.getJumpStartAcceleration());
           }
           this.setPos(x,y);
           }
           
           
        public void decideSprite(){
                if (this.getVelocity() == 0){
                        if (this.getJumpVelocity() == 0){
                                if (this.getDucking() != 0){
                                        if (this.getSecond() > 0){
                                                if (this.getDirection() == 1)
                                                        this.setSprite(this.getSprites()[7]);
                                                else
                                                        this.setSprite(this.getSprites()[6]);
                                        }
                                        else
                                                this.setSprite(this.getSprites()[1]);
                                }
                                else{
                                        if (this.getSecond() > 0){
                                                if (this.getDirection() == 1)
                                                        this.setSprite(this.getSprites()[3]);
                                                else
                                                        this.setSprite(this.getSprites()[2]);
                                        }
                                        else
                                                this.setSprite(this.getSprites()[0]);
                                }
                        }
                        else{
                                if (this.getSecond() > 0){
                                        if (this.getDirection() == 1)
                                                this.setSprite(this.getSprites()[5]);
                                        else
                                                this.setSprite(this.getSprites()[4]);
                                }
                                else
                                        this.setSprite(this.getSprites()[0]);
                        }
                }
                else if (this.getJumpVelocity() != 0){
                        if (this.getDirection() == 1){
                                this.setSprite(this.getSprites()[5]);
                        }
                        else
                                this.setSprite(this.getSprites()[4]);
                }
                else if (this.getDucking() != 0){
                        if (this.getDirection() == 1)
                                this.setSprite(this.getSprites()[7]);
                        else
                                this.setSprite(this.getSprites()[6]);
                }
                else{
                        if (this.getWalkInt() == (8 + 2*spriteDivider) || this.getWalkInt() == (8 + spriteDivider) || this.getDirection() != this.getPreviousDirection())
                                this.setWalkInt(0);
                        if (this.getWalkInt() == 0){
                                if (this.getDirection() == 1)
                                        this.setWalkInt(8 + spriteDivider);
                                else
                                        this.setWalkInt(8);
                        }
                        this.setSprite(this.getSprites()[this.getWalkInt()]);
                        this.setWalkInt(this.getWalkInt() + 1);
                        this.setPreviousDirection(this.getDirection());
                }
        }
        @Override
        public boolean collidesSide(World world){
            int posX = this.getXCord();
            int posY = this.getYCord();
            if (this.getDirection() == 2)
                    posX = posX + getSizeX() - 1;//TODO: werkt dees?
            int heigth = getSizeY();
            for (int y = 1; y < heigth-1; y++){
                    if (world.getGeologicalFeature(posX, y + posY) == 1){
                            return true;
                    }
            }
            return false;
    }
}