package tower;
 
public class Catapult extends Tower {
	private static final int upgradeCost = 100;
	private static final int powerIncrement = 40;
	private static final int rangeIncrement = 40;
	
	private static final int initialCost = 80;
	private static final int initialPower = 50;
	private static final int initialRange = 200;
	private static final int initialCDTimer=4;
	
	public Catapult(int []Coord) {
		super(Coord,initialCost,initialPower,initialRange,initialCDTimer,upgradeCost);
		System.out.println("Catapult constructed");
	}
	
	@Override
	public void upgradeTower(boolean canUpgrade) {
		if(!canUpgrade)return;
		
		this.setPower(this.getPower() + powerIncrement);
		this.setRange(this.getRange() + rangeIncrement);
	}
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			System.out.println("Catapult("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}
	public String getTowerType() {
		return "catapult";
	}
}
