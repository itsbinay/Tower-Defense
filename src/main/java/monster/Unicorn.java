package monster;

/**
 * This is a Unicorn class that is based on the abstract Monster class.
 * @author apple
 *
 */

public class Unicorn extends Monster{

	private int hp = 150;
	private int movementSpeed = 1;
	private static String img = "unicorn.png";
	private static final int resourceEarn = 50;
	
	public Unicorn(int [] coord,int hp, int movementSpeed, int frozen) {
		super(coord,hp, movementSpeed,img, frozen,resourceEarn,"Unicorn");
		
	}


	

}
