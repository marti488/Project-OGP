 
        package jumpingalien.model;
         
        import jumpingalien.util.ModelException;
        import jumpingalien.util.Sprite;
        import be.kuleuven.cs.som.annotate.*;
         
        abstract class Entities{    
                protected Sprite sprite;
                protected Sprite[] sprites;
                
                protected boolean onGround = false;
               
                protected double currentVelocity = 0, currentAcceleration = 0, currentJumpVelocity = 0,
                        currentJumpAcceleration = 0, second = 0, snowballTime = 0, maxVelocity = 3,startVelocity = 1,
                        jumpstartAcceleration = -10,jumpstartVelocity=8, xPos = 0, yPos = 0;
       
                protected int minX = 0, minY = 0, direction = 0, ducking = 0, xcord = 0, ycord = 0, walkInt = 0,
                                previousDirection = 0, spriteDivider = 0, hitPoints = 100;
               
                protected static int xBorder = 1024, yBorder = 768;
                
                public Entities(int pixelLeftX,int pixelBottomY,Sprite[] sprites){
                	try{this.setXCord(pixelLeftX);}
                   		catch(ModelException exc){
                   			if (pixelLeftX <0)
                   				this.setXCord(this.getminX());
                   			else
		                      	this.setXCord((int)xBorder-1);
                   		}
                	
                	try{this.setYCord(pixelBottomY);}
		              	catch(ModelException exc){
		              		if (pixelBottomY <0)
		              			this.setYCord(this.getminY());
		              		else
		                       	this.setYCord((int)yBorder-1);
		            	}
                	this.setSprite(sprites[0]);
                	this.setSprites(sprites);
                }
                
                protected void setPos(double x, double y){
                	this.setXPos(x);
                	this.setYPos(y);
                	this.setPosition((int)x, (int)y);
                }
                
                protected void setPosition(int x,int y){
                    this.setXCord(x);
                    this.setYCord(y);         
                }
                public int[] getPosition(){
                        int[] position = {this.getXCord(),this.getYCord()};
                        return position;
                }
               
                protected void setAcceleration(double a){
                        this.currentAcceleration = a;          
                }
               
                protected void setVelocity(double a){
                        this.currentVelocity = a;              
                }
               @Basic
                public double getAcceleration(){
                        return this.currentAcceleration;
                }
               @Basic
                public double getVelocity(){
                        return this.currentVelocity;
                }
               @Basic
                public Sprite getSprite() {
                        return this.sprite;
                }
               
                protected void setSprite(Sprite a) {
                        this.sprite = a;
                }
                @Basic
                public int getSizeX(){
                        return this.getSprite().getWidth();
                }
                @Basic
                public int getSizeY(){
                        return this.getSprite().getHeight();
                }
                @Basic
                public static double getXBorder(){
                        return xBorder;
                }
                @Basic
                public static double getYBorder(){
                        return yBorder;
                }
                @Basic
                public int getminY(){
                        return this.minY;
                }
               
                protected void setminY(int minY){
                        this.minY = minY;
                }
                @Basic
                public int getminX(){
                        return this.minX;
                }
               
                protected void setminX(int minX){
                        this.minX = minX;
                }
                @Basic
                public double getStartVelocity(){
                        return this.startVelocity;
                };
                @Basic
                public double getBaseAcceleration(){
                        return this.currentAcceleration;
                };
                @Basic
                public double getJumpStartAcceleration(){
                        return this.jumpstartAcceleration;
                };
                @Basic
                public double getJumpStartVelocity(){
                        return this.jumpstartVelocity;                 
                };
               
                protected void setSnowBallTime(double a){
                        this.snowballTime = a;
                }
                @Basic
                public double getSnowBallTime(){
                        return this.snowballTime;
                }
               
                protected void setSprites(Sprite[] a){
                        this.sprites = a;
                }
                @Basic
                public Sprite[] getSprites(){
                        return this.sprites;
                }
                @Basic
                public double getMaxVelocity(){
                                return this.maxVelocity;
                }
               
                protected void setMaxVelocity(double newMaxVelocity){
                                this.maxVelocity = newMaxVelocity;
                }
                @Basic
                public int getWalkInt(){
                        return this.walkInt;
                }
               
                protected void setWalkInt(int a){
                        this.walkInt = a;
                }      
                @Basic
                public int getDirection(){
                        return this.direction;
                }
               
                protected void setDirection(int direction){
                        this.direction = direction;
                }
                @Basic
                public int getXCord(){
                        return this.xcord;
                }
               
                @Basic
                public int getPreviousDirection(){
                        return this.previousDirection;
                }
               
                protected void setPreviousDirection(int a){
                        this.previousDirection = a;
                }
               
                protected void setHitPoints(int hitPoints){
                                this.hitPoints = hitPoints;
                }
               
                public int getHitPoints(){
                        return this.hitPoints;
                }
                   
                protected void setXCord(int xcord){
                        this.xcord=xcord;
                }
                   
                @Basic
                public int getYCord(){
                        return this.ycord;
                }
               
                protected void setYCord(int ycord){
                        this.ycord = ycord;
                }
                
                @Basic
                public double getYPos(){
                        return this.ycord;
                }
               
                protected void setYPos(double yPos) throws ModelException {
                        if(yPos>=yBorder||yPos<0)
                                throw new ModelException("invalid Ypos");
                        this.yPos = yPos;
                        setYCord((int)yPos);
                }
                
                @Basic
                public double getXPos(){
                        return this.ycord;
                }
               
                protected void setXPos(double xPos) throws ModelException {
                        if(xPos>=xBorder||xPos<0)
                                throw new ModelException("invalid Xpos");
                        this.xPos = xPos;
                        setXCord((int)xPos);
                }
               
                protected void setSecond(double second){
                        this.second = second;
                }
               
                @Basic
                public double getSecond(){
                        return this.second;
                }
                   
                protected void setJumpVelocity(double a){
                    this.currentJumpVelocity = a;
                }
               
                @Basic
                public double getJumpVelocity(){
                        return this.currentJumpVelocity;
                }
               
                @Basic
                public double getJumpAcceleration(){
                        return this.currentJumpAcceleration;
                }
                   
                protected void setJumpAcceleration(double a){
                        this.currentJumpAcceleration = a;
                }
                
                protected void setOnGround(boolean a){
                	this.onGround = a;
                }
                
                @Basic
                public boolean getOnGround(){
                	return this.onGround;
                }
                
                public boolean collidesBottom(World world){
                	int width = getSizeX();
                	int posX = this.getXCord();
                	int posY = this.getYCord();
                	for (int x = 0; x < width; x++){
                		if (world.getGeologicalFeature(x + posX, posY) == 1){
                			return true;
                		}
                	}
                	return false; 
                }
                
                public boolean collidesTop(World world, Sprite sprite){
                	int height = sprite.getHeight();
                	int width = getSizeX();
                	int posX = this.getXCord();
                	int posY = this.getYCord();
                	for (int x = 0; x < width; x++){
                		if (world.getGeologicalFeature(x + posX, height + posY) == 1){
                			return true;
                		}
                	}
                	return false;
                }
                
                public boolean collidesSide(World world){
                	int posX = this.getXCord();
                	int posY = this.getYCord();
                	if (this.getDirection() == 1)
                		posX = posX + getSizeX();
                	int heigth = getSizeY();
                	for (int y = 0; y < heigth; y++){
                		if (world.getGeologicalFeature(posX, y + posY) == 1){
                			return true;
                		}
                	}
                	return false;
                }
                
                abstract void advanceTime(double dt);
                   
                abstract void decideSprite();
               
                /**
                 *
                 * @param direction
                 * @pre   the value direction can only be equal to 0,1 or 2
                 *
                 * @post  the new velocity is equal to this.startVelocity
                 *                |new.getVelocity() == this.startVelocity
                 * @post  the new acceleration is equal to this.baseAcceleration
                 *                |new.getAcceleration() == this.getBaseAcceleration()
                 * @post  the variable this.second has been reset to 0
                 *                |new.getSecond() == 0
                 * @post  the new direction is equal to the given direction
                 *                |new.getDirection() == direction
                 */
                public void startMove(int direction){
                    assert (direction == 1||direction == 2||direction == 0);
                    this.setVelocity(this.getStartVelocity());
                    this.setAcceleration(this.getBaseAcceleration());
                    this.setSecond(0);
                    this.setDirection(direction);
                    this.setSnowBallTime(0);
                    this.decideSprite();
                }
               
                abstract void startJump();
               
                /**
                 
                 * @post   the new jumpVelocity is equal to 0
                 *                 |new.getJumpVelocity() == 0
                 */
                public void endJump(){
                        if (this.getJumpVelocity() > 0){
                                this.setJumpVelocity(0);}
                }
               
                /**
                 *
                 * @param direction
                 * @pre   the direction is equal to either 1,2 or 0
                 *                |(direction ==0 || direction == 1 || direction == 2)
                 * @post  the new velocity is set to zero if the given direction is equal to the current direction
                 *                |if (this.getDirection() == direction) then
                 *                |             new.getVelocity() == 0
                 * @post  the new acceleration is set to 0 if the given direction is equal to the current direction
                 *                |if (this.getDirection() == direction) then
                 *                |             new.getAcceleration() == 0
                 * @post  the new second is set to 1 if the given direction is equal to the current direction
                 *                |if (this.getDirection() == direction) then
                 *                |             new.getSecond() == 1
                 * @note  normally we would use the code assert(direction !=0 && direction != 1 && direction != 2)
                 *                but for some reason this would not function on our computer. this is why
                 *                we used an AssertionError to substitute 'assert'. Consider this as an assert.
                 *
                 */
                public void stopMove(int direction) throws AssertionError{
                    if (direction !=0 && direction != 1 && direction != 2)
                        throw new AssertionError(direction);
                        if (this.getDirection() == direction){
                                this.setVelocity(0);
                                this.setAcceleration(0);
                                this.setSecond(1);}
                }
         
        }