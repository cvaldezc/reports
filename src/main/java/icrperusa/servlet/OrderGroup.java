package icrperusa.servlet;

import java.io.IOException;
import java.text.MessageFormat;
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
        response.setContentType("application/pdf;charset=UTF-8");
        final String SOURCE = getServletContext().getRealPath("/");
        String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;
        String groupid = "";
        // define parameter
        Map<String, Object> parameter = new HashMap<String, Object>();
        if (request.getParameterMap().containsKey("groupid"))
            groupid = request.getParameter("groupid");
        else
            response.sendRedirect("/404");
        parameter.put("groupid", groupid);
        Reports rpt = new Reports(ruc);
        byte[] bytes = null;
        try {
            bytes = rpt.getReportcn(MessageFormat.format("{0}/reports/storage/.jasper", SOURCE), parameter);
        } catch (Exception ex) {
            response.sendRedirect(String.format("/500?problem=%s", ex.getMessage()));
            log.info("Error when build report ".concat(ex.getMessage()));
        }
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }

}
