package icrperusa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NotFound
 */
public class NotFound extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotFound() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append"Served at: ").append(request.getContextPath());
        response.getWriter().append("Recursos no encontrados");
        if (request.getParameterMap().containsKey("raise")){
            response.getWriter().append("El siguiente recurso no se ha encontrado: ");
            response.getWriter().append(request.getParameter("raise"));
        }
    }

}
