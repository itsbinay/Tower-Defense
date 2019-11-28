package monster;



/**
 * This is an abstract class model for all types of monsters.
 * 
 * @author apple
 *
 */

public class Monster {

	public enum MonsterState {
		FROZEN, TARGETED, UNTOUCHED, DAMAGED,
	}

	private int movementSpeed;
	private int hp;
	private int frozen;
	private String img;
	private int deathResource;
	private int[] coord = { 0, 0 };


	private MonsterState curState= MonsterState.UNTOUCHED;

	private String monsterTypeStr;
	

	public Monster(int[] coord, int hp, int movementSpeed, String img, int frozen, int resourceEarn,String monsterType) {
		this.movementSpeed = movementSpeed;
		this.hp = hp;
		this.frozen = frozen;
		this.coord = coord;
		this.img = img;
		this.deathResource = resourceEarn;
		this.monsterTypeStr=monsterType;
	}




	public String getMonsterType(){
		return this.monsterTypeStr;
	}

	public int getResourceEarned() {
		return this.deathResource;
	}
	public MonsterState getMonsterState() {
		return curState;
	}
	public void setMonsterState(MonsterState newState) {
		this.curState=newState;
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
	public int[] getCoord() {
		int[] coordinate = { this.getX() * 40, this.getY() * 40 };
		return coordinate;
	}

	/**
	 * @return returns the x-coordinate of the monster.
	 */
	public int getY() {
		return this.coord[0];
	}

	public void regenerate() {

		this.hp += 10;

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

	public int getFrozen() {
		return this.frozen;
	}

	public void setFrozen(int a) {
		this.frozen = a;
	}

	/**
	 * reduces the movement speed of the monster if its status is frozen.
	 */
	public void reduceSpeed() {

		if (this.frozen > 0) {

			if (this.img == "fox.png") {
				if (this.movementSpeed == 2)

					this.movementSpeed = this.movementSpeed - 1;

			}

			if (this.img == "unicorn.png" || this.img == "penguin.png") {
				if (this.movementSpeed == 1)

					this.movementSpeed = this.movementSpeed - 1;

			}

			--this.frozen;

		}
	}

	public void unFreeze() {

		if (this.frozen == 0) {

			if (this.img == "fox.png") {
				if (this.movementSpeed != 2)

					this.movementSpeed = 2;

			}

			if (this.img == "unicorn.png" || this.img == "penguin.png") {
				if (this.movementSpeed != 1)

					this.movementSpeed = 1;

			}

		}

	}

}
