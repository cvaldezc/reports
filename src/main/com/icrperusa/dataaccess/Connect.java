/**
 * 
 */
package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import utils.Module;;

/**
 * @author christian
 *
 */
public class Connect {



    public Connection Open() throws SQLException{
        String uri = "";
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            uri = "jdbc:postgresql://"+ Module.HOST +":5432/" + Module.DB;
            if(Module.empresa == true){
                if (Module.RUC.equals("20428776110")){
                    cn = DriverManager.getConnection(uri, Module.USER, Module.PWD);
                }else{
                    cn = DriverManager.getConnection(uri, Module.USER, Module.PWD);                    
                }
            }else{
                cn = DriverManager.getConnection(uri, Module.USER, Module.PWD);
            }    
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            System.out.println("INSTANCE SQL "+ e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            System.out.println("ILEGAL SQL "+ e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("CLASS NOT FOUND SQL "+ e.getMessage());
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
            // execute query sql statement
            rs = pstatement.executeQuery();
            //			while (rs.next()) {
            //				System.out.println(rs.getObject(2));
            //				System.out.println(rs.getObject(3));
            //				System.out.println(rs.getObject(4));
            //			}
        } catch (Exception e) {
            // TODO: handle exception
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
        } catch (Exception e) {
            _column = null;
        }
        return _column;
    }
    


}
