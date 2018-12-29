package pub.liyf.utils;

public class CommonUtil {
    public static String spiltLine = "----------------------------------------";

    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
