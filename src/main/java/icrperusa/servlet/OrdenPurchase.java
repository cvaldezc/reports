package icrperusa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icrperusa.businesslogical.BLPurchase;
import icrperusa.utils.Module;
import icrperusa.utils.NumberToChar;
import icrperusa.utils.Reports;
import icrperusa.utils.RoundPlaces;


/**
 * Servlet implementation class PurchaseGuide
 */
public class OrdenPurchase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdenPurchase() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        String SOURCE = getServletContext().getRealPath("/");
        String ruc = (request.getParameterMap().containsKey("ruc")) ? request.getParameter("ruc") : Module.defenterpise;
        Map<String, Object> parameter = new HashMap<String, Object>();
        String purchaseid = "";
        if (request.getParameterMap().containsKey("purchase"))
            purchaseid = request.getParameter("purchase");
        else
            purchaseid = "OC17000001";

        Double subtotal = new BLPurchase(ruc).amountPurchase(purchaseid);
        System.out.println("SUB TOTAL FOR " + subtotal);
        Double pigv = new BLPurchase(ruc).getIGV(purchaseid);

        System.out.println("ig " + pigv);
        Double igv = RoundPlaces.toDouble(((subtotal * pigv)/100), 4);
        System.out.println("igv " + igv);
        Double igv2dec = RoundPlaces.toDouble(igv);
        Double subtotal2dec = RoundPlaces.toDouble(subtotal);
        System.out.println("subtotal" + subtotal2dec);
        Double total = igv2dec + subtotal2dec;
        Double t = RoundPlaces.toDouble(total);
        System.out.println("subtotal"+ t);

        String texttot = new NumberToChar().numero_a_letras(t);

        parameter.put("parcompra_id", purchaseid);
        parameter.put("parsubtotal",RoundPlaces.toDouble(subtotal, 2));
        parameter.put("parigv", igv2dec);
        parameter.put("pardescuento", 0);
        parameter.put("partotal", t);
        parameter.put("parnumigv", pigv);
        parameter.put("parnumtexto", texttot);
        parameter.put("parlogo", "");
        parameter.put("PATHSOURCE", SOURCE);
        parameter.put("RUC", ruc);

        Reports rpt = new Reports(ruc);
        byte[] bytes = null;
        try {
            bytes = rpt.getReportcn(String.format("%sWEB-INF/resources/reports/logistics/ordencompra.jasper", SOURCE), parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentLength(bytes.length);

        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
