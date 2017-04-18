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
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see icrperusa.interfaces.utils.IUploadFile#delete(java.lang.String)
     */
    public boolean delete(String filename) {
        // TODO Auto-generated method stub
        return false;
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
        if (Module.RESOURCE == ""){
            Module.setRESOURCE(new File(".").getAbsolutePath());
            System.out.println(Module.RESOURCE);
        }
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(String.format("%sconfig.json", Module.RESOURCE)));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject enterpriseObj = (JSONObject) jsonObject.get(enterprise);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
