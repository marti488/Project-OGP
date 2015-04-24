package jumpingalien.model;
 
import java.util.ArrayList;

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
        public void addShark(Shark shark){
                sharks.add(shark);
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
       
        }
        public ArrayList<Slime> getSlimes(){
                return this.slimes;
        }
        public void addSlimes(Slime slime){
                ArrayList<Slime> slimes = this.getSlimes();
                slimes.add(slime);
                this.setSlimes(slimes);
        }
        
        public void setTimeTillDamage(double a){
        	this.timeTillDamage = a;
        }
        
        public double getTimeTillDamage(){
        	return this.timeTillDamage;
        }
       
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
                for (int x = tile1X;x<=tile2X;x++){
                        for (int y = tile1Y;y<=tile2Y;y++){
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
                System.out.println('b');
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
        
        public double getSmallTime(double dt, Entities entity){
        	
        	double smallTime = 0;
            double smallTimeX;
            double smallTimeY;
        	
        	if (entity.getAcceleration() == 0)
            	smallTimeX = 1/(Math.abs(entity.getVelocity()/100));
            else
            	smallTimeX = (Math.sqrt(Math.abs(entity.getAcceleration()/100) + Math.pow(entity.getVelocity()/100, 2))
            			- Math.abs(entity.getVelocity()/100)) / Math.abs(entity.getAcceleration()/100);
            
            if (entity.getJumpAcceleration() == 0)
            	smallTimeY = 1/(Math.abs(entity.getJumpVelocity()/100));
            else
            	smallTimeY = (Math.sqrt(Math.abs(entity.getJumpAcceleration()/100) + Math.pow(entity.getJumpVelocity()/100, 2))
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
        
        public boolean collidesWith(Entities entity1, Entities entity2){
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
        	
        	double saveDT = dt;
        	double smallTime = 0;
        	
        	while (dt > 0){
	        	smallTime = this.getSmallTime(dt, this.getMazub());
	        	
	            double oldX = (double) this.getMazub().getXCord();
	            double oldY = (double) this.getMazub().getYCord();
	            
	            this.getMazub().advanceTime(smallTime);
	            dt = dt - smallTime;
	            if (this.getTimeTillDamage() > 0)
	            	this.setTimeTillDamage(this.getTimeTillDamage() - smallTime);
	            
	            if (this.getMazub().collidesSide(this) || this.getMazub().getXPos() < this.getMazub().getminX()){
	            	this.getMazub().setXPos(oldX);
	     	    }
	            
	            if (this.getTimeTillDamage() <= 0){
		            for (Shark shark:this.getsharks()){
		            	if (this.collidesWith(shark, this.getMazub())){
		            		this.getMazub().setHitPoints(this.getMazub().getHitPoints() - 50);
		            		shark.setHitPoints(shark.getHitPoints() - 50);
		            		
		            		this.setTimeTillDamage(0.6);
		            	}
		            }
		            for (Plant plant:this.getPlants()){
		            	if (this.collidesWith(plant, this.getMazub())){
		            		this.getMazub().setHitPoints(this.getMazub().getHitPoints() + 50);
		            		//TODO: delete plant!
		            	}
		            }
		            for (Slime slime:this.getSlimes()){
		            	if (this.collidesWith(slime, this.getMazub())){
		            		this.getMazub().setHitPoints(this.getMazub().getHitPoints() - 50);
		            		slime.setHitPoints(slime.getHitPoints() - 50);
		            		
		            		this.setTimeTillDamage(0.6);
		            	}
		            }
	            }
	            
	            
	            if (this.getMazub().getYPos() >= getWorldSizeInPixels()[1] || this.getMazub().collidesTop(this, this.getMazub().getSprite())){
	                   this.getMazub().setYPos(oldY);
	                   this.getMazub().endJump();
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
        	}
        	
        	dt = saveDT;
            
        }
        
        public int[] getWorldSizeInPixels(){
                int[] size = {this.getNbTilesX()*this.getTileSize(),this.getNbTilesY()*this.getTileSize()};
                return size;
        }
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
        }
        public Mazub getMazub(){
                return this.mazub;
        }
}