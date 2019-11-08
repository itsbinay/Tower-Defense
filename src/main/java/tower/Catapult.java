package tower;
 
public class Catapult extends Tower {
	private static final int upgradeCost = 100;
	private static final int powerIncrement = 40;
	private static final int rangeIncrement = 40;
	
	private static final int initialCost = 80;
	private static final int initialPower = 50;
	private static final int initialRange = 200;
	
	public Catapult(int []Coord) {
		super(Coord,initialCost,initialPower,initialRange);
	}
	
	@Override
	public void upgradeTower(boolean canUpgrade) {
		if(!canUpgrade)return;
		
		this.setPower(this.getPower() + powerIncrement);
		this.setRange(this.getRange() + rangeIncrement);
	}
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
}
