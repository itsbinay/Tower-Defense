package tower;

public class laserTower extends Tower {
	private static final int upgradeCost = 120;
	private static final int powerIncrement = 120;
	private static final int rangeIncrement = 50;

	private static final int attackCost = 20;
	private static final int initialCost = 180;
	private static final int initialPower = 180;
	private static final int initialRange = 50;
	private static final String towerType = "laserTower";
	private static final int initialCDTimer=3;
	public laserTower(int [] initialCoord){
		super(initialCoord,initialCost,initialPower,initialRange, towerType,upgradeCost,initialCDTimer);
	}
	@Override
	public void upgradeTower(boolean canUpgrade){
		if(!canUpgrade)return;
		
		this.setPower(this.getPower()+powerIncrement);
		this.setRange(this.getPower()+rangeIncrement);
	}
	
	
	public int getAttackCost() {
		return this.attackCost;
	}
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			System.out.println("laserTower("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}
	public String getTowerType() {
		return "laserTower";
	}
}
