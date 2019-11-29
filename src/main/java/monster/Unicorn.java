package monster;

/**
 * This is a Unicorn class that is based on the abstract Monster class.
 * 
 * @author apple
 *
 */

public class Unicorn extends Monster {

	private int hp = 150;
	private int movementSpeed = 1;
	private static String img = "unicorn.png";
	private static final int resourceEarn = 50;

	/**
	 * Constructor to instantiate Unicorn object
	 * 
	 * @param coord         The x and y coordinates for each monster object.
	 * @param frozen        The status of monster that illustrates if a monster is
	 *                      frozen or not.
	 * @param movementSpeed the number of grids the monster can move each turn.
	 * @param hp            the current health point of the monster.
	 * 
	 */

	public Unicorn(int[] coord, int hp, int movementSpeed, int frozen) {
		super(coord, hp, movementSpeed, img, frozen, resourceEarn, "Unicorn");

	}

}
