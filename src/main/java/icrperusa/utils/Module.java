/**
 *
 */
package icrperusa.utils;

import java.io.File;

/**
 * @author christian
 *
 */
public class Module {

    public static String USER = "postgres";
    public static String PWD = "Syst3mH3ll";
    public static String DB = "erpicrperu";

    public static final String USERINST = "";
    public static final String DBINST = "erpicrinstalaciones";

    public static boolean empresa = false;
    public static String RUC = "";
    public static String HOST = "localhost";

    public static String RESOURCE = "";

    public static void setRESOURCE(String rESOURCE) {
        RESOURCE = String.format("%s%s", rESOURCE, SEPARATOR);
    }

    public static char SEPARATOR = File.separatorChar;

    public static void usePERU(){
        USER = "postgres";
        PWD = "Syst3mH3ll";
        DB = "erpicrperu";
        RUC = "20428776110";
    }

    public static void useINSTALLATION(){
        USER = "postgres";
        PWD = "Syst3mH3ll";
        DB = "erpicrinstalaciones";
        RUC = "20555029277";
    }

}
