/**
 *
 */
package icrperusa.businesslogical;

import java.sql.ResultSet;

import icrperusa.dataaccess.Connect;
import icrperusa.entity.GuideTools;

/**
 * @author christian
 *
 */
public class BLGuideTools extends GuideTools {

    public BLGuideTools(String RUC){
        this.setEnterprise(RUC);
    }

    public int getCounter(String idguide){
        int counter = 0;
        try {
            String xquery = "";
            // get year of order purchase
            xquery = "SELECT count(*) as counter FROM almacen_guiaherramienta gh INNER JOIN almacen_detguiaherramienta dgh ON dgh.guia_id=gh.guia_id WHERE gh.guia_id = ? and dgh.grupo=''";
            ResultSet guide = new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[]{ idguide });
            // String counterstring = "";
            guide.next();
            if (guide.getBoolean("counter") == true){
                counter = guide.getInt("counter");
                if (counter > 0)
                    counter -= 1;
            }else
                return 0;
            // _igv = (Double)new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[] { Convert.toDString(register, "yyyy")}, "igv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }

}
