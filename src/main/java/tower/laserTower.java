package tower;

/**
 * This is a laserTower class to model a laserTower-type Tower
 * This class inherits the abstract class Tower
 * @author Binay 
 */
public class laserTower extends Tower {
	private static final int upgradeCost = 120;
	private static final int powerIncrement = 30;
	private static final int rangeIncrement = 50;

	private static final int attackCost = 20;
	private static final int initialCost = 180;
	private static final int initialPower = 130;
	private static final int initialRange = 80;
	private static final String towerType = "laserTower";
	private static final int initialCDTimer=5;
	private static final int MAXUpgradeLimit = 4;
	
	private int upgradeCount = 0;
	/**
	 * Constructor of the laserTower
	 * @param Coord coordinate of where the Catapult is built (the top left corner of the grid)
	 */
	public laserTower(int [] Coord){
		super(Coord,initialCost,initialPower,initialRange, towerType,upgradeCost,initialCDTimer);
	}
	@Override
	public void upgradeTower(boolean canUpgrade){
		if(!canUpgrade || upgradeCount>=MAXUpgradeLimit)return;
		
		upgradeCount++;
		this.setPower(this.getPower()+powerIncrement);
		this.setRange(this.getRange()+rangeIncrement);
	}
	@Override
	public int getAttackCost() {
		return this.attackCost;
	}
	/**
	* Gets the hp of the monster, attacks it and then returns the new monster hp
	* 
	* @param hp initial hp of the monster before being attacked
	* @return returns the the new HP of the monster attacked
	*/
 @Override
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			//System.out.println("laserTower("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}

	/**
	 * Gets the rangeIncrement of the laserTower in every upgrade
	 * @return the rangeIncrement of the lasertower in every upgrade
	 */
	public int getRangeIncrement() {
		return rangeIncrement;
	}
}
