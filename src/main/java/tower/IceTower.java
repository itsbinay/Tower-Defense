package tower;

public class IceTower extends Tower {
    private static final int upgradeCost = 100;
    private static final int freezeIncrement = 1;
    private static final int rangeIncrement = 10;
    
    private static final int initialCost = 90;
    private static final int initialPower = 20;
    private static final int initialRange = 50;
    private static final int initialCDTimer=1;
    
    private int FreezeTimer = 3;	//This is in terms of frames
    
    public IceTower(int []Coord){
        super(Coord,initialCost,initialPower,initialRange,initialCDTimer);
        System.out.println("iceTower constructed");
    }
    
    @Override
    public void upgradeTower(boolean canUpgrade) {	
    	if(!canUpgrade)return;
    	
        this.setRange(this.getRange() + rangeIncrement);
        this.FreezeTimer +=  freezeIncrement;
    }
    
    public int getFreezeTimer() {
    	return this.FreezeTimer;
    }
    
    public int upgradeCost() {
    	return upgradeCost;
    }
    
	public int attack(int hp) {
		if(this.getTowerState()==TowerState.READY) {
			hp-=this.getPower();
			this.setCooldown(this.initialCDTimer);
			this.setTowerState(TowerState.ATTACK);
			System.out.println("IceTower("+this.getCoord()[0]+","+this.getCoord()[1]+") attacked");
			return hp;
		}
		return hp;
	}
	public String getTowerType() {
		return "iceTower";
	}
}
