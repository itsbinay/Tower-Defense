package tower;

public class basicTower extends Tower{
	private static final int upgradeCost = 50;
	private static final int powerIncrement = 5;
	private static final int rangeIncrement = 5;
	
	private static final int initialCost = 60;
	private static final int initialPower = 50;
	private static final int initialRange = 70;
	
    public basicTower(int []Coord){
        super(Coord,initialCost,initialPower,initialRange);
    }
    public int getUpgradeCost(){
        return upgradeCost;
    }
    @Override
    public void upgradeTower(boolean canUpgrade) {
    	if(!canUpgrade)return;
    	
        this.setPower(this.getPower() + powerIncrement);
        this.setRange(this.getRange() + rangeIncrement);
    }
    //Made Some change here
}