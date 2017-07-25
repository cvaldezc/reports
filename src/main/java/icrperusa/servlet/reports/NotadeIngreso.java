package icrperusa.servlet.reports;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class NotadeIngreso
 */
public class NotadeIngreso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotadeIngreso() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        String SOURCE = getServletContext().getRealPath("/").concat(String.valueOf(Module.SEPARATOR));
        // Logger.getLogger(OrdenPurchase.class.getName()).info("SERVCER PATH DIR ".concat(SOURCE));
        String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;

        Map<String, Object> parameter = new HashMap<String, Object>();
        String paridnota = "";
        if (request.getParameterMap().containsKey("idnota"))
            paridnota = request.getParameter("idnota");

        parameter.put("paridnota", paridnota);
        parameter.put("SOURCE", SOURCE);
        parameter.put("RUC", ruc);

        Reports rpt = new Reports(ruc);
        byte[] bytes = null;
        try {
            bytes = rpt.getReportcn(String.format("%sreports/storage/notadeingreso.jasper", SOURCE), parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentLength(bytes.length);

        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    }

}
