package jumpingalien.model;
 
import java.util.ArrayList;
import java.util.Random;
 
import jumpingalien.util.ModelException;
 
public class World {
        private Mazub mazub;
        private int tileSize,nbTilesX,nbTilesY, visibleWindowWidth,visibleWindowHeight,targetTileX,targetTileY;
        private double timeTillDamage = 0;
        private int[][] geologicalFeatures;
        public ArrayList<Shark> sharks= new ArrayList<Shark>();
        public ArrayList<Plant> plants = new ArrayList<Plant>();
        public ArrayList<Slime> slimes = new ArrayList<Slime>();
        public ArrayList<Shark> getsharks(){
                return this.sharks;
        }
        /**
         * checks wether the player has won.
         *
         * @return true if the tiles mazub possesses contains the targettiles
         *              |if {targetTileX,targetTileY} in this.getPosition(this.getMazub().getXCord(),this.getMazub().getYCord(),
         *              |     ,this.getMazub().getXCord()+this.getMazub().getSizeX(), this.getMazub().getSizeY()+this.getMazub().getYCord())
         */
        public boolean hasplayerwon(){
        		int playerOne = 5;
                int x= this.getMazub().getXCord();
                int y = this.getMazub().getYCord();
                int xsize = this.getMazub().getSizeX();
                int ysize = this.getMazub().getSizeY();
                int[][] tiles = this.getTilePositionsIn(x, y, x+xsize, y+ysize);
                for (int i=0;i<tiles.length;i++){
                        if (tiles[i][0] == this.getTargetTileX() && tiles[i][1] == this.getTargetTileY()){
                                return true;
                        }
                }
                return false;
        }
        /**
         * tells you if the game is over
         * @return true if de method hasplayerwon is true or when the hitpoints of mazub are lower than zero
         *                      | if (hasplayerwon()||this.getMazub().getHitPoints()<=0)
                    |    then return true;
                }
         */    
        public boolean gameover(){
                if (hasplayerwon()||this.getMazub().getHitPoints()<=0){
                        return true;
                }
                return false;
        }
        public void addShark(Shark shark){
                sharks.add(shark);
                shark.setXBorder(this.getWorldSizeInPixels()[0]);
                shark.setYBorder(this.getWorldSizeInPixels()[1]);
        }
        public void setSharks(ArrayList<Shark> sharks){
                this.sharks = sharks;          
        }
        public void setSlimes(ArrayList<Slime> slimes){
                this.slimes = slimes;
        }
        public void setPlants(ArrayList<Plant> plants){
                this.plants = plants;}
       
        public ArrayList<Plant> getPlants(){
                return this.plants;
        }
        public void addPlants(Plant plant){
                ArrayList<Plant> plants = (ArrayList<Plant>)this.getPlants();
                plants.add(plant);
                this.setPlants(plants);
                plant.setXBorder(this.getWorldSizeInPixels()[0]);
                plant.setYBorder(this.getWorldSizeInPixels()[1]);
       
        }
        public ArrayList<Slime> getSlimes(){
                return this.slimes;
        }
        public void addSlimes(Slime slime){
                ArrayList<Slime> slimes = this.getSlimes();
                slimes.add(slime);
                this.setSlimes(slimes);
                slime.setXBorder(this.getWorldSizeInPixels()[0]);
                slime.setYBorder(this.getWorldSizeInPixels()[1]);
        }
       
        public void setTimeTillDamage(double a){
                this.timeTillDamage = a;
                if (this.timeTillDamage < 0)
                        this.setTimeTillDamage(0);
        }
       
