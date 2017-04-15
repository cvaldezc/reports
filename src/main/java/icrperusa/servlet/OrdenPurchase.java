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
import icrperusa.utils.NumberTOWords;
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
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("application/pdf;charset=UTF-8");
        // String path = getServletContext().getResource("/WEB-INF/resources/").toString().substring(6);
        String path = getServletContext().getRealPath("/WEB-INF/resources/");
        String ruta = "";
        String logoname;
        String parruc = ruta;
        if(request.getParameterMap().containsKey("ruc")){
            ruta = request.getParameter("ruc");
            Module.empresa = true;
            if(parruc.equals("20428776110") || parruc.equals("")){
                logoname = "/icrlogo.png";
            }else{
                logoname = "/icrlogoinst.png";
            }
        }else{
            Module.empresa = false;
            logoname = "/icrlogo.png";
        }
        
        Module.RUC = ruta;
        Module.HOST = request.getServerName();

        // List <Double> limporte = new ArrayList<Double>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        String purchaseid = "";
        if (request.getParameterMap().containsKey("purchase"))
            purchaseid = request.getParameter("purchase");
        else
            purchaseid = "OC17000079";

        Double subtotal = new BLPurchase().amountPurchase(purchaseid);
        System.out.println("SUb TOTAL FOR " + subtotal);
        Double pigv = new BLPurchase().getIGV(purchaseid); 
        System.out.println("ig " + pigv);
        Double igv = RoundPlaces.toDouble(((subtotal * pigv)/100), 4);
        System.out.println("igv " + igv);
        Double igv2dec = RoundPlaces.toDouble(igv);
        Double subtotal2dec = RoundPlaces.toDouble(subtotal);
        System.out.println("subtotal" + subtotal2dec);
        Double total = igv2dec + subtotal2dec;
        Double t = RoundPlaces.toDouble(total);
        System.out.println("subtotal"+ t);
        //
//        String [] numseparet = t.toString().split("\\.");
//        int decen = Integer.parseInt(numseparet[0]);
//        int unid = Integer.parseInt(numseparet[1]);
        
//        String textdece = new NumberTOWords()(decen);
//        String textunid = numletras.numero_a_letras_con(unid);
        String texttot = new NumberTOWords().numero_a_letras(total); // textdece + " CON " + textunid;
        
        //System.out.println("descuento "+RoundPlaces.toDouble());
        parameter.put("parcompra_id", purchaseid);
        parameter.put("parsubtotal",RoundPlaces.toDouble(subtotal, 2));
        parameter.put("parigv",igv2dec);
        System.out.println("parigv "+igv2dec);
        parameter.put("pardescuento", 0);
        parameter.put("partotal", t);
        parameter.put("parnumigv",pigv);
        parameter.put("parnumtexto", texttot);
        System.out.println("logo "+path);
        parameter.put("parlogo",path+logoname);

        Reports rpt = new Reports();
        byte[] bytes = null;
        try {
            bytes = rpt.getReportcn(path + "/ordencompra.jasper", parameter);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
