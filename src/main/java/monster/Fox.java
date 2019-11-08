package monster;


/**
 * This is a fox class that is based on the abstract Monster class.
 * @author apple
 *
 */

public class Fox extends Monster{
	private static int hp = 100;
	private static int movementSpeed = 3;
	
	/**
	 * 
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	public Fox(int [] coord, boolean frozen) {
		super(coord,hp, movementSpeed, frozen);
		// TODO Auto-generated constructor stub
	}


	


}
