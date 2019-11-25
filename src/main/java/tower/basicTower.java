package tower;

/**
 * This is a basicTower class to model a basic-type Tower
 * 
 * @author binay
 *
 */
public class basicTower extends Tower {
	/**
	 * The upgradeCost of a basicTower
	 */
	private static final int upgradeCost = 50;
	/**
	 * the power increment per upgrade of a basicTower
	 */
	private static final int powerIncrement = 5;
	/**
	 * the range increment per upgrade of a basicTower
	 */
	private static final int rangeIncrement = 5;

	/**
	 * Initial cost of a basicTower
	 */
	private static final int initialCost = 60;
	/**
	 * Initial power of a basicTower
	 */
	private static final int initialPower = 50;
	/**
	 * Initial range of a basicTower
	 */
	private static final int initialRange = 70;
	
	/**
	 * Cooldowntimer of a basicTower
	 */
	private static final int initialCDTimer=1;
	
	private static final String towerType = "basicTower";

	/**
	 * Constructor of the basicTower
	 * 
	 * @param Coord coordinate of basicTower where is created
	 */
	public basicTower(int[] Coord) {
		super(Coord, initialCost, initialPower, initialRange, towerType,upgradeCost,initialCDTimer);
	}


	@Override
	/**
	 * If the canUpgrade argument is evaluates to be true, the power and the range
	 * of the basicTower will be incremented.
	 * 
	 * @param canUpgrade this parameter indicates whether the basicTower can be
	 *                   upgraded
	 */
	public void upgradeTower(boolean canUpgrade) {
		if (!canUpgrade)
			return;

		this.setPower(this.getPower() + powerIncrement);
		this.setRange(this.getRange() + rangeIncrement);
	}
	/**
	 * Returns the updated hp of the monster given it was attacked.
	 * @param hp the current hp of the monster
	 * @return the updated hp of the monster
	 */
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			//System.out.println("basicTower("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}
	public String getTowerType() {
		return "basicTower";
	}
}