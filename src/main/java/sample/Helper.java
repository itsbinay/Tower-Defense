package sample;

public class Helper {
	private int help;
	/**
	 * Default constructor
	 * This class is to hold helper functions for mycontroller
	 * @param r integer variable
	 */
	public Helper(int r) {
		this.help = r;
		
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
	public static String getTowerName(String s) {
		String a = new String();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='.')break;
			a+=s.charAt(i);
		}
		return a;
	}
}
