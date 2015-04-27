package jumpingalien.model;
 
import jumpingalien.util.Sprite;
 
public class Plant extends Entities{
        /**
         * initialize a new plant with the position and the current sprite
         * @param pixelLeftX
         *              the x-coördinate of the plant
         * @param pixelBottomY
         *              the Y-coordinate of the plant
         * @param sprites
         *              the list of sprites of the plant
         * @post
         *              the velocity will be set to 0.5, the variable alternate will be set to 0; the damage will be set to -50,
         *              and so on for every variable you can see
         * @post if the pixelLeftX is in range of 0 and the worldsize in width than the new xcoordinate and XPos is equal to pixelLeftX
         *               if the pixelLeftX is smaller than zero than the xcoordinate and Xpos is equal to zero
         *               if the pixelLeftX is larger than the world size in width than the xcoordinate is equal to the maximum xcoordinate - 1.
         *               the same system occurs for pixelBottomY
         *               |if (pixelLeftX> 0 && pixelLeftX<this.getworldsizeinPixels()[0])
         *               |              then new.getXCord() == pixelLeftX and new.getXPos() = pixelLeftX
         *       |else if (pixelLeftX < 0)
         *       |              then new.getXCord() == 0 and new.getXPos() = 0
         *       |else if (pixelLeftX > this.getWorldSizeInPixels()[1])
         *       |              then new.getXCord() == this.getWorldsizeinPixels()[0]-1 and new.getXPos() = this.getWorldsizeinPixels()[0]-1
         *      
         */
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
        /**
         * perform the action described in the parameter action
         * @param action
         *              the action that will be performed
         * @post
         *               if the action is MOVELEFT, then the velocity will be set to -0.5
         *               if the action is MOVERIGHT, then the velocity will be set to 0.5
         *               | if (action == MOVELEFT)
         *               | then new.getVelocity() == -0.5
         *               | else if (action == MOVERIGHT)
         *               | then new.getVelocity() == 0.5
         *               
         */
        public void start(Action action){
                if (action == Action.MOVELEFT){
                        this.setVelocity(-0.5);
                }
                else if (action == Action.MOVERIGHT){
                        this.setVelocity(0.5);
                }
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
        void advanceTime(double dt){
	        this.setAlternate(this.getAlternate()+dt);
	        if (this.getAlternate()>=0.5)
	        {
                this.setAlternate(this.getAlternate()-0.5);
                if (this.getVelocity()<0){
                        start(Action.MOVERIGHT);
                }
                else{
                        start(Action.MOVELEFT);
                }
	        }
		    this.decideSprite();
		   
		    double x = this.getXPos()+100*dt*this.getVelocity();
		    this.setXPos(x);
		    this.setXCord((int)this.getXPos());
        }
       
       
        @Override
        /**
         * decide the new sprite depending on the velocity
         * @effect if the velocity is smaller than zero than the plant will be facing the left direction aka sprites[0]
         *                 else the plant will be facing the right direction
         *                      |if (this.getVelocity()<0){
                                |this.setSprite(this.getSprites()[0]);)
                                |else{this.setSprite(this.getSprites()[1]);}
                }
         */                    
        void decideSprite() {
                if (this.getVelocity()<0){
                        this.setSprite(this.getSprites()[0]);
                }
                else{
                        this.setSprite(this.getSprites()[1]);
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