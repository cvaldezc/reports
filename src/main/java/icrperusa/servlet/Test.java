package icrperusa.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.businesslogical.BLConfig;
import icrperusa.utils.Module;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruc = (request.getParameterMap().containsKey("ruc"))?request.getParameter("ruc"):Module.defenterpise;
        BLConfig nob = new BLConfig(ruc);
        ResultSet xrs = nob.getConfig();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("Served at: ").append(request.getContextPath());
        response.getWriter().write("<h3>LIST CONF:</h3>");
        try {
            while (xrs.next()) {
                response.getWriter().write("<ul>");
                response.getWriter().write("<li>" + xrs.getString("periodo") + "</li>");
                response.getWriter().write("<li>" + xrs.getString("registrado") + "</li>");
                response.getWriter().write("<li>" + xrs.getString("igv") + "</li>");
                response.getWriter().write("<li>" + xrs.getString("perception") + "</li>");
                response.getWriter().write("</ul>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
