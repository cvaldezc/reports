/**
 *
 */
package icrperusa.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Module {

    private static Map<String, Map<String, Object>> config = new HashMap<String, Map<String, Object>>();

    public final static String defenterpise = "20428776110";

    public static char SEPARATOR = File.separatorChar;

    private static final Map<String, Object> loadData(String denterprise){
        System.out.println("READ FILE CONFIG");
        return new ManagerFiles().readConfig(denterprise);
    }

    public static Map<String, Object> loadConfig(String RUC){
        System.out.println("PATH SETTINGS " + UPLOAD_PATH());
        if (config.containsKey(RUC)){
            System.out.println("GET RUC CONFIG");
            return config.get(RUC);
        } else if (!RUC.isEmpty()){
            config.put(RUC, loadData(RUC));
            System.out.println("GET RUC CONFIG EMPTY");
            return loadConfig(RUC);
        }else{
            System.out.println("GET RUC CONFIG DEFAULT");
            config.put(RUC, loadData(defenterpise));
            return loadConfig(RUC);
        }
    }

    public static final String UPLOAD_PATH(){
        String UPLOAD_PATH = "";
        if (System.getProperty("os.name").equals("Linux"))
            UPLOAD_PATH = "/mnt/webapp/reports";
        else
            UPLOAD_PATH = "C:\\webapp\\reports";
        UPLOAD_PATH = UPLOAD_PATH.concat(String.valueOf(Module.SEPARATOR));
        return UPLOAD_PATH;
    }

    public static void cleanConfig()
    {
        config = new HashMap<String, Map<String, Object>>();
    }
}
