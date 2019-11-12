package monster;


/**
 * This is a penguin class that is based on the abstract Monster class.
 * @author apple
 *
 */
public class Penguin extends Monster{

	private  int hp = 100;
	private  int movementSpeed = 1;
	private static String img = "penguin.png";
	private static final int resourceEarn = 35;
	
	/**
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	public Penguin(int [] coord,int hp, int movementSpeed, int frozen)  {
		super(coord,hp, movementSpeed, img, frozen,resourceEarn);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  increases the health points of this monster(penguin) by 5 if its health points is below 100.
	 */


	

}
