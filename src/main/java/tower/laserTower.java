package tower;

public class laserTower extends Tower {
	private static final int upgradeCost = 120;
	private static final int powerIncrement = 120;
	private static final int rangeIncrement = 50;

	private static final int initialCost = 180;
	private static final int initialPower = 180;
	private static final int initialRange = 50;
	private static final String towerType = "laserTower";

	public laserTower(int [] initialCoord){
		super(initialCoord,initialCost,initialPower,initialRange, towerType);
	}
	@Override
	public void upgradeTower(boolean canUpgrade){
		if(!canUpgrade)return;
		
		this.setPower(this.getPower()+powerIncrement);
		this.setRange(this.getPower()+rangeIncrement);
	}
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
	public int getRangeIncrement() {
		return rangeIncrement;
	}
}
