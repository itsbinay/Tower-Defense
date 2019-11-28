package monster;


/**
 * This is a fox class that is based on the abstract Monster class.
 * @author apple
 *
 */

public class Fox extends Monster{
	private int hp = 100;
	private int movementSpeed = 2;
	private static String img = "fox.png";
	private static final int resourceEarn = 50;
	/**
	 * 
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	public Fox(int [] coord,int hp, int movementSpeed, int frozen)  {
		super(coord,hp, movementSpeed,img, frozen,resourceEarn,"Fox");
		
	}


	


}
