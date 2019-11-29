package monster;

import java.util.Random;

/**
 * Monster Factory class that will help to instantiate new Monsters for the game
 */
public class MonsterFactory{

    /**
     * Constructor for Monster Factory
     */
    public MonsterFactory(){

    }
    private Random r = new Random();
    private int[] startCoord={0,0};
    
    /**
     * Generate Monster from the Monster Factory and return it
     * @return newly generated Monster from the Monster Factory
     */
    public Monster generateMonster(){
        int random = r.nextInt(3-1+1)+1+1;

        switch(random){
            case 1:
                return new Fox(startCoord,100,2,0);
            case 2:
                return new Unicorn(startCoord,150,1,0);
            case 3:
                return new Penguin(startCoord,100,1,0);
            default:
                return new Fox(startCoord,100,2,0);
        }
    }
}