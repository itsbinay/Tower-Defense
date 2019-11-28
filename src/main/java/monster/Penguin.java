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
	
	
	public Penguin(int [] coord,int hp, int movementSpeed, int frozen)  {
		super(coord,hp, movementSpeed, img, frozen,resourceEarn,"Penguin");
		
	}

	/**
	 *  increases the health points of this monster(penguin) by 5 if its health points is below 100.
	 */


	

}
