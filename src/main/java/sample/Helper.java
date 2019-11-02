package sample;

public class Helper {
    
    public static String getURL(String x){
    	String url;
        //boolean parseNow=false;
        if(x.contains("url")){
            String substr1=x.substring(x.indexOf("url"));
            url=substr1.substring(substr1.indexOf('(')+2,substr1.indexOf(')')-1);
            return url;
        }
        return x;
    }
}
