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

//
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
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Module.HOST = request.getServerName();
            response.setContentType("application/pdf;charset=UTF-8");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("codguia", request.getParameter("ng"));
            parameter.put("pardate", "FECHA: "+dateFormat.format(date));

            // String path = getServletContext().getResource("/WEB-INF/resources/").toString().substring(6);
            String path = getServletContext().getRealPath("/WEB-INF/resources/");
            Reports rpt = new Reports();
            byte[] bytes = rpt.getReportcn(path + "/guiaherramienta.jasper", parameter);
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            System.out.println("ERROR SHOW APPLICATION " + e.getMessage());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