        public double getTimeTillDamage(){
                return this.timeTillDamage;
        }
       /**
        * initialises a new world
        * @param tileSize
        *              
        @param tileSize
         *            Length (in pixels) of a side of each square tile in the world
         * @param nbTilesX
         *            Number of tiles in the horizontal direction
         * @param nbTilesY
         *            Number of tiles in the vertical direction
         * @param visibleWindowWidth
         *            Width of the visible window, in pixels
         * @param visibleWindowHeight
         *            Height of the visible window, in pixels
         * @param targetTileX
         *            Tile x-coordinate of the target tile of the created world
         * @param targetTileY
         *            Tile y-coordinate of the target tile of the created world
         * @effect creates a new world with a new matrix of geological features
         */
        public World(int tileSize, int nbTilesX, int nbTilesY,
                        int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
                        int targetTileY){
                this.setTileSize(tileSize);
                this.setNbTilesX(nbTilesX);
                this.setNbTilesY(nbTilesY);
                this.setVisibleWindowWidth(visibleWindowWidth);
                this.setVisibleWindowHeight(visibleWindowHeight);
                this.setTargetTileX(targetTileX);
                this.setTargetTileY(targetTileY);
                int[][] geo = new int[nbTilesX][nbTilesY];
                this.setGeologicalFeatures(geo);
               
        }
        public void setGeologicalFeatures(int[][] geo){
                this.geologicalFeatures = geo;
        }
        public int[][] getGeologicalFeatures(){
                return this.geologicalFeatures;
        }
        /**
         * returns every tile present in a window with the following corners
         * @param pixelLeft
         *              the left pixel of a certain window
         * @param pixelBottom
         *              the bottom pixel of the window
         * @param pixelRight
         *              the right pixel of the window
         * @param pixelTop
         *              the top pixel of the window
         * @return a list of coordinates of every tile in the window. every
         *                      tilecoördinate shall have the form[x_T,Y_T]
         */
        public int[][] getTilePositionsIn(int pixelLeft,
                int pixelBottom, int pixelRight, int pixelTop){
                if (pixelLeft<0){pixelLeft = 0; pixelRight = this.getVisibleWindowWidth();}
                if (pixelBottom<0){pixelBottom = 0;pixelTop = this.getVisibleWindowHeight();}
                if (pixelRight>this.getWorldSizeInPixels()[0]-1){pixelRight = this.getWorldSizeInPixels()[0]-1-this.getVisibleWindowWidth();}
                if (pixelTop>this.getWorldSizeInPixels()[1]-1){pixelTop = this.getWorldSizeInPixels()[1]-1-this.getVisibleWindowHeight();}
               
               
                int tile1X = pixelLeft/this.getTileSize();
                int tile2X = pixelRight/this.getTileSize();
                int tile1Y = pixelBottom/this.getTileSize();
                int tile2Y = pixelTop/this.getTileSize();
                int[][] positions = new int[(tile2X-tile1X+1)*(tile2Y-tile1Y+1)][2];
               
               
                int x2 = 0;
                for (int y = tile1Y;y<=tile2Y;y++){
                        for (int x = tile1X;x<=tile2X;x++){
                                positions[x2][0] = x;
                                positions[x2++][1] = y;
                        }
                }
                return positions;
        }
       
        public void setGeologicalFeature(int x,int y,int type){
               
                int[][] geo = this.getGeologicalFeatures();
                geo[x][y] = type;
                this.setGeologicalFeatures(geo);
        }
        public int getGeologicalFeature(int x,int y){
               
                int[][] geo = this.getGeologicalFeatures();
                return geo[x/this.getTileSize()][y/this.getTileSize()];
        }
        public void setNbTilesY(int nbTilesY) {
                this.nbTilesY = nbTilesY;
        }
        public void setTargetTileY(int targetTileY) {
                this.targetTileY = targetTileY;
        }
        public void setTargetTileX(int targetTileX) {
                this.targetTileX = targetTileX;
               
        }
        public void setVisibleWindowHeight(int visibleWindowHeight) {
                this.visibleWindowHeight = visibleWindowHeight;
               
        }
        public void setVisibleWindowWidth(int visibleWindowWidth) {
                this.visibleWindowWidth =visibleWindowWidth;
               
        }
        public void setNbTilesX(int nbTilesX) {
                this.nbTilesX = nbTilesX;
               
        }
        public void setTileSize(int tileSize) {
                this.tileSize = tileSize;
               
        }
        public int getNbTilesY() {
                return this.nbTilesY;
        }
       /**
        * returns a smaller time wich should represent the time it takes to move one pixel
        * @param dt
        *               the time in the method advancetime
        * @param entity
        *               the entity for which we calculate the smalltime
        * @return a small double variable which represents the time it takes to move a pixel.
        *
        */
        private double getSmallTime(double dt, Entities entity) throws ModelException{
               
            double smallTime = 0;
            double smallTimeX;
            double smallTimeY;
            double yDiff;
            double xDiff;
            
            if (entity.getDirection() == 1)
            	xDiff = entity.getXPos() - Math.floor(entity.getXPos());
            else if (entity.getDirection() == 2)
            	xDiff = Math.ceil(entity.getXPos()) - entity.getXPos();
            else if (entity.getVelocity() < 0)
            	xDiff = entity.getXPos() - Math.floor(entity.getXPos());
            else
            	xDiff = Math.ceil(entity.getXPos()) - entity.getXPos();
            
            
            if (entity.getJumpVelocity() < 0)
            	yDiff = entity.getYPos() - Math.floor(entity.getYPos());
            else
            	yDiff = Math.ceil(entity.getYPos()) - entity.getYPos();
            
            if (yDiff < 0 || xDiff < 0)
            	throw new ModelException("Smalltime problems");
            
               
            if (entity.getAcceleration() == 0)
                smallTimeX = xDiff/(Math.abs(entity.getVelocity()/100));
            else
                smallTimeX = (Math.sqrt(Math.abs(entity.getAcceleration()/100)*2*xDiff + Math.pow(entity.getVelocity()/100, 2))
                                - Math.abs(entity.getVelocity()/100)) / Math.abs(entity.getAcceleration()/100);
            
           
            if (entity.getJumpAcceleration() == 0)
                smallTimeY = yDiff/(Math.abs(entity.getJumpVelocity()/100));
            else
                smallTimeY = (Math.sqrt(Math.abs(entity.getJumpAcceleration()/100)*2*yDiff + Math.pow(entity.getJumpVelocity()/100, 2))
                                - Math.abs(entity.getJumpVelocity()/100)) / Math.abs(entity.getJumpAcceleration()/100);
           
            smallTime = Math.min(smallTimeX, smallTimeY);
           
            if (smallTime <= dt)
                    return smallTime;
            else
                return dt;
        }
       
       
        public int getTargetTileY() {
                return this.targetTileY;
               
        }
        public int getTargetTileX() {
                return this.targetTileX;
               
        }
        public int getVisibleWindowHeight() {
                return this.visibleWindowHeight;
               
        }
        public int getVisibleWindowWidth() {
                return this.visibleWindowWidth;
               
        }
        public int getNbTilesX() {
                return this.nbTilesX;}
        public void addshark(Shark shark){
               
        }
       
