package monster;

/**
 * This is a Unicorn class that is based on the abstract Monster class.
 * @author apple
 *
 */

public class Unicorn extends Monster{

	private static int hp = 150;
	private static int movementSpeed = 1;
	private static String img = "unicorn.png";
	private static final int resourceEarn = 20;
	/** 
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	public Unicorn(int [] coord, boolean frozen) {
		super(coord,hp, movementSpeed,img, frozen,resourceEarn);
		// TODO Auto-generated constructor stub
	}


	

}
