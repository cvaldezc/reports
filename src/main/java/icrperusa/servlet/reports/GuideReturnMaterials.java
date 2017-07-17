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

import icrperusa.utils.Module;
import icrperusa.utils.Reports;

/**
 * Servlet implementation class GuideReturnMaterials
 */
public class GuideReturnMaterials extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideReturnMaterials() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        String SOURCE = getServletContext().getRealPath("/").concat(String.valueOf(Module.SEPARATOR));
        String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;
        boolean format = (request.getParameterMap().containsKey("format")) ? true : false;
        Map<String, Object> parameter = new HashMap<String, Object>();
        Logger.getLogger(OrdenPurchase.class.getName()).info("SERVCER PATH DIR ".concat(SOURCE));

        parameter.put("idguiadevmat", request.getParameter("nguide"));
        parameter.put("SOURCE", SOURCE);
        parameter.put("RUC", ruc);
        Reports rpt = new Reports(ruc);
        byte[] bytes = null;
        try {
            if (format)
                bytes = rpt.getReportcn(String.format("%sreports/storage/guidereturnmatformat.jasper", SOURCE), parameter);
            else
                bytes = rpt.getReportcn(String.format("%sreports/storage/guidereturnmat.jasper", SOURCE), parameter);
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
