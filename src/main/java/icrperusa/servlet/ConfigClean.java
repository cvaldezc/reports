package icrperusa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.utils.Module;

/**
 * Servlet implementation class ConfigClean
 */
public class ConfigClean extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigClean() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Module.cleanConfig();
        response.setContentType("text/html;characterset=UTF-8");
        response.getWriter().write("<!doctype>");
        response.getWriter().write("<html>");
        response.getWriter().write("<head><title>Clean Config</title></head>");
        response.getWriter().write("<section>");
        response.getWriter().write("<p>Configuration Cleaned!!!<p>");
        response.getWriter().write("</section>");
        response.getWriter().write("<html>");
        response.getWriter().write("</html>");
    }

}
