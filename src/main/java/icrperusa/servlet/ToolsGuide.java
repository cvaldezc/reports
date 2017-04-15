package icrperusa.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
//import utils.Reports;
//import utils.Module;
//import controller.Reports;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // response.getWriter().append("Served at:
        // ").append(request.getContextPath());
//        Module.HOST = request.getServerName();
//        response.setContentType("application/pdf;charset=UTF-8");
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//
//        Map<String, Object> parameter = new HashMap<String, Object>();
//        parameter.put("codguia", request.getParameter("ng"));
//        parameter.put("pardate", "FECHA: " + dateFormat.format(date));
//
//        // String path =
//        // getServletContext().getResource("/WEB-INF/resources/").toString().substring(6);
//        String path = getServletContext().getRealPath("/WEB-INF/resources/");
//        parameter.put("REPORT_PATH", path);
//        Reports rpt = new Reports();
//        byte[] bytes = null;
//        try {
//            bytes = rpt.getReportcn(path + "/guiaherramienta.jasper", parameter);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        response.setContentLength(bytes.length);
//        ServletOutputStream ouputStream = response.getOutputStream();
//        ouputStream.write(bytes, 0, bytes.length);
//        ouputStream.flush();
//        ouputStream.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
