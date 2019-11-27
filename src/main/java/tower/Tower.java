package tower;
import java.lang.Math;

/**
 * This is an abstract class model for all types of tower.
 * @author binay
 *
 */
public abstract class Tower {
	
	/**
	 * an Enum to inidicate the current state of the Tower
	 * @author binay
	 *
	 */

	public enum TowerState{
		READY,
		COOLDOWN,
		ATTACK
	};
	
	private int buildingCost;
	private int power;
	private int shootingRange;
	private int[] coord;
	private TowerState curState=TowerState.READY;
	private int cooldownTimer;
	private int maxcdTimer;
	private int upgradeCost;
	
	private String towerType;
	
	/**
	 * This is an abstract class constructor which will base structure that will be inherited 
	 * by all of its children class. A "Tower" class object cannot be constructed.
	 * This is a Tower class constructor that helps to initialise all of the base class data members.
	 * The Tower class helps to generalise and group all common attributes among all towers.
	 * 
	 * @param Coord	coordinate of basicTower where is created
	 * @param cost	the cost of building the Tower
	 * @param power	the initial power of the tower built
	 * @param range	the initial range of the tower built
	 */
	public Tower(int []Coord,int cost,int power, int range, String towerType,int UpgradeCost,int cdTimer) {
		this.buildingCost = cost;
		this.power = power;
		this.shootingRange = range;
		this.coord = new int[2];
		this.coord[0] = Coord[0];
		this.coord[1] = Coord[1];
		this.cooldownTimer=1;
		this.maxcdTimer=cdTimer;
		this.upgradeCost=UpgradeCost;
		this.towerType = towerType;
	}
	
	public int getUpgradeCost() {
		return this.upgradeCost;
	}
	/**
	 * Retuns the cooldown timer of the tower.
	 * the cooldown of the Timer is lowered if the timer value is greater than 1
	 * The timer of the tower shall never be lower than 1.
	 * cooldownTimer==1 indciates that the tower can attack in the next turn
	 * 
	 * @return the cooldown timer of the tower
	 */
	public void updateTowerState() {
		if(this.curState==TowerState.ATTACK) {	//If you have attacked recently
			this.curState=TowerState.COOLDOWN;
			this.cooldownTimer=this.maxcdTimer;
			return;
		}
		this.cooldownTimer--;
		if(this.cooldownTimer<=1) {
			this.cooldownTimer=1;
			this.curState=TowerState.READY;
		}
	}
	public int getMaxCDTimer(){
		return this.maxcdTimer;
	}
	public void setMaxCDTimer(int a){
		if(a<=1)return;
		this.maxcdTimer=a;
	}
	public void setCooldown(int cd) {
		this.cooldownTimer=cd;
	}
	public int getCooldown() {
		return this.cooldownTimer;
	}
	
	public String getTowerType () {
		return this.towerType;
	}
	/**
	 * Returns the building cost of the Tower.
	 * 
	 * @return returns the cost of Tower
	 */
	public int getTowerCost () {
		return this.buildingCost;
	}
	/**
	 * Returns the power of the tower.
	 * 
	 * @return	the power of the tower
	 */
	public int getPower() {
		return this.power;
	}
	/**
	 * Returns the range of the tower.
	 * 
	 * @return	the range of the tower
	 */
	public int getRange(){
		return this.shootingRange;
	}
	/**
	 * Returns the coordinate of where the tower was built.
	 * 
	 * @return the coordinate of the tower
	 */
	public int [] getCoord(){
		return this.coord;
	}
	/**
	 * Sets the power variable of the base class according to the given parameter "power".
	 * @param power	the new power of the tower
	 */
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * Sets the range variable of the base class according to the given parameter "range"
	 * @param range the new range of the tower
	 */
	public void setRange(int range) {
		this.shootingRange = range;
	}
	/**
	 * The function takes a coordinate of an object and indicates 
	 * whether it is in the range of the tower.
	 * @param coord the coordinate of the object to see if it is in range
	 * @return	whether the object of the given coordinate is in range
	 */
	public boolean isInRange(int [] coord) {
        int [] towerCoord = this.getCoord();
        
        double distance = Math.pow((coord[0]-towerCoord[0]),2)+Math.pow((coord[1]-towerCoord[1]),2);
        //System.out.println("Distance:"+distance+" Range:"+this.getRange());
        return (distance<=Math.pow(this.getRange(),2))?true:false;
    }
	public String getStateStr() {
		switch(this.curState) {
			case READY: return "Ready";
			case COOLDOWN:return "Cooldown";
			case ATTACK:return "Attack";
			default: return "Cant find state";
		}
	}
	/**
	 * Returns the state of the selected tower
	 * 
	 * @return the state of the selected tower
	 */
	public TowerState getTowerState() {
		return curState;
	}
	/**
	 * The function updates the state of the tower.
	 * 
	 * @param newState the newState of the tower to be set
	 */
	public void setTowerState(TowerState newState) {
		this.curState=newState;
	}
	/**
	 * If the canUpgrade argument is evaluates to be true, the power and the range of the 
     * basicTower will be incremented.
     * 
	 * @param canUpgrade canUpgrade this parameter indicates whether the basicTower can be upgraded
	 */
	public abstract void upgradeTower(boolean canUpgrade);
	public abstract int attack(int hp);
	//public abstract String getTowerType();
	//public abstract int getUpgradeCost();
	public int getAttackCost() {
		return 0;
	}
	public int getMinRange(){
		return 0;
	}
}
