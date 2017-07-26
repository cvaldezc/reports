package icrperusa.servlet.reports;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.utils.Module;
import icrperusa.utils.Reports;

/**
 * Servlet implementation class GuideRetTools
 */
public class GuideRetTools extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideRetTools() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/pdf;charset=UTF-8");
            String SOURCE = getServletContext().getRealPath("/").concat(String.valueOf(Module.SEPARATOR));
            String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("iddoc", request.getParameter("iddoc"));
            parameter.put("pardate", "FECHA: "+dateFormat.format(date));
            parameter.put("SOURCE", SOURCE);
            parameter.put("emple", (request.getParameterMap().containsKey("emple")) ? request.getParameter("emple"): "");
            parameter.put("RUC", ruc);

            Reports rpt = new Reports(ruc);
            byte[] bytes = rpt.getReportcn(String.format("%sreports/storage/guiadevolucionherra.jasper", SOURCE), parameter);
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            System.out.println("ERROR SHOW APPLICATION " + e.getMessage());
            e.getStackTrace();
        }
    }

}
