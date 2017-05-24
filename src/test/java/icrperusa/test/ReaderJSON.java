package icrperusa.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import icrperusa.utils.Module;

public class ReaderJSON {

    @Test
    public void test() throws ParseException {

        // String dJSON = "{\"object\": 7}";
        try {
            BufferedReader reader =  new BufferedReader(new FileReader(Module.UPLOAD_PATH().concat("config.json")));
            String dJSON = "", line = "";
            try {
                while((line = reader.readLine()) != null)
                    dJSON = dJSON.concat(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONParser psr = new JSONParser();
            Object obj = psr.parse(dJSON);
            System.out.println(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
