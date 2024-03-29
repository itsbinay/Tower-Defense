/**
 * Provides the classes necessary to construct monsters.
 */
package monster;

/**
 * This is an abstract class model for all types of monsters.
 * 
 * @author apple
 *
 */

public class Monster {
	/**
	 * an Enum to inidicate the current state of the Monster
	 */

	public enum MonsterState {
		/**
		 * The monster is currently Frozen
		 */
		FROZEN,
		/**
		 * The monster is currently Targeted
		 */
		TARGETED,
		/**
		 * The monster is currently Untouched
		 */
		UNTOUCHED,
		/**
		 * The monster is currently Damaged
		 */
		DAMAGED
	}

	private int movementSpeed;
	private int hp;
	private int frozen;
	private String img;
	private int deathResource;
	private int[] coord = { 0, 0 };

	private MonsterState curState = MonsterState.UNTOUCHED;

	private String monsterTypeStr;

	/**
	 * Base Class Constructor for Monster
	 * 
	 * @param coord         The x and y coordinates for each monster object.
	 * @param hp            The health points that each monster obtains when
	 *                      created.
	 * @param movementSpeed The maximum number of squares on the grid the monster
	 *                      can move.
	 * @param frozen        The status of monster that illustrates if a monster is
	 *                      frozen or not.
	 * @param resourceEarn  The amount of resource given when the monster is killed.
	 * @param monsterType   The type of monster (fox, unicorn and penguin).
	 * @param img           The name of the .png file of the specific monster type.
	 */
	public Monster(int[] coord, int hp, int movementSpeed, String img, int frozen, int resourceEarn,
			String monsterType) {
		this.movementSpeed = movementSpeed;
		this.hp = hp;
		this.frozen = frozen;
		this.coord = coord;
		this.img = img;
		this.deathResource = resourceEarn;
		this.monsterTypeStr = monsterType;
	}

	/**
	 * Returns the type of monster (fox,penguin and Unicorn).
	 * 
	 * @return returns the type of monster (fox,penguin and Unicorn).
	 */
	public String getMonsterType() {
		return this.monsterTypeStr;
	}

	/**
	 * Returns the resource gained from the monster dying.
	 * 
	 * @return returns the resource gained from the monster dying.
	 */
	public int getResourceEarned() {
		return this.deathResource;
	}

	/**
	 * Returns the current state of the monster.
	 * 
	 * @return returns the current state of the monster.
	 */
	public MonsterState getMonsterState() {
		return curState;
	}

	/**
	 * Set the new state of the monster
	 * 
	 * @param newState the new state of the monster.
	 */

	public void setMonsterState(MonsterState newState) {
		this.curState = newState;
	}

	/**
	 * Returns the movement speed of the monster.
	 * 
	 * @return returns the movement speed of the monster.
	 */

	public int getMovementSpeed() {
		return this.movementSpeed;
	}

	/**
	 * Returns the name of .png file of the specific monster
	 * 
	 * @return returns the name of .png file of the specific monster.
	 */
	public String getImg() {
		return this.img;
	}

	/**
	 * Returns the coordinate of the monster
	 * 
	 * @return the coordinate of the monster
	 */
	public int[] getCoord() {
		int[] coordinate = { this.getX() * 40, this.getY() * 40 };
		return coordinate;
	}

	/**
	 * Returns the y-coordinate of the monnster
	 * 
	 * @return returns the y-coordinate of the monster.
	 */
	public int getY() {
		return this.coord[0];
	}

	/**
	 * Regenerate the current hp by 10 - only for the penguin, if injured
	 */
	public void regenerate() {

		this.hp += 10;

	}

	/**
	 * Returns the x-coordinate of the monster
	 * 
	 * @return returns the x-coordinate of the monster.
	 */
	public int getX() {
		return this.coord[1];
	}

	/**
	 * Sets the coordinate of the monster with the given coordinate
	 * 
	 * @param coord The current coordinates of the monster.
	 */
	public void setYX(int[] coord) {
		this.coord = coord;
	}

	/**
	 * Returns the health point (hp) of the monster
	 * 
	 * @return returns the health point of the monster
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * Sets the new hp of the monster
	 * 
	 * @param hp the new hp of the monster.
	 */

	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Returns the frozen status (int) of the monster
	 * 
	 * @return returns frozen status of the monster
	 */

	public int getFrozen() {
		return this.frozen;
	}

	/**
	 * Sets the frozen state of the monster with the given int val
	 * 
	 * @param a the integer to represent if the monster is frozen or not.
	 */
	public void setFrozen(int a) {
		this.frozen = a;
	}

	/**
	 * Reduces the movement speed of the monster if its status is frozen.
	 */
	public void reduceSpeed() {

		if (this.frozen > 0) {

			if (this.img == "fox.png") {
				if (this.movementSpeed >= 2)

					this.movementSpeed = this.movementSpeed - 1;

			}

			if (this.img == "unicorn.png" || this.img == "penguin.png") {
				if (this.movementSpeed >= 1)

					this.movementSpeed = this.movementSpeed - 1;

			}

			--this.frozen;

		}
	}

	/**
	 * Unfreezes the monster if it was previous frozen
	 */

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