        public int getTileSize() {
                return this.tileSize;
               
        }
       /**
        * returns the amount of damage the entitie should receive depending on the environment,
        * @param entity
        *               the entity that might take damage
        * @param damage
        *               the damage the entity shall take
        * @param smallTime
        *               the time in which the entitie has been in the terrain
        * @effect if the total time in which the entitie is inside the terrain,exerts 0.2s, then the damage will be deduced of
        *                       the hitpoints of the entity and the totaltime will be reset
        */
        public void TerrainDamage(Entities entity, int damage, double smallTime){
                if (entity.getEnviroTime() >= 0.2){
                        entity.setEnviroTime(entity.getEnviroTime() - 0.2);
                        entity.setHitPoints(entity.getHitPoints() - damage);
                }
                entity.setEnviroTime(entity.getEnviroTime() + smallTime);
        }
       /**
        * determines the collisiondirection
        * @param entity1
        *               one of the entities which collides
        * @param entity2
        *               one of the entities which collides
        * @return 1 if the minimum difference in space in the y-coordinate is smaller than the minimum difference in space in the x-coordinate
        *                       else false
        */
        private void collisionPeaceful(Entities entity1, Entities entity2, double oldX, double oldY) throws ModelException{
               
        		if ((int) oldX == entity1.getXCord()){
        			entity1.setYPos(oldY);
        			entity1.setJumpVelocity(0);
        		}
        		
        		else if ((int) oldY == entity1.getYCord()){
        			entity1.setXPos(oldX);
        			entity1.setVelocity(0);
        		}
        		
        		else
        			throw new ModelException("colpea erroring");
        		
        		//TODO: werkt het? ...
        }
       /**
        * checks if two entities collide.
        * @param entity1
        *               one of the entities which might collide
        * @param entity2
        *               one of the entities which might collide
        * @return true if one of the following statements is true: the x-coordinate of entity1 resides in between
        *                               the x-coordinate and the x-coordinate plus the size of the sprite in width of entity2,
        *                               the x-coordinate of entity2 resides in between
        *                               the x-coordinate and the x-coordinate plus the size of the sprite in height of entity1,
        *                               the y-coordinate of entity1 resides in between
        *                               the y-coordinate and the y-coordinate plus the size of the sprite in width of entity2,
        *                               the y-coordinate of entity2 resides in between
        *                               the y-coordinate and the y-coordinate plus the size of the sprite in height of entity1,
        *                
        */
        private boolean collidesWith(Entities entity1, Entities entity2){
                double x1 = entity1.getXPos();
                double y1 = entity1.getYPos();
                double x2 = entity2.getXPos();
                double y2 = entity2.getYPos();
                if (x1 + (entity1.getSizeX() - 1) < x2 || x2 + (entity2.getSizeX() - 1) < x1 ||
                                y1 + (entity1.getSizeY() - 1) < y2 || y2 + (entity2.getSizeY() - 1) < y1){
                        return false;
                }
                return true;
        }
       
