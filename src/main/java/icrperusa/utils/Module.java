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
        return new MangerFiles().readConfig(denterprise);
    }

    public static Map<String, Object> loadConfig(String RUC){
        if (config.containsKey(RUC))
            return config.get(RUC);
        else if (!RUC.isEmpty()){
            config.put(RUC, loadData(RUC));
            return loadConfig(RUC);
        }else{
            config.put(RUC, loadData(defenterpise));
            return loadConfig(RUC);
        }
    }

    public static void cleanConfig()
    {
        config = new HashMap<String, Map<String, Object>>();
    }
}
