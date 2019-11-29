package tower;
 
/**
 * This is a Catapult class to model a catapult-type Tower
 * This class inherits the abstract class Tower
 * @author Binay 
 */
public class Catapult extends Tower {
	private static final int upgradeCost = 100;
	
	private static final int initialCost = 80;
	private static final int initialPower = 60;
	private static final int initialRange = 150;
	private static final int minimumRange = 50;
	private static final int rangeIncrement = 10;
	private static final String towerType = "catapult";
	private static final int initialCDTimer=3;
	private static final int MAXUpgradeLimit = 4;
	
	private int upgradeCount = 0;
	/**
	 * Constructor of Catapult
	 * @param Coord coordinate of where the Catapult is built (the top left corner of the grid)
	 */
	public Catapult(int []Coord) {
		super(Coord,initialCost,initialPower,initialRange, towerType,upgradeCost,initialCDTimer);
	}
	
	
	@Override
	public void upgradeTower(boolean canUpgrade) {
		if(!canUpgrade || upgradeCount>=MAXUpgradeLimit)return;
		
		upgradeCount++;
		this.setRange(this.getRange()+rangeIncrement);
		if(this.getMaxCDTimer()>1){
			this.setMaxCDTimer(this.getMaxCDTimer()-1);
		}
	}
	/**
	 * Returns the minimum range of the catapult
	 * 
	 * @return the minimum range of the Catapult
	 */
	@Override
	public int getMinRange(){
		return this.minimumRange;
	}

	@Override
	public boolean isInRange(int [] coord) {
        int [] towerCoord = this.getCoord();
		int minRange = minimumRange*minimumRange;
		int maxRange = this.getRange()*this.getRange();

        double distance = Math.pow((coord[0]-towerCoord[0]),2)+Math.pow((coord[1]-towerCoord[1]),2);
		//System.out.println("Distance:"+distance+" Range:"+this.getRange());
		if(distance<=maxRange && distance>=minRange)return true;
        return false;
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
			//System.out.println("Catapult("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}

}
