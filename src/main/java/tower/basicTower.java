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
	
	private static final String towerType = "basicTower";

	/**
	 * Constructs a basicTower that can be placed on the screen The Coord argument
	 * must be an integer array of size 2 which specifies the x coordinate and the
	 * y-coordinate.
	 * 
	 * @param Coord coordinate of basicTower where is created
	 */
	public basicTower(int[] Coord) {
		super(Coord, initialCost, initialPower, initialRange, towerType);
	}

	/**
	 * Returns the upgrade cost of the given basicTower.
	 * 
	 * @return the upgrade cost of the basicTower
	 */
	public int getUpgradeCost() {
		return upgradeCost;
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
	public int getRangeIncrement() {
		return rangeIncrement;
	}

	// Made Some change here
}