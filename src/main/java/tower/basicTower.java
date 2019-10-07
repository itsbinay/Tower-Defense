package tower;
//import Tower;

public class basicTower extends Tower{
    public basicTower(int xCoord, int yCoord,int cost,int power,int range){
        super(xCoord,yCoord,cost,power,range);
    }
    
    @Override 
    public boolean isInRange() {
    	return true;
    }
}