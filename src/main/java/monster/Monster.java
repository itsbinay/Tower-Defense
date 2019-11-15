package monster;

/**
 * This is an abstract class model for all types of monsters.
 * @author apple
 *
 */

public class Monster {
	
	public enum MonsterState{
		FROZEN,
		TARGETED
	}
	private int movementSpeed;
	private int hp;
	private boolean frozen;
	private String img;
	private int deathResource;
	private int[] coord = {0,0};
	
	/**
	 * @param coord The x and y coordinates for each monster object.
	 * @param hp The health points that each monster obtains when created.
	 * @param movementSpeed The maximum number of squares on the grid the monster can move. 
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	
	public Monster(int[]coord,int hp, int movementSpeed,String img, boolean frozen,int resourceEarn) {
		this.movementSpeed = movementSpeed;
		this.hp = hp;
		this.frozen = frozen;
		this.coord = coord;
		this.img = img;
		this.deathResource=resourceEarn;
	}
	public int getResourceEarned(){
		return this.deathResource;
	}
	/**
	 * @return returns the movement speed of the monster.
	 */

	public int getMovementSpeed() {
		return this.movementSpeed;
	}
	public String getImg() {
		return this.img;
	}
	
	/**
	 * 
	 * @return the coordinate of the monster
	 */
	public int [] getCoord() {
		int [] coordinate= {this.getX()*40,this.getY()*40};
		return coordinate;
	}
	/** 
	 * @return returns the x-coordinate of the monster.
	 */
	public int getY() {	
		return this.coord[0];
	}
	/** 
	 * @return returns the y-coordinate of the monster.
	 */
	public int getX() {	
		return this.coord[1];
	}
	/**
	 * @param coord The current coordinates of the monster. 
	 */
	public void setYX(int[] coord) {
		this.coord = coord;
	}
	
/**
 * 
 * @return returns the health point of the monster
 */
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
	/**
	 * 
	 * @return returns frozen status of the monster
	 */
	
	public boolean getFrozen() {
		return this.frozen;
	}
	public void setFrozen() {
		this.frozen=true;
	}
	
	/**
	 * reduces the movement speed of the monster if its status is frozen.
	 */
	public void reduceSpeed() {
		
		if(this.frozen == true) {
			
			this.movementSpeed = this.movementSpeed-5;
			
		}
	}
		public void unFreeze() {
			
			if(this.frozen == true) {
				
				this.movementSpeed = this.movementSpeed+5;
				this.frozen = false;
			}
		
		
		
	}
	

	

	
	
	
	
	
	
}
