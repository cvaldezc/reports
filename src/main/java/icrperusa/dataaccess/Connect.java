/**
 *
 */
package icrperusa.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import icrperusa.utils.Module;

/**
 * @author christian
 *
 */
public class Connect {

    final Logger log = Logger.getLogger(Connect.class.getName());

    protected Map<String, Object> config = new HashMap<String, Object>();

    public Connect(String RUC){
        System.out.println("IN CONNECT RUC " + RUC);
        config = Module.loadConfig(RUC);
        System.out.println("LOAD CONFIG" + Arrays.toString(config.values().toArray()));
    }

    public Connection Open() throws SQLException{
        // Module.loadData();
        String uri = "";
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            uri = String.format("jdbc:postgresql://%s:%s/%s",
                    config.get("host"),
                    config.get("port"),
                    config.get("db"));
            System.out.println("SHOW URL " + uri);
            cn = DriverManager.getConnection(uri, config.get("user").toString(), config.get("passwd").toString());
        } catch (InstantiationException e) {
            log.info("ERROR INSTANCIA: ".concat(e.getMessage()));
        } catch (IllegalAccessException e) {
            log.info("ERROR ILEGAL ACCESS: ".concat(e.getMessage()));
        } catch (ClassNotFoundException e) {
            log.info("ERROR CLASS NOT FOUND: ".concat(e.getMessage()));
        }
        return cn;
    }

    public ResultSet ExecuteQuery(String query, Object[] params) throws SQLException
    {
        ResultSet rs = null;
        PreparedStatement pstatement = null;
        Connection xcon = null;
        try {
            xcon = this.Open();
            pstatement = xcon.prepareStatement(query);
            int count = 1;
            for (Object object : params) {
                pstatement.setObject(count, object);
                count++;
            }
            rs = pstatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("RESULT SET SQL "+ e.getMessage());
            System.out.println(e.getMessage());
        }
        finally{
            xcon.close();
        }
        return rs;
    }

    public Object ExecuteQuery(String xquery, Object[] params, String xcolumn)
    {
        Object _column = null;
        try {
            ResultSet rs = ExecuteQuery(xquery, params);
            while (rs.next())
                _column = rs.getObject(xcolumn);
        } catch (SQLException e) {
            _column = null;
        }
        return _column;
    }

}
