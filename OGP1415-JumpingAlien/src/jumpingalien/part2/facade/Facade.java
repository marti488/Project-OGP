package jumpingalien.part2.facade;
 
import java.util.Collection;
 
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.model.*;
 
public class Facade implements IFacadePart2{
 
        @Override
        public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
                Mazub alien = new Mazub(pixelLeftX,pixelBottomY,sprites);
                return alien;
        }
 
        @Override
        public int[] getLocation(Mazub alien) {
                int[] pos= {alien.getXCord() , alien.getYCord()};
        return pos;
        }
 
        @Override
        public double[] getVelocity(Mazub alien) {
                 double[] vel = {alien.getVelocity(),alien.getJumpVelocity()};
         return vel;}
       
        @Override
        public double[] getAcceleration(Mazub alien) {
                 double[] acc = {alien.getAcceleration(),alien.getJumpAcceleration()};
         return acc;
        }
 
        @Override
        public int[] getSize(Mazub alien){
                int[] size = {alien.getSizeX(), alien.getSizeY()};
        return size;
        }
 
        @Override
        public Sprite getCurrentSprite(Mazub alien) {
                return alien.getSprite();
        }
 
        @Override
        public void startJump(Mazub alien) {
                alien.startJump();
        }
 
        @Override
        public void endJump(Mazub alien) {
                alien.endJump();
        }
 
        @Override
        public void startMoveLeft(Mazub alien) {
                 alien.startMove(1);
        }
 
        @Override
        public void endMoveLeft(Mazub alien) {
                alien.stopMove(1);  
               
        }
 
        @Override
        public void startMoveRight(Mazub alien) {
                alien.startMove(2);
               
        }
 
        @Override
        public void endMoveRight(Mazub alien) {
                alien.stopMove(2);
               
        }
 
        @Override
        public void startDuck(Mazub alien) {
        alien.startDuck();
               
        }
 
        @Override
        public void endDuck(Mazub alien) {
                alien.endDuck();
               
        }
 
        @Override
        public void advanceTime(Mazub alien, double dt) {
               
               
               
        }
 
        @Override
        public int getNbHitPoints(Mazub alien) {
       
                return alien.getHitPoints();
        }
 
        @Override
        public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
                        int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
                        int targetTileY) {
                World world = new World(tileSize,nbTilesX,nbTilesY, visibleWindowWidth,visibleWindowHeight,targetTileX,
                                 targetTileY);
                return world;
        }
 
        @Override
        public int[] getWorldSizeInPixels(World world) {
                return world.getWorldSizeInPixels();
        }
 
        @Override
        public int getTileLength(World world) {
                return world.getTileSize();
        }
 
        @Override
        public void startGame(World world) {
               
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public boolean isGameOver(World world) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean didPlayerWin(World world) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public void advanceTime(World world, double dt) {
               
                world.advanceTime(dt); 
        }
 
        @Override
        public int[] getVisibleWindow(World world) {
       
                // TODO Auto-generated method stub
               
                return world.getVisibleWindow();
        }
 
        @Override
        public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
                return world.getBottomLeftPixelOfTile(tileX,tileY);
        }
 
        @Override
        public int[][] getTilePositionsIn(World world, int pixelLeft,
                        int pixelBottom, int pixelRight, int pixelTop) {
                // TODO Auto-generated method stub
               
                return world.getTilePositionsIn(pixelLeft, pixelBottom, pixelRight, pixelTop);
        }
 
        @Override
        public int getGeologicalFeature(World world, int pixelX, int pixelY)
                        throws ModelException {
                return world.getGeologicalFeature(pixelX, pixelY);
        }
 
        @Override
        public void setGeologicalFeature(World world, int tileX, int tileY,
                        int tileType) {
                world.setGeologicalFeature(tileX, tileY, tileType);
               
        }
 
        @Override
        public void setMazub(World world, Mazub alien) {
                world.setMazub(alien); 
        }
 
        @Override
        public boolean isImmune(Mazub alien) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public Plant createPlant(int x, int y, Sprite[] sprites) {
                Plant plant = new Plant(x,y,sprites);
                return plant;
        }
 
        @Override
        public void addPlant(World world, Plant plant) {
                world.addPlants(plant);
        }
 
        @Override
        public Collection<Plant> getPlants(World world) {
                // TODO Auto-generated method stub
                return world.getPlants();
        }
 
        @Override
        public int[] getLocation(Plant plant) {
                // TODO Auto-generated method stub
                int[] pos= {plant.getXCord() , plant.getYCord()};
        return pos;
        }
 
        @Override
        public Sprite getCurrentSprite(Plant plant) {
                // TODO Auto-generated method stub
                return plant.getSprite();
        }
 
        @Override
        public Shark createShark(int x, int y, Sprite[] sprites) {
               
                Shark shark = new Shark(x,y,sprites);
                return shark;
        }
 
        @Override
        public void addShark(World world, Shark shark) {
               
                world.addShark(shark);         
        }
 
        @Override
        public Collection<Shark> getSharks(World world) {
               
                return world.getsharks();
        }
 
        @Override
        public int[] getLocation(Shark shark) {
                int[] pos= {shark.getXCord() , shark.getYCord()};
        return pos;
        }
 
        @Override
        public Sprite getCurrentSprite(Shark shark) {
               
                return shark.getSprite();
        }
 
        @Override
        public School createSchool(){
                // TODO Auto-generated method stub
                return null;
        }
 
        @Override
        public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
                school =new School();
                Slime slime = new Slime(x,y,sprites,school);
                slime.getSchool().addslimetoschool(slime);
                return slime;
        }
 
        @Override
        public void addSlime(World world, Slime slime) {
                world.addSlimes(slime);
        }
       
        @Override
        public Collection<Slime> getSlimes(World world) {
                // TODO Auto-generated method stub
                return world.getSlimes();
        }
 
        @Override
        public int[] getLocation(Slime slime) {
                // TODO Auto-generated method stub
                int[] pos= {slime.getXCord() , slime.getYCord()};
        return pos;
        }
 
        @Override
        public Sprite getCurrentSprite(Slime slime) {
                // TODO Auto-generated method stub
                return slime.getSprite();
        }
 
        @Override
        public School getSchool(Slime slime) {
                School school = slime.getSchool();
                return school;
        }
 
}