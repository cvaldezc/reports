/**
 *
 */
package icrperusa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import icrperusa.interfaces.utils.IManagerFile;

/**
 * @author christian
 *
 */
public class MangerFiles implements IManagerFile {

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
            File _file = (File) file;

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

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("/home"));
            JSONObject jsonOb = (JSONObject) obj;
        }
        catch(IOException ex){

        }catch(ParseException exp){

        } finally {
            // TODO: handle finally clause
        }
    }

    @Override
    public Map<String, Object> readConfig(String enterprise) {
        Map<String, Object> _config = new HashMap<String, Object>();
        JSONParser parser = new JSONParser();
        try
        {
            URL url = new URL("http://172.16.0.80:8089/reports/config.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String all = "", tmp = "";
            while((tmp = in.readLine()) != null)
                all += tmp;
            //            System.out.println(all);
            in.close();
            Object obj = parser.parse(all);
            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject);
            JSONObject enterpriseObj = (JSONObject) jsonObject.get("enterprise");
            enterpriseObj = (JSONObject) enterpriseObj.get(enterprise);

            if (enterpriseObj.containsKey("port"))
                _config.put("port", enterpriseObj.get("port").toString());
            if (enterpriseObj.containsKey("host"))
                _config.put("host", enterpriseObj.get("host").toString());
            if (enterpriseObj.containsKey("db"))
                _config.put("db", enterpriseObj.get("db").toString());
            if (enterpriseObj.containsKey("passwd"))
                _config.put("passwd", enterpriseObj.get("passwd").toString());
            if (enterpriseObj.containsKey("user"))
                _config.put("user", enterpriseObj.get("user").toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return _config;
    }

}
