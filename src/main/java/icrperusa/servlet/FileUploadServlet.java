package icrperusa.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import icrperusa.utils.Module;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet(name="FileUploadServlet", urlPatterns={"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("<!DOCTYPE html>");
        response.getWriter().append("<html>");
        response.getWriter().append("<head>");
        response.getWriter().append("<title>Upload Config</title>");
        response.getWriter().append("</head>");
        response.getWriter().append("<body>");
        response.getWriter().append("<header>");
        response.getWriter().append("<h4>Upload File Configuration</h4>");
        response.getWriter().append("<form method=\"POST\" action=\"\" enctype=\"multipart/form-data\">");
        response.getWriter().append("<label>Select File </label>");
        response.getWriter().append("<input type=\"file\" name=\"file\"  />");
        response.getWriter().append("<br />");
        response.getWriter().append("<br />");
        response.getWriter().append("<button type=\"submit\" name=\"save\">upload file</button>");
        response.getWriter().append("</form>");
        response.getWriter().append("</header>");
        response.getWriter().append("</body>");
        response.getWriter().append("</html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("settings".concat(String.valueOf(Module.SEPARATOR)));
        response.setContentType("text/html;charset=UTF-8");
        final Part filePart = request.getPart("file");
        String filename = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter write = response.getWriter();
        try {
            System.out.println("PATH IN SAVE FILE CONFIG " + path);
            String _path = String.format("%s%s", path, filename);
            System.out.println("PATH WHERE UPLOAD ".concat(_path));
            File _file = new File(_path);
            if (_file.exists()){
                _file.delete();
                write.println("File exists and delete!");
            }
            out = new FileOutputStream(_file);
            // System.out.println(out);
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1)
                out.write(bytes, 0, read);
            write.println("New File "+ filename);
        } catch (FileNotFoundException exf) {
            write.println("MIssing file or no insufficient permission.");
            write.print("Error " + exf.getMessage());
        }finally{
            if (out != null)
                out.close();
            if (filecontent != null)
                filecontent.close();
            if (write != null)
                write.close();
        }
    }

    private String getFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");
        String name = header.substring(header.indexOf("filename=\"")+10);
        return name.substring(0, name.indexOf("\""));
    }

}
