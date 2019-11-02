package tower;
import java.lang.Math;

public abstract class Tower {
	
	public enum TowerState{
		READY,
		COOLDOWN,
		ATTACK
	}
	
	private int buildingCost;
	private int power;
	private int shootingRange;
	private int[] coord;
	private TowerState curState=TowerState.READY;
	
	
	public Tower(int []Coord,int cost,int power, int range) {
		this.buildingCost = cost;
		this.power = power;
		this.shootingRange = range;
		this.coord = new int[2];
		this.coord[0] = Coord[0];
		this.coord[1] = Coord[1];
	}
	
	public int getTowerCost () {
		return this.buildingCost;
	}
	public int getPower() {
		return this.power;
	}

	public int getRange(){
		return this.shootingRange;
	}
	public int [] getCoord(){
		return coord;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setRange(int range) {
		this.shootingRange = range;
	}
	public boolean isInRange(int [] coord) {
        int [] towerCoord = this.getCoord();
        double distance = Math.sqrt(Math.pow((coord[0]-towerCoord[0]),2)+Math.pow((coord[1]-towerCoord[1]),2));

        return (distance<this.getRange())?true:false;
    }
	public TowerState getTowerState() {
		return curState;
	}
	public void setTowerState(TowerState newState) {
		this.curState=newState;
	}
	public abstract void upgradeTower(boolean canUpgrade);
}
