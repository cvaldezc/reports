/**
 *
 */
package icrperusa.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import icrperusa.utils.Module;

/**
 * @author christian
 *
 */
public class Connect {

    public Connect(){
        Module.loadData();
    }

    public Connection Open() throws SQLException{
        // Module.loadData();
        String uri = "";
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            uri = String.format("jdbc:postgresql://%s:%s/%s", Module.HOST, Module.PORT, Module.DB);
            System.out.println(uri);
            cn = DriverManager.getConnection(uri, Module.USER, Module.PWD);
            //System.out.println("user and pwd "+ Module.USER + " " + Module.PWD);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            //System.out.println("INSTANCE SQL "+ e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            //System.out.println("ILEGAL SQL "+ e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //System.out.println("CLASS NOT FOUND SQL "+ e.getMessage());
            e.printStackTrace();
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
