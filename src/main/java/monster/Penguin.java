package monster;


/**
 * This is a penguin class that is based on the abstract Monster class.
 * @author apple
 *
 */
public class Penguin extends Monster{

	private static int hp = 100;
	private static int movementSpeed = 3;
	
	
	/**
	 * @param coord The x and y coordinates for each monster object.
	 * @param frozen The status of monster that illustrates if a monster is frozen or not.
	 */
	public Penguin(int[] coord, boolean frozen) {
		super(coord,hp, movementSpeed, frozen);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  increases the health points of this monster(penguin) by 5 if its health points is below 100.
	 */

	public void regenerate() {
		if (this.hp< 100) {
			this.hp += 5;
		}
 		
	}
	

}
