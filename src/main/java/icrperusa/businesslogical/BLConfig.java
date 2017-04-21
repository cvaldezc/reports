/**
 *
 */
package icrperusa.businesslogical;

import java.sql.ResultSet;

import icrperusa.dataaccess.Connect;
import icrperusa.entity.ConfMaster;

/**
 * @author christian
 *
 */
public class BLConfig extends ConfMaster {

    public BLConfig(String RUC){
        this.setEnterprise(RUC);
    }

    public ResultSet getConfig()
    {
        ResultSet xrs = null;
        try {
            String xquery = "SELECT * FROM home_configuracion";
            // System.out.println("Cursor Name" + xrs.getCursorName());
            xrs = new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[]{});
            System.out.println("Size of Result Set" + xrs.getFetchSize());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("BUSSINESS LOGIN SQL "+ e.getMessage());
            System.out.println(e.getMessage());
        }
        return xrs;
    }

}
