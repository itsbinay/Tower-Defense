package tower;

public abstract class Tower {
	private int buildingCost;
	private int power;
	private int shootingRange;
	private int[] coord;
	
	public Tower(int xCoord,int yCoord,int cost,int power, int range) {
		this.buildingCost = cost;
		this.power = power;
		this.shootingRange = range;
		coord[0] = xCoord;
		coord[1] = yCoord;
	}
	public Tower() {
		this.buildingCost = 0;
		this.power = 0;
		this.shootingRange = 0;
	}
	
	public int getTowerCost () {
		return this.buildingCost;
	}
	public int getPower() {
		return this.power;
	}
	public int [] getCoord(){
		return coord;
	}
	
	public abstract boolean isInRange();
}
