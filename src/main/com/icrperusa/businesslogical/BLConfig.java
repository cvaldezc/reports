/**
 * 
 */
package businesslogical;

import java.sql.ResultSet;

import dataaccess.Connect;

/**
 * @author christian
 *
 */
public class BLConfig {
	
	public ResultSet getConfig()
	{
		ResultSet xrs = null;
		try {
			String xquery = "SELECT * FROM home_configuracion";
			// System.out.println("Cursor Name" + xrs.getCursorName());
			xrs = new Connect().ExecuteQuery(xquery, new Object[]{});
			System.out.println("Size of Result Set" + xrs.getFetchSize());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("BUSSINESS LOGIN SQL "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		return xrs;
	}

}
