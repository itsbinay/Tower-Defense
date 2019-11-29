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
	private static final int resourceEarn = 50;
	

	/**
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 * @param movementSpeed the number of grids the monster can move each turn.
	 * @param hp the current health point of the monster. 
	 *  
	 */

	
	public Penguin(int [] coord,int hp, int movementSpeed, int frozen)  {
		super(coord,hp, movementSpeed, img, frozen,resourceEarn,"Penguin");
		
	}




	

}
