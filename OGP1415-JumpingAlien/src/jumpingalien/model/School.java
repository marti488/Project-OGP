package jumpingalien.model;
 
import java.util.ArrayList;
 
public class School{
        private int amountslimes;
        private ArrayList<Slime> slimes = new ArrayList();
        public School(){
                this.amountslimes=0;           
        }
        public void setamountslimes(int amountslimes){
                this.amountslimes = amountslimes;
        }
        public int getamountslimes(){
                return this.amountslimes;
        }
        public void setslimes(ArrayList<Slime> slimes){
                this.slimes = slimes;
        }
        public ArrayList<Slime> getslimes(){
                return this.slimes;
        }
        public void addslimetoschool(Slime slime){
                ArrayList<Slime> slimes = this.getslimes();
                slimes.add(slime);
                this.setslimes(slimes);
                this.setamountslimes(this.getamountslimes()+1);
        }//TODO remove slime from school
 
}