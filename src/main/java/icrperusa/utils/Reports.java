/**
 * 
 */
package icrperusa.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import icrperusa.dataaccess.Connect;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author christian
 *
 */
public class Reports {


    public byte[] getReportcn(String jrxml, Map<String, Object> parameter) throws SQLException{
        byte[] bytes = null;
        Connection xcon = null;
        try {
            xcon = new Connect().Open();
            JasperReport master = (JasperReport) JRLoader.loadObjectFromFile(jrxml);
            bytes = JasperRunManager.runReportToPdf(master, parameter,  xcon);
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if (!xcon.isClosed()) {
                xcon.close();
            }
        }
        return bytes;
    }

    public byte[] getReportEmptyData(String jrxml, Map<String, Object> param){
        byte[] bytes = null;
        try {
            JasperReport master = (JasperReport) JRLoader.loadObjectFromFile(jrxml);
            bytes = JasperRunManager.runReportToPdf(master, param,  new JREmptyDataSource());
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return bytes;
    } 

}
