package sample;

import java.util.List;

import javafx.scene.Node;
import tower.Catapult;
import tower.IceTower;
import tower.Tower;
import tower.basicTower;
import tower.laserTower;

public class Helper {
	public static String labelProcessing(String id) {

		String removeLabel = id.substring(5,id.length());

		return removeLabel;
	}
	/**
	 * 
	 * @param id
	 * @return url
	 */
	public static String preProcessing(String id) {
		String url;
		String removeLabel = id.substring(5,id.length());
		String uncapitalizeF = removeLabel.substring(0,1).toLowerCase() + removeLabel.substring(1);
		url = uncapitalizeF +".png";
		return url;
	}
	/**
	 * Get Tower name using image url
	 * @param s
	 * @return
	 */
	public static String getTowerName(String s) {
		String a = new String();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='.')break;
			a+=s.charAt(i);
		}
		return a;
	}
	
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
	
	public static String space(String name) {
		if(!name.equals("catapult")){
			String k;
			String n;
			String m;
			k = name.substring(0, 1).toUpperCase();
			n = name.substring(1, name.indexOf("Tower"));
			m = k + n + " " + "Tower";
			return m;
		}	
		return name;
	}
}
