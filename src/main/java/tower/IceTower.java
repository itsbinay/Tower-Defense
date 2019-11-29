package tower;

/**
 * This is a IceTower class to model a IceTower-type Tower
 * This class inherits the abstract class Tower
 * @author Binay 
 */
public class IceTower extends Tower {
    private static final int upgradeCost = 100;
    private static final int freezeIncrement = 1;
    private static final int rangeIncrement = 10;
    
    private static final int initialCost = 90;
    private static final int initialPower = 20;
    private static final int initialRange = 100;
    private static final int initialCDTimer=3;
    
    private static final String towerType = "iceTower";
    private int FreezeTimer = 3;	//This is in terms of frames
    
    /**
     * Constructor of the IceTower
     * @param Coord coordinate of where the Catapult is built (the top left corner of the grid)
     */
    public IceTower(int []Coord){
        super(Coord,initialCost,initialPower,initialRange, towerType,upgradeCost,initialCDTimer);
    }
    
    @Override
    public void upgradeTower(boolean canUpgrade) {	
    	if(!canUpgrade)return;
    	
        this.setRange(this.getRange() + rangeIncrement);
        this.FreezeTimer +=  freezeIncrement;
    }
    
    /**
     * Returns the number of frames the tower freezes the monster
     * 
     * @return returns the frames this tower freezes
     */
    public int getFreezeTimer() {
    	return this.FreezeTimer;
    }
    /**
	* Gets the hp of the monster, attacks it and then returns the new monster hp
	* 
	* @param hp initial hp of the monster before being attacked
	* @return returns the the new HP of the monster attacked
	*/
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			//System.out.println("IceTower("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}

}
