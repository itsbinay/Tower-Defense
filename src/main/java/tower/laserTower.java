package tower;

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
	/**
	 * Constructor of the laserTower
	 * @param Coord coordinate of where the Catapult is built (the top left corner of the grid)
	 */
	public laserTower(int [] Coord){
		super(Coord,initialCost,initialPower,initialRange, towerType,upgradeCost,initialCDTimer);
	}
	@Override
	public void upgradeTower(boolean canUpgrade){
		if(!canUpgrade)return;
		
		this.setPower(this.getPower()+powerIncrement);
		this.setRange(this.getPower()+rangeIncrement);
	}
	@Override
	public int getAttackCost() {
		return this.attackCost;
	}
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
	public String getTowerType() {
		return towerType;
	}
	public int getRangeIncrement() {
		return rangeIncrement;
	}
}
