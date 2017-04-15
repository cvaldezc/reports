package icrperusa.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.businesslogical.BLConfig;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BLConfig nob = new BLConfig();
		ResultSet xrs = nob.getConfig();
		response.setContentType("text/html");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().write("<h3>LIST CONF:</h3>");
		try {
			while (xrs.next()) {
				response.getWriter().write("<ul>");
				response.getWriter().write("<li>"+ xrs.getString("periodo")+"</li>");
				response.getWriter().write("<li>"+ xrs.getString("registrado")+"</li>");
				response.getWriter().write("<li>"+ xrs.getString("igv")+"</li>");
				response.getWriter().write("<li>"+ xrs.getString("perception")+"</li>");
				response.getWriter().write("</ul>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
