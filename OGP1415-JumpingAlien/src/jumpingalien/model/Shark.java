package jumpingalien.model;
 
import jumpingalien.util.Sprite;
 
 
 
import java.util.Random;
public class Shark extends Entities {
        private int damage,passedperiod;
        private double acceleration,velocity,period,floatingAcc,floatingVel,jumping,damagetime = 0;
        public Shark(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {     
                super(pixelLeftX, pixelBottomY, sprites);
                Random rnd =new Random();
                this.setVelocity(0);
                this.setAcceleration(1.5);
                this.setDamage(50);
                this.setHitPoints(100);
                this.setPeriod((double)rnd.nextInt(31)/10 + 1);
                this.setSnowBallTime(0);
                this.setfloatingAcc((double)rnd.nextInt(40)/100 - 0.2);
                this.setfloatingvel(0);
                this.setpassedperiod(0);
                this.setJumpVelocity(2);
                this.setJumpAcceleration(-10);
                this.setjumping(0);
                this.setXPos(pixelLeftX);
                this.setYPos(pixelBottomY);
                }
        public void setjumping(double jumping){
                this.jumping = jumping;
        }
        public double getjumping(){
                return this.jumping;
        }
        //TODO in geval van een impassable object draai snelheid om
               
        @Override
        public void endJump(){
                Random rnd =new Random();
                this.setfloatingvel(0);
                this.setfloatingAcc((double)rnd.nextInt(40)/100 - 0.2);
                this.setjumping(0);
                }
        @Override
        public void startJump(){
                this.setfloatingvel(this.getJumpVelocity());
                this.setfloatingAcc(0);
                this.setjumping(1);
               
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
        public void setfloatingvel(double floatingvel){
                this.floatingVel = floatingvel;
        }
        public double getfloatingvel(){
                return this.floatingVel;
        }
        public void setpassedperiod(int passedperiod){
                this.passedperiod = passedperiod;
        }
        public int getpassedperiod(){
                return this.passedperiod;
        }
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
        public void setfloatingAcc(double floatingAcc){
                this.floatingAcc = floatingAcc;
        }
        public double getfloatingAcc(){
                return this.floatingAcc;
        }
       
       
        @Override
        public void setVelocity(double velocity){
                if (velocity>4){
                        velocity = 4;
                }
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
        void advanceTime(double dt) {
                Random rnd =new Random();
                setSnowBallTime(getSnowBallTime()+dt);//TODO magma
                if (outwater(world)&& watertop(world)){
                        this.setDamageTime(this.getDamageTime()+dt);
                        this.setfloatingAcc(-10);
                        if (collidesSide(world)){
                                setVelocity(0);
                                setAcceleration(0);
                               
                        }
                        if (collidesTop(world,getSprite())&& getfloatingvel() > 0){
                                setfloatingvel(0);
                        }
                        this.setAcceleration(0);
                        if (this.collidesBottom(world)){this.setfloatingAcc(0);setfloatingvel(0);}}
               
                else {
                        this.setDamageTime(0);
                        if (getSnowBallTime()>=getPeriod()){
                                setpassedperiod(getpassedperiod()+1);
                                if (getpassedperiod() == 0){
                                        endJump();
                                }
                                setPeriod((double)rnd.nextInt(31)/10 + 1);
                                setSnowBallTime(0);
                                this.setAcceleration(1.5);
                                setfloatingAcc((double)rnd.nextInt(41)/100 - 0.2);
                                setfloatingvel(0);
                                if (getpassedperiod() >=4 && Math.random()>0.5){
                                        setpassedperiod(-1);
                                        startJump();
                                }
                        }
                        if (collidesSide(world)){
                                setVelocity(0);
                                setAcceleration(getAcceleration()*-1);
                               
                        }
                        if ((collidesBottom(world) && getfloatingvel() < 0)||(collidesTop(world,getSprite())&& getfloatingvel() > 0)){
                                setfloatingAcc(0);
                                setfloatingvel(getfloatingvel()*-1);
                        }
                        if (this.getfloatingAcc() == -10){
                                setfloatingvel(0);
                                setfloatingAcc(0);
                        }
                }
                if (getDamageTime()>0.2){
                        this.setHitPoints(this.getHitPoints() - 6);
                        setDamageTime(getDamageTime()-0.2);
                }
               
                double x =  getXPos()+100*(getVelocity()*dt+getAcceleration()*dt*dt/2);
                setVelocity(getVelocity()+getAcceleration()*dt);
                double y = getYPos()+100*(getfloatingAcc()*dt*dt/2+getfloatingvel()*dt);
                setfloatingvel(getfloatingvel()+getfloatingAcc()*dt);
                setYPos(y);
                setXPos(x);
                setYCord((int)getYPos());
                setXCord((int)getXPos());
                decideSprite();
               
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
        public boolean outwater(World world){
                int width = getSizeX();
        int posX = this.getXCord();
        int posY = this.getYCord();
        for (int x = 0; x < width; x++){
                if (world.getGeologicalFeature(x + posX, posY) == 2){
                        return false;
                }
        }
        return true;}
    public boolean watertop(World world){
               
                int height = getSprite().getHeight();
                int width = getSizeX();
                int posX = this.getXCord();
                int posY = this.getYCord();
                for (int x = 0; x < width; x++){
                        if (world.getGeologicalFeature(x + posX, height + posY) == 2){
                                return false;
                        }
                }
                return true;
       
        }
       
}