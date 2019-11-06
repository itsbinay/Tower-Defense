package sample;

public class Helper {
	public static String preProcessing(String id) {
		String url;
		String removeLabel = id.substring(5,id.length());
		String uncapitalizeF = removeLabel.substring(0,1).toLowerCase() + removeLabel.substring(1);
		url = uncapitalizeF +".png";
		return url;
	}
}
