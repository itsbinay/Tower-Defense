package sample;

import java.util.List;

import javafx.scene.Node;
import tower.Catapult;
import tower.IceTower;
import tower.Tower;
import tower.basicTower;
import tower.laserTower;

/**
 * helper functions class
 */
public class Helper {
	/**
	 * Default Constructor for Helper class
	 */
	public Helper() {
		
	}
	/**
	 * This function returns the a substring of the parameter id
	 * @param id id the id of each tower label (labelBasicTower/ labelLaserTower/ labelIceTower/ labelCatapult)
	 * @return returns a substring of id by removing the "label" part of the string
	 */
	public static String labelProcessing(String id) {

		String removeLabel = id.substring(5,id.length());

		return removeLabel;
	}
	/**
	 * This function processes the id of each tower label to the image url
	 * @param id the id of each tower label (labelBasicTower/ labelLaserTower/ labelIceTower/ labelCatapult)
	 * @return the name of the tower image png file 
	 */
	public static String preProcessing(String id) {
		String url;
		String removeLabel = id.substring(5,id.length());
		String uncapitalizeF = removeLabel.substring(0,1).toLowerCase() + removeLabel.substring(1);
		url = uncapitalizeF +".png";
		return url;
	}
	/**
	 * This function gets the tower name using image url
	 * @param s image url of the png image of each tower
	 * @return returns the tower name (iceTower/ basicTower/ laserTower/ catapult)
	 */ 
	public static String getTowerName(String s) {
		String a = new String();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='.')break;
			a+=s.charAt(i);
		}
		return a;
	}
	
	/**
	 * This function returns the tower inside the given parameter Towers based on the parameter Coord of where the tower is placed on the map in terms of pixels
	 * @param Coord an int array representing the coordinates of the tower on the map in terms of pixels
	 * @param Towers a list of towers built on the map
	 * @return returns the Tower based on the given parameter Coord of where the tower is on the map
	 */ 
	public static Tower returnTower(int []Coord, List<Tower> Towers) {
		for(Tower a : Towers) {
			int []aCoor = a.getCoord();
			int aX = aCoor[0];
			int aY = aCoor[1];
			if(Coord[0] == aX && Coord[1] == aY) {
				return a;
			}
		}
		return null;
	}
	
	 /**
     * Returns the index of the tower in the towers list, given the coordinate
     * if tower doesn't exist 0 is returned 
     * 
     * @param coord The Coordinate of the label
     * @param towers The list of towers from where to find the index
     * @return	the index of where the Tower is
     */
	
	public static int getTowerIndex(int[] coord, List<Tower> towers) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getCoord()[0] == coord[0] && towers.get(i).getCoord()[1] == coord[1])
                return i;
        }
        return 0;
    }
	/**
     * This function returns the string of the tower name used to be represented for showing the tower stats
     * 
     * @param name name of each tower without the substring Tower next to it
     * @return	a string of the tower name (Basic Tower/Ice Tower/catapult/Laser Tower)
     */
	
	public static String space(String name) {			
		if(!name.equals("catapult")) {
			String k;
			String n;
			String m;
			k = name.substring(0, 1).toUpperCase();
			n = name.substring(1, name.indexOf("Tower"));
			m = k + n + " " + "Tower";	
			return m;
		}
		else {
			return name;
		}
	}
}