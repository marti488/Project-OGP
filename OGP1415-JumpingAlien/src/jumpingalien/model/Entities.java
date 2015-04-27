 
        package jumpingalien.model;
         
        import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;
         
        abstract class Entities{    
                protected Sprite sprite;
                protected Sprite[] sprites;
               
                protected double jumpStartAcceleration, jumpStartVelocity;
               
                protected boolean onGround = false, inWater = false, inMagma = false;
               
                protected double currentVelocity = 0, currentAcceleration = 0, currentJumpVelocity = 0,
                        currentJumpAcceleration = 0, second = 0, snowballTime = 0, enviroTime = 0, maxVelocity = 3,
                        startVelocity = 1, jumpstartAcceleration = -10,jumpstartVelocity=8, xPos = 0, yPos = 0;
       
                protected int minX = 0, minY = 0, direction = 0, ducking = 0, xcord = 0, ycord = 0, walkInt = 0,
                                previousDirection = 0, spriteDivider = 0, hitPoints = 100;
               
                protected int xBorder = 1024, yBorder = 768;
               
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
               
                @Basic
                public boolean getInMagma(){
                        return this.inMagma;
                }
               
                public void setInMagma(boolean a){
                        this.inMagma = a;
                }
               
                @Basic
                public double getJumpStartAcceleration(){
                        return this.jumpStartAcceleration;
                }
               
                @Basic
                public double getJumpStartVelocity(){
                        return this.jumpStartVelocity;
                }
               
                protected void setJumpStartVelocity(double a){
                        this.jumpStartVelocity = a;
                }
               
                protected void setJumpStartAcceleration(double a){
                        this.jumpStartAcceleration = a;
                }
               
                @Basic
                public boolean getInWater(){
                        return this.inWater;
                }
               
                public void setInWater(boolean a){
                        this.inWater = a;
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
                public double getEnviroTime(){
                        return this.enviroTime;
                }
               
                public void setEnviroTime(double a){
                        this.enviroTime = a;
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
                public int getXBorder(){
                        return xBorder;
                }
                public void setXBorder(int xborder){
                        this.xBorder = xborder;
                }
                @Basic
                public int getYBorder(){
                        return this.yBorder;
                }
                public void setYBorder(int yborder){
                        this.yBorder = yborder;
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
               
                public void setHitPoints(int hitPoints){
                    this.hitPoints = hitPoints;
                    if (this.getHitPoints() > 500)
                        this.setHitPoints(500);
                    if (this.getHitPoints() < 0)
                        this.setHitPoints(0);
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
                        return this.yPos;
                }
               
                protected void setYPos(double yPos) throws ModelException {
                    if(yPos<0)
                        yPos = 0;
                    if(yPos>=yBorder)
                        yPos = yBorder-1;
                    this.yPos = yPos;
                    setYCord((int)yPos);
                }
               
                @Basic
                public double getXPos(){
                        return this.xPos;
                }
               
                protected void setXPos(double xPos) throws ModelException {
                        if(xPos<0)
                        xPos = 0;
                    if(xPos>=xBorder)
                        xPos = xBorder-1;
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
               
               
                public int inSubstance(World world){
                    int width = getSizeX();
                    int heigth = getSizeY();
                        int posX = this.getXCord();
                        int posY = this.getYCord();
                        for (int x = 0; x < width; x++){
                        if (world.getGeologicalFeature(x + posX, posY) == 2||world.getGeologicalFeature(x + posX, posY+heigth)==2){
                                return 2; //water
                        }
                        if (world.getGeologicalFeature(x + posX, posY) == 3||world.getGeologicalFeature(x + posX, posY+heigth)==3){
                                return 3; //magma
                        }
                        }
                        for (int x = 0; x < width; x++){
                        if (world.getGeologicalFeature(x + posX, posY) == 0||world.getGeologicalFeature(x + posX, posY+heigth)==2){
                                return 0; //air
                        }
                        }
                        return 0; //ground
                    }
               
                public int topSubstance(World world){
                    int height = getSprite().getHeight();
                    int width = getSizeX();
                    int posX = this.getXCord();
                    int posY = this.getYCord();
                    for (int x = 0; x < width; x++){
                        if (world.getGeologicalFeature(x + posX, height + posY) == 2){
                            return 2;//water
                        }
                        if (world.getGeologicalFeature(x + posX, height + posY) == 3){
                            return 3;//magma
                        }
                    }
                    for (int x = 0; x < width; x++){
                        if (world.getGeologicalFeature(x + posX, height + posY) == 0){
                            return 0;//air
                        }
                    }
                    return 1; //ground
                }
               
                public boolean collidesBottom(World world){
                        int width = getSizeX();
                        int posX = this.getXCord();
                        int posY = this.getYCord();
                        for (int x = 1; x < width - 2; x++){
                                if (world.getGeologicalFeature(x + posX, posY) == 1){
                                        return true;
                                }
                        }
                        return false;
                }
                public void xcorrection(World world){
                        while (!this.collidesSide(world)){
                                if (this.getVelocity()<0){
                                        this.setXPos(this.getXPos()-1);
                                }
                                else{
                                        this.setXPos(this.getXPos()+1);
                                }
                        }
                }
               
                public boolean collidesTop(World world, Sprite sprite){
                        int height = sprite.getHeight();
                        int width = getSizeX();
                        int posX = this.getXCord();
                        int posY = this.getYCord();
                        for (int x = 1; x < width-2; x++){
                                if (world.getGeologicalFeature(x + posX, posY + height - 1) == 1){
                                        return true;
                                }
                        }
                        return false;
                }
               
                public boolean collidesSide(World world){
                        int posX = this.getXCord();
                        int posY = this.getYCord();
                        if (this.getAcceleration() > 0)
                                posX = posX + getSizeX() - 1;
                        int heigth = getSizeY();
                        for (int y = 1; y < heigth - 2; y++){
                                if (world.getGeologicalFeature(posX, y + posY) == 1){
                                        return true;
                                }
                        }
                        return false;
                }
                public boolean collidesbothsides(World world){
                        int posX = this.getXCord();
                    int posY = this.getYCord();
                    int posX2 = posX + getSizeX();
                    int posY2 = posX + getSizeY();
                    for (int i = 1;i<=getSizeY();i++){
                        if (world.getGeologicalFeature(posX, posY+i)==1||world.getGeologicalFeature(posX2, posY+i)==1){
                                return true;
                        }
                               
                    }
                    return false;
                }
                public boolean collideTopAndBottom(World world){
                        return (this.collidesBottom(world) && this.collidesTop(world, this.getSprite()));                      
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
               
                public abstract void endJump();
               
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