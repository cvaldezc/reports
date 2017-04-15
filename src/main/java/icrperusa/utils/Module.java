/**
 * 
 */
package icrperusa.utils;

/**
 * @author christian
 *
 */
public class Module {

	public static String USER = "postgres";
    public static String PWD = "Syst3mH3ll";
    public static String DB = "erpicrperu";
    // public static final String DB = "dbicrperu";
    
    public static final String USERINST = "";
    public static final String PWDINST = "";
    public static final String DBINST = "erpicrinstalaciones";
    
    public static boolean empresa = false;
    public static String RUC = "";
    public static String HOST = "localhost";
    
    public static void usePERU(){
    	USER = "postgres";
        PWD = "Syst3mH3ll";
        DB = "erpicrperu";
    }
    
    public static void useINSTALLATION(){
    	USER = "postgres";
        PWD = "Syst3mH3ll";
        DB = "erpicrinstalaciones";
    }
	
}
