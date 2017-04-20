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

    public static String USER = "";
    public static String PWD = "";
    public static String DB = "";
    public static String PORT = "";
    public static String HOST = "";

    public static boolean empresa = false;
    public static String RUC = "";

    public final static String defenterpise = "20428776110";
    public static String enterprise = "";
    public static String RESOURCE = "";

    public static void setRESOURCE(String rESOURCE) {
        RESOURCE = String.format("%s%s", rESOURCE, SEPARATOR);
    }

    public static char SEPARATOR = File.separatorChar;

    private static final void loadData(String denterprise){
        new MangerFiles().readConfig(denterprise);
    }

    public static void loadData(){
        if (enterprise.isEmpty())
            loadData(defenterpise);
        else
            loadData(enterprise);
    }

}
