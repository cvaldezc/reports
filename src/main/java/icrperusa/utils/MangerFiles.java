/**
 *
 */
package icrperusa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public boolean existsFile(String filename, boolean fullpath) {
        boolean _status = false;
        try {
            String path = "";
            if (!fullpath)
                path = String.format("%s%s", Module.RESOURCE, filename);
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
    public boolean existsFile(String filename) {
        return existsFile(filename, true);
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#upload(java.lang.Object)
     */
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
    public boolean delete(String filename, boolean fullpath) {
        boolean _status = false;
        try {
            String path = "";
            if (!fullpath)
                path = String.format("%s%s", Module.RESOURCE, filename);
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
    public boolean delete(String filename) {
        return delete(filename, true);
    }

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

    public void readConfig(String enterprise) {
        JSONParser parser = new JSONParser();
        try {
            String path = String.format("%s../config.json", this.getClass().getClassLoader().getResource("").getPath());
            if (existsFile(path))
                System.out.println("File Exists Yeahh!!!");
            System.out.println(path);
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject);
            JSONObject enterpriseObj = (JSONObject) jsonObject.get("enterprise");
            //System.out.println(enterpriseObj);
            //System.out.println("Name enterprise "+enterprise);
            enterpriseObj = (JSONObject) enterpriseObj.get(enterprise);
            //System.out.println("PARSE obj json "+ enterpriseObj);
            if (enterpriseObj.containsKey("port"))
                Module.PORT = enterpriseObj.get("port").toString();
            if (enterpriseObj.containsKey("host"))
                Module.HOST = enterpriseObj.get("host").toString();
            if (enterpriseObj.containsKey("db"))
                Module.DB = enterpriseObj.get("db").toString();
            if (enterpriseObj.containsKey("passwd"))
                Module.PWD = enterpriseObj.get("passwd").toString();
            if (enterpriseObj.containsKey("user"))
                Module.USER = enterpriseObj.get("user").toString();
            Module.RUC = Module.defenterpise;
            //System.out.println(String.format("%s %s %s %s %s", Module.HOST, Module.PORT, Module.USER, Module.DB, Module.PWD));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
