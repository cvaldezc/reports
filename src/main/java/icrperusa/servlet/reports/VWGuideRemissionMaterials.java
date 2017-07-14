package icrperusa.servlet.reports;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.businesslogical.BLGuideRemissionMaterials;
import icrperusa.entity.GuideRemissionMaterials;
import icrperusa.utils.Module;
import icrperusa.utils.Reports;

/**
 * Servlet implementation class GuideRetTools
 */
public class VWGuideRemissionMaterials extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VWGuideRemissionMaterials() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Logger log = Logger.getLogger(VWGuideRemissionMaterials.class.getName());
            response.setContentType("application/pdf;charset=UTF-8");
            String SOURCE = getServletContext().getRealPath("/").concat(String.valueOf(Module.SEPARATOR));
            String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;;
            //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println("RUC IN GUIDE REMISION " + ruc);
            System.out.println("SOURCE " + SOURCE);
            //Date date = new Date();
            String idguide = request.getParameter("idguide");
            System.out.println("Number of guide " + idguide);
            Map<String, Object> parameter = new HashMap<String, Object>();
            GuideRemissionMaterials getbedside = new BLGuideRemissionMaterials(ruc).bedsideReport(idguide);
            //if (getbedside != null)\
            log.info("BEDSIDE ".concat(getbedside.toString()));
            //try{
            String[] months = new String[]{ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre",};
            // get parameters
            parameter.put("GUIDEID", idguide);
            parameter.put("SOURCEPATH", SOURCE);
            parameter.put("DOTSTART", "Jr San Martin Mz E Lt 6 Urb Los Huertos de Huachipa, Lurigancho Chosica, Lima");
            parameter.put("DOTARRIVAL", getbedside.getDotarrival());
            parameter.put("SUPPLIERNAME", getbedside.getSuppliername());
            parameter.put("SUPPLIERID", getbedside.getSupplierid());
            parameter.put("DAY", getbedside.getDay());
            parameter.put("MONTH", months[Integer.parseInt(getbedside.getMonth()) - 1]);
            parameter.put("YEAR", getbedside.getYear());
            parameter.put("BRAND", getbedside.getBrand());
            parameter.put("PLATE", getbedside.getPlate());
            parameter.put("INSCRIPTION", getbedside.getInscription() == null?"":getbedside.getInscription());
            parameter.put("LISENCE", getbedside.getLisence());
            parameter.put("TRASUPPLIERNAME", getbedside.getTrasuppliername());
            parameter.put("TRASUPPLIERID", getbedside.getTrasupplierid());
            parameter.put("PROJECT", String.format("%s - %s", getbedside.getProjectid(), getbedside.getProjectname()));
            log.info("Name file: ".concat(SOURCE));
            parameter.put("ORDERS", new BLGuideRemissionMaterials(ruc).getOrders(idguide));

            // parameter.put("PATHSOURCE", SOURCE);
            parameter.put("RUC", ruc);
            System.out.println("BEFORE CREATE REPORT");
            Reports rpt = new Reports(ruc);
            // byte[] bytes = rpt.getReportcn(String.format("%sreports/storage/guideremision.jasper", SOURCE), parameter);
            // response.setContentLength(bytes.length);
            // Reports rpt = new Reports(ruc);
            byte[] bytes = null;
            try {
                bytes = rpt.getReportcn(String.format("%sreports/storage/guideremision.jasper", SOURCE), parameter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            System.out.println("ERROR SHOW GUIDE REMISSION " + e.getMessage());
            e.getStackTrace();
        }
    }


}
