package icrperusa.servlet.reports;

import java.io.IOException;
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
 * Servlet implementation class OrderGroup
 */
public class OrderGroup extends HttpServlet {

    private static final Logger log = Logger.getLogger(OrderGroup.class.getName());

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderGroup() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.getWriter().append("Served at: ").append(request.getContextPath());
        final String SOURCE = getServletContext().getRealPath("/").concat(String.valueOf(Module.SEPARATOR));
        System.out.println("SOURCE PATH " + SOURCE);
        String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;
        String groupid = "";
        // define parameter
        Map<String, Object> parameter = new HashMap<String, Object>();
        if (request.getParameterMap().containsKey("groupid"))
            groupid = request.getParameter("groupid");
        else
            response.sendRedirect("/reports/404");
        parameter.put("parcodgrupo", groupid);
        parameter.put("PATHSOURCE", SOURCE);
        parameter.put("RUC", ruc);
        Reports rpt = new Reports(ruc);
        byte[] bytes = null;
        try {
            bytes = rpt.getReportcn(String.format("%sreports/storage/groupbyorder.jasper", SOURCE), parameter);

        } catch (Exception ex) {
            log.info("Error when build report ".concat(ex.getMessage()));
            response.sendRedirect(String.format("/reports/500?problem=%s", ex.getMessage()));
        }
        if (bytes == null)
            bytes = new byte[0];
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/pdf;charset=UTF-8");
        response.setContentLength(bytes.length);
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }

}
