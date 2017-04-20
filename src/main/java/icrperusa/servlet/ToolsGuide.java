package icrperusa.servlet;

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
 * Servlet implementation class ToolsGuide
 */
public class ToolsGuide extends HttpServlet {
    private static final long serialVersionUID = 102831973239L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToolsGuide() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        String SOURCE = getServletContext().getRealPath("/");
        Module.enterprise = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : "";
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("codguia", request.getParameter("ng"));
            parameter.put("pardate", "FECHA: "+dateFormat.format(date));
            parameter.put("PATHSOURCE", Module.RESOURCE);
            parameter.put("RUC", Module.RUC);

            byte[] bytes = new Reports().getReportcn(String.format("%sWEB-INF/resources/reports/storage/guiaherramienta.jasper", SOURCE), parameter);
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error al mostrar el reporte" + e.getMessage() +"</h3>");
            System.out.println("ERROR SHOW APPLICATION " + e.getMessage());
        }
    }


}
