/**
 *
 */
package icrperusa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import icrperusa.interfaces.utils.IManagerFile;

/**
 * @author christian
 *
 */
public class ManagerFiles implements IManagerFile {

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#existsFile(java.lang.String, boolean)
     */
    @Override
    public boolean existsFile(String filename, boolean fullpath) {
        boolean _status = false;
        try {
            String path = "";
            if (!fullpath)
                path = String.format("%s%s", getClass().getResource("").getPath(), filename);
            else
                path = filename;
            if (new File(path).exists())
                _status = true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return _status;
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#existsFile(java.lang.String)
     */
    @Override
    public boolean existsFile(String filename) {
        return existsFile(filename, true);
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#upload(java.lang.Object)
     */
    @Override
    public boolean upload(Object file) {
        boolean _status = false;
        try {
            // File _file = (File) file;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return _status;
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#delete(java.lang.String, boolean)
     */
    @Override
    public boolean delete(String filename, boolean fullpath) {
        boolean _status = false;
        try {
            String path = "";
            if (!fullpath)
                path = String.format("%s%s", getClass().getResource("").getPath(), filename);
            else
                path = filename;
            if (existsFile(filename))
                new File(path).delete();
        } catch (Exception e) {
            _status = false;
            e.getStackTrace();
        }
        return _status;
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#delete(java.lang.String)
     */
    @Override
    public boolean delete(String filename) {
        return delete(filename, true);
    }

    @Override
    public void readFile(String filename) {
        //            JSONParser parser = new JSONParser();
        //            Object obj = parser.parse(new FileReader("/home"));
        //            JSONObject jsonOb = (JSONObject) obj;

    }

    @Override
    public Map<String, Object> readConfig(String enterprise) {
        Logger.getLogger(ManagerFiles.class.getName()).info("PATH FOR READER CONFIG ".concat(Module.UPLOAD_PATH()));
        Map<String, Object> _config = new HashMap<String, Object>();
        JSONParser parser = new JSONParser();
        try
        {
            BufferedReader reader =  new BufferedReader(new FileReader(Module.UPLOAD_PATH().concat("config.json")));
            String all = "", line = "";
            try {
                while((line = reader.readLine()) != null)
                    all = all.concat(line);
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                reader.close();
            }
            Object obj = parser.parse(all);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("OBJECT JSON " + jsonObject);
            JSONObject enterpriseObj = (JSONObject) jsonObject.get("enterprise");
            System.out.println("OBJECT ENTERPRISE " + enterprise);
            enterpriseObj = (JSONObject) enterpriseObj.get(enterprise.toString());
            System.out.println("BLOCK IFs");
            if (enterpriseObj.containsKey("port")){
                System.out.println("IN THE PORT");
                _config.put("port", enterpriseObj.get("port").toString());
            }
            if (enterpriseObj.containsKey("host")){
                System.out.println("IN THE HOST");
                _config.put("host", enterpriseObj.get("host").toString());
            }
            if (enterpriseObj.containsKey("db")){
                System.out.println("IN THE DB");
                _config.put("db", enterpriseObj.get("db").toString());
            }
            if (enterpriseObj.containsKey("passwd")){
                System.out.println("IN THE PWD");
                _config.put("passwd", enterpriseObj.get("passwd").toString());
            }
            if (enterpriseObj.containsKey("user")){
                System.out.println("IN THE USER");
                _config.put("user", enterpriseObj.get("user").toString());
            }
            System.out.println("READ CONFIG " + enterpriseObj);
            System.out.println("CONFIG " + _config);
        } catch (FileNotFoundException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        } catch (ParseException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        }
        return _config;
    }

}