        public void advanceTime(double dt) throws ModelException {
            if (dt<0)
                throw new ModelException("illegal time");
               
                boolean damaged = false;
                double saveDT = dt;
                double smallTime = 0;
                double oldX = 0;
                double oldY = 0;
                int enviro = 0; //used for checking passable environments
                int enviro2 = 0;
               
                while (dt > 0){
                    smallTime = this.getSmallTime(dt, this.getMazub());
                       
                    oldX = (double) this.getMazub().getXCord();
                    oldY = (double) this.getMazub().getYCord();
                   
                    this.getMazub().advanceTime(smallTime);
                    dt = dt - smallTime;
                    if (this.getTimeTillDamage() > 0 && !damaged)
                        this.setTimeTillDamage(this.getTimeTillDamage() - smallTime);
                   
                    if (this.getMazub().collidesSide(this) || this.getMazub().getXPos() < this.getMazub().getminX())
                        this.getMazub().setXPos(oldX);
                   
                    if (this.getMazub().getYPos() >= getWorldSizeInPixels()[1] || this.getMazub().collidesTop(this, this.getMazub().getSprite())||this.getMazub().collidesBottom(this)){
                        if (this.getMazub().collidesBottom(this)){  
                        this.getMazub().setYPos(oldY);
                           while(!this.getMazub().collidesBottom(this)){
                                   this.getMazub().setYPos( this.getMazub().getYPos()-1);
                           }
                        }
                       
                     
                        else if(this.getMazub().collidesTop(this, this.getMazub().getSprite())){
                                this.getMazub().setYPos(oldY);
                            while(!this.getMazub().collidesTop((this),this.getMazub().getSprite())){
                                   this.getMazub().setYPos( this.getMazub().getYPos()+1);
                            }
                         
                         }
                        else{
                                this.getMazub().setYPos(oldY);
                        }
                       
                         this.getMazub().endJump();
                    }
 
           
                    for (Shark shark:this.getsharks()){
                        if (this.collidesWith(shark, this.getMazub())){
                                if (this.getTimeTillDamage() <= 0){
                                        if (!damaged){
                                                this.getMazub().setHitPoints(this.getMazub().getHitPoints() - shark.getDamage());
                                                shark.setHitPoints(shark.getHitPoints() - 50);
                                                damaged = true;
                                                this.setTimeTillDamage(0.6 - dt);
                                        }
                                }
                                collisionPeaceful(this.getMazub(), shark,oldX,oldY);
                        }
                    }
                    for (Plant plant:this.getPlants()){
                        if (this.collidesWith(plant, this.getMazub())){
                                this.getMazub().setHitPoints(this.getMazub().getHitPoints() + 50);
                                plant.setHitPoints(0);//TODO for alle objecten die nul hitpoints hebben verwijderen uit lijst
                               
                        }
                    }
                    for (Slime slime:this.getSlimes()){
                        if (this.collidesWith(slime, this.getMazub())){
                                if (this.getTimeTillDamage() <= 0){
                                        if(!damaged){
                                                this.getMazub().setHitPoints(this.getMazub().getHitPoints() - 50);
                                                slime.setHitPoints(slime.getHitPoints() - 50);
                                                damaged = true;
                                                this.setTimeTillDamage(0.6 - dt);
                                        }
                                }
                                collisionPeaceful(this.getMazub(), slime,oldX,oldY);
                        }
                    }
 
                    if(this.getMazub().collidesBottom(this)){
                        this.getMazub().setOnGround(true);
                        this.getMazub().setJumpAcceleration(0);
                        this.getMazub().setJumpVelocity(0);
                    }
                    else
                        this.getMazub().setOnGround(false);
                   
                    if (this.getMazub().getDucking() == 2){
                        if (!this.getMazub().collidesTop(this, this.getMazub().getSprites()[0]))
                                this.getMazub().setDucking(0);
                                this.getMazub().setMaxVelocity(this.getMazub().getMaxVelocityStanding());
                    }
                   
                    enviro = this.getMazub().inSubstance(this);
                    if (enviro == 2){
                        TerrainDamage(this.getMazub(), 2, smallTime);
                    }
                    else if (enviro == 3){
                        TerrainDamage(this.getMazub(), 50, smallTime);
                    }
                    else{
                        this.getMazub().setEnviroTime(0);
                    }
                }
               
            dt = saveDT;
            smallTime = 0;            
            Random rnd =new Random();
            int size = this.getsharks().size();
            for (int i = 0;i<size;i++){
                Shark shark = this.getsharks().get(size-1-i);
                if (shark.getHitPoints() <=0){
                        this.getsharks().remove(size-1-i);
                }
            }
            for (Shark shark:this.getsharks()){
                while (dt > 0){
                        smallTime = this.getSmallTime(dt, shark);
                       
                    oldX = (double) shark.getXCord();
                    oldY = (double) shark.getYCord();
                   
                    shark.advanceTime(smallTime);
                   
                    dt = dt - smallTime;
                   
                    if (shark.inSubstance(this) == 0 && shark.getabovewater() == false){
                        shark.setabovewater(true);
                        shark.setFalling(true);
                        shark.setJumpAcceleration(shark.getJumpStartAcceleration());
                    }
                    if (shark.topSubstance(this) == 0 && shark.getJumping()){
                        shark.setJumpAcceleration(0);
                    }
               
                    if (shark.topSubstance(this) == 2){
                        shark.setabovewater(false);
                        if (shark.getFalling())
                            shark.setFalling(false);
                                shark.setJumpVelocity(0);
                                shark.setJumpAcceleration(0);
                    }
                   
                if (shark.getabovewater()){
                   
                    if (shark.collidesSide(this) || shark.getXPos() < shark.getminX()){
                        shark.setXPos(oldX);
                    }
                   
                    if (shark.getYPos() >= getWorldSizeInPixels()[1]){
                           shark.setYPos(oldY);
                           shark.endJump();
                    }
                   
                    if (shark.collidesBottom(this) ){//alleen als je uit het water bent
                        shark.setJumpAcceleration(0);
                        shark.setJumpVelocity(0);
                        shark.setYPos(oldY);
                        while (!shark.collidesBottom(this)){
                            shark.setYPos(shark.getYPos()-1);
                        }
                        if (shark.getFalling()){
                                shark.setFalling(false);
                        }
                    }
                   
                    if (shark.collidesTop(this, shark.getSprite())){
                        shark.setJumpVelocity(0);
                        shark.setYPos(oldY);
                    }
                   }
                   
                 
                else{
                    if (shark.collidesSide(this)){
                        if (shark.getVelocity() > 0){
                                while (shark.collidesSide(this)){
                                    shark.setXPos(shark.getXPos()-1);
                                }
                        }
                        else{
                                while (shark.collidesSide(this)){
                                    shark.setXPos(shark.getXPos()+1);
                                }
                        }
                    }
                        if (shark.collidesTop(this, shark.getSprite())){
                        while (shark.collidesTop(this,shark.getSprite())){
                                shark.setYPos(shark.getYPos() - 1);
                        }
                     }
                     if (shark.collidesBottom(this)){
                        shark.setYPos(oldY);
                        while (!shark.collidesBottom(this)){
                                shark.setYPos(shark.getYPos()-1);
                        }
                     }
                     
                     if (shark.getSnowBallTime() >= shark.getPeriod()){
                         
                         if (shark.getJumping() == true){
                                 shark.end(Action.JUMP);
                                 shark.setTimeTillJump(4);
                         }
                         else
                                 shark.setTimeTillJump(shark.getTimeTillJump() - 1);
                       
                         shark.setPeriod((double)rnd.nextInt(31)/10 + 1);
                         shark.setSnowBallTime(0);
                         
                         if (Math.random()>0.5)
                                shark.start(Action.MOVELEFT);
                         else
                                shark.start(Action.MOVERIGHT);
                         
                         shark.setJumpAcceleration((double)rnd.nextInt(41)/100 - 0.2);
                         
                         if (shark.getTimeTillJump() == 0){
                                 shark.start(Action.JUMP);
                         }
                     }
                     
                   
                }
               
                    for (Shark shark2:this.getsharks()){
                        this.collisionPeaceful(shark,shark2,oldX,oldY);
                    }
                   
                    for (Slime slime:this.getSlimes()){
                        if (this.collidesWith(shark, slime)){
                            shark.setHitPoints(shark.getHitPoints() - 50);
                            slime.setHitPoints(slime.getHitPoints() - 50);
                            this.collisionPeaceful(shark, slime,oldX,oldY);
                        }
                    }
                   
                    if (this.collidesWith(shark, this.getMazub())){
                        if (this.getTimeTillDamage() <= 0){
                            if (!damaged){
                                    this.getMazub().setHitPoints(this.getMazub().getHitPoints() - shark.getDamage());
                                    shark.setHitPoints(shark.getHitPoints() - 50);
                                    damaged = true;
                                    this.setTimeTillDamage(0.6 - dt);
                            }
                        }
                        collisionPeaceful(shark, this.getMazub(),oldX,oldY);
                        }
                   
                    enviro = shark.inSubstance(this);
                    enviro2 = shark.topSubstance(this);
                   
                    if (enviro == 0 || enviro2 == 0){
                        TerrainDamage(shark, 6, smallTime);
                    }
                    else if (enviro == 3){
                        TerrainDamage(shark, 50, smallTime);
                    }
                    else{
                        shark.setEnviroTime(0);
                    }
                    shark.decideSprite();
                   
                }
                dt = saveDT;
                smallTime = 0;
            }
            dt = saveDT;
            smallTime = 0;
            size = this.getPlants().size();
            for (int i = 0;i<size;i++){
                Plant plant = this.getPlants().get(size-1-i);
                if (plant.getHitPoints() <=0){
                        this.getPlants().remove(size-1-i);
                }
            }
            for (Plant plant:this.getPlants()){
                while (dt > 0){
                        smallTime = this.getSmallTime(dt, plant);
                   
                    plant.advanceTime(smallTime);
                   
                    dt = dt - smallTime;
                   
                    if (this.collidesWith(plant, this.getMazub())){
                                this.getMazub().setHitPoints(this.getMazub().getHitPoints() + 50);
                                plant.setHitPoints(0);
                        }
                   
                }
                dt = saveDT;
                smallTime = 0;
            }
           
            size = this.getSlimes().size();
            for (int i = 0;i<size;i++){
                Slime slime = this.getSlimes().get(size-1-i);
                if (slime.getHitPoints() <=0){
                        this.getSlimes().remove(size-1-i);
                }
            }
            for (Slime slime:this.getSlimes()){
                while (dt > 0){
                    smallTime = this.getSmallTime(dt, slime);
                       
                    oldX = (double) slime.getXCord();
                    oldY = (double) slime.getYCord();
                   
                    slime.advanceTime(smallTime);
                   
                    dt = dt - smallTime;
                   
                   
                    if (!slime.collidesBottom(this)){
                                slime.start(Action.JUMP);
                    }
                    else{
                        slime.end(Action.JUMP);
                        slime.setYPos(oldY);
                        while (!slime.collidesBottom(this)){
                                slime.setYPos(slime.getYPos()-1);
                        }
                    }
                   
                   
                    if (slime.collidesSide(this) || slime.getXPos() < slime.getminX()){
                       
                        if (slime.collidesSide(this)){
                                slime.setXPos(oldX);
                                int direction = ((slime.getAcceleration()<0) ? 1:2);
                                while(!slime.collidesSide(this)){
                                        if (direction == 2){
                                                slime.setXPos(slime.getXPos()+1);
                                        }
                                        else{
                                                slime.setXPos(slime.getXPos()-1);
                                        }
                               
                                       
                                }
                                }
                       
                        else{
                                slime.setXPos(oldX);
                        }
                        if (slime.getAcceleration()<0){
                                slime.end(Action.MOVELEFT);
                                slime.start(Action.MOVERIGHT);
                        }
                        else{
                                slime.end(Action.MOVERIGHT);
                                slime.start(Action.MOVELEFT);
                    }
                    }
                   
                   
                   
                    for (Slime slime2:this.getSlimes()){
                       
                        if (this.collidesWith(slime, slime2) && slime != slime2){
                               
                                if (slime.getSchool() != slime2.getSchool()){
                                        if (slime.getSchool().getamountslimes() < slime2.getSchool().getamountslimes()){
                                                int life = 0;
                                                for (Slime slime3:slime.getSchool().getslimes()){
                                                        if(slime3 != slime){
                                                                life++;
                                                                slime3.setHitPoints(slime3.getHitPoints()+1);
                                                        }
                                                }
                                                slime.setHitPoints(slime.getHitPoints()-life);
                                                slime.getSchool().removeslime(slime);
                                                slime.setSchool(slime2.getSchool());
                                                slime2.getSchool().addslimetoschool(slime);
                                                life = 0;
                                                for (Slime slime3:slime2.getSchool().getslimes()){
                                                        if(slime3 != slime){
                                                                life++;
                                                                slime3.setHitPoints(slime3.getHitPoints()-1);
                                                        }
                                                }
                                                slime.setHitPoints(slime.getHitPoints()+life);    
                       
                                        }
                                        else if (slime.getSchool().getamountslimes() < slime2.getSchool().getamountslimes()){
                                                int life = 0;
                                                for (Slime slime3:slime2.getSchool().getslimes()){
                                                        if(slime3 != slime2){
                                                                life++;
                                                                slime3.setHitPoints(slime3.getHitPoints()+1);
                                                        }
                                                }
                                                slime2.setHitPoints(slime2.getHitPoints()-life);
                                                slime2.getSchool().removeslime(slime2);
                                                slime2.setSchool(slime.getSchool());
                                                slime.getSchool().addslimetoschool(slime2);
                                                life = 0;
                                                for (Slime slime3:slime.getSchool().getslimes()){
                                                        if(slime3 != slime2){
                                                                life++;
                                                                slime3.setHitPoints(slime3.getHitPoints()-1);
                                                        }
                                                }
                                                slime2.setHitPoints(slime2.getHitPoints()+life);    
                                        }
                                }
                                collisionPeaceful(slime, slime2,oldX,oldY);  
                        }
                     }
                  }
                 
                        if (this.collidesWith(slime, this.getMazub())){
                            if (this.getTimeTillDamage() <= 0){
                                if (!damaged){
                                        this.getMazub().setHitPoints(this.getMazub().getHitPoints() - slime.getdamage());
                                        slime.setHitPoints(slime.getHitPoints() - 50);
                                        punishschool(slime);
                                        damaged = true;
                                        this.setTimeTillDamage(0.6 - dt);
                                }
                            }
                            collisionPeaceful(slime, this.getMazub(),oldX,oldY);
                        }
                        for (Shark shark:this.getsharks()){
                            if (this.collidesWith(shark, slime)){
                                shark.setHitPoints(shark.getHitPoints() - 50);
                                slime.setHitPoints(slime.getHitPoints() - 50);
                                punishschool(slime);
                                collisionPeaceful(slime, shark,oldX,oldY);
                            }
                        }
                       
                enviro = slime.inSubstance(this);
               
                if (enviro == 2){
                    TerrainDamage(slime, 6, smallTime);
                    punishschool(slime);
                }
                else if (enviro == 3){
                    TerrainDamage(slime, 50, smallTime);
                    punishschool(slime);
                }
                else{
                    slime.setEnviroTime(0);
                }
                dt = saveDT;
                smallTime = 0;
                }
        }
       
       
        public int[] getWorldSizeInPixels(){
                int[] size = {this.getNbTilesX()*this.getTileSize(),this.getNbTilesY()*this.getTileSize()};
                return size;
        }
        /**
         * gives back the visible window of mazub, the half on one side of the screen and the other half on the other side
         * @post if the Xcord of mazub minus the half of screenwidth is smaller than 0,
         *                      than the visible x-window is from zero to the visiblewindowwidth
         *               if the Xcord of mazub plus the half of screenwidth is bigger than worldsizeX,
         *                      than the visible x-window is from worldSizeX minus visiblewindowwidth/2 to worldSizeX
         *                      else the visible window is from the xcord minus half of the visible window and xcord plus half of the visible window
         *                      same system for Y
         *                      |if (this.getMazub().getXcord()-this.getVisibleWindowWidth()/2<0)
         *                      |       then new.getvisiblewindow()[1] == 0 and new.getvisiblewindow()[3] == this.getVisibleWindowWidth()
         *                      |else if (this.getMazub().getXcord()+this.getVisibleWindowWidth()/2>this.getworldsizeinpixels[0])
         *                      |       then new.getvisiblewindow()[1] == this.getWorldsizeinpixels[0]-getvisiblewindowwidth/2 and new.getvisiblewindow()[3] == this.getWorldsizeinpixels[0]
         *                      |else  new.getvisiblewindow()[1] == this.getMazub().getXCord()-getvisiblewindowwidth() and  new.getvisiblewindow()[3] == this.getMazub().getXCord()-getvisiblewindowwidth()
         * @return the 4pixels of the visible window
         */
        public int[] getVisibleWindow(){
                Mazub mazub = this.getMazub();
                int x=mazub.getXCord();
                int y=mazub.getYCord();
               
                int left = x-(this.getVisibleWindowWidth()/2);
                int bottom = y-(this.getVisibleWindowHeight()/2);
                int right = x+(this.getVisibleWindowWidth()/2);
                int top = y+(this.getVisibleWindowHeight()/2);
                if (left<0){left = 0; right = this.getVisibleWindowWidth();}
                if (bottom<0){bottom = 0;top = this.getVisibleWindowHeight();}
                if (right>this.getWorldSizeInPixels()[0]-1){right = this.getWorldSizeInPixels()[0]-1;left = this.getWorldSizeInPixels()[0]-1-this.getVisibleWindowWidth();}
                if (top>this.getWorldSizeInPixels()[1]-1){top = this.getWorldSizeInPixels()[1]-1;bottom = this.getWorldSizeInPixels()[1]-1-this.getVisibleWindowHeight();}
                int[] visible = {left,bottom,right,top};
                return visible;
        }
        public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
                int[] bottom = {tileX*this.getTileSize(), tileY*this.getTileSize()};
                return bottom;
        }
        public void setMazub(Mazub mazub){
                this.mazub = mazub;
                this.getMazub().setXBorder(this.getWorldSizeInPixels()[0]);
                this.getMazub().setYBorder(this.getWorldSizeInPixels()[1]);
        }
        public Mazub getMazub(){
                return this.mazub;
        }
       
        /*private void collisionPeaceful(Entities enti1, Entities enti2,double oldX,double oldY){
            if (enti1 != enti2 && this.collidesWith(enti1, enti2)){
               
                boolean colDir = this.horizontalchecker(oldY, enti1, enti2);
                if (!colDir){
                    this.verticalCollisionEntities(enti1, enti2,oldX,oldY);
                }
                else{
                                this.horizontalCollisionEntities(enti1, enti2);
                }
            }
        }*/
       
        private void verticalCollisionEntities(Entities enti1, Entities enti2,double OldX,double OldY){
                enti1.setJumpVelocity(0);
                if (enti1.getYPos() > enti2.getYPos()){
                        while(this.collidesWith(enti1, enti2) && !enti1.collideTopAndBottom(this)){
                    enti1.setYPos(enti1.getYPos()+1);
                        }
                        while(this.collidesWith(enti1, enti2)){
                                 enti2.setYPos(enti2.getYPos()-1);     
                        }
            }
                else{
                        while(this.collidesWith(enti1, enti2) && !enti1.collideTopAndBottom(this)){
                    enti1.setYPos(enti1.getYPos()-1);
                        }
                        while(this.collidesWith(enti1, enti2)){
                         enti2.setYPos(enti2.getYPos()+1);     
                       }
                       
                }
        }
        private void horizontalCollisionEntities(Entities enti1, Entities enti2){
                enti1.setVelocity(0);
                if (enti1.getXPos() > enti2.getXPos()){
                        while(this.collidesWith(enti1, enti2) && !enti1.collidesbothsides(this)){
                    enti1.setXPos(enti1.getXPos()+1);
                        }
                        while(this.collidesWith(enti1, enti2)){
                                enti2.setXPos(enti2.getXPos()-1);
                        }
            }
                else{
                         while(this.collidesWith(enti1, enti2) && !enti1.collidesbothsides(this)){
                         enti1.setXPos(enti1.getXPos()-1);
                             }
                     while(this.collidesWith(enti1, enti2)){
                                enti2.setXPos(enti2.getXPos()+1);
                             }
                }
        }
        private void punishschool(Slime slime){
            for (Slime slime2:slime.getSchool().getslimes()){
                if (slime2 != slime)
                        slime2.setHitPoints(slime2.getHitPoints()-1);
            }
        }
        private boolean horizontalchecker(double oldY,Entities enti1,Entities enti2){
                double limit = oldY+enti1.getSizeY();
                for (double i = oldY;i<limit;i++){
                        if(i>=enti2.getYCord() && i<=(enti2.getYCord()+enti2.getSizeY())){
                                return true;
                        }
                }
                return false;
        }
}