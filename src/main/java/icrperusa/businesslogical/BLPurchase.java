/**
 *
 */
package icrperusa.businesslogical;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import icrperusa.dataaccess.Connect;
import icrperusa.entity.Purchase;
import icrperusa.interfaces.IPurchase;
import icrperusa.utils.Convert;
import icrperusa.utils.RoundPlaces;

/**
 * @author christian
 *
 */
public class BLPurchase extends Purchase implements IPurchase {

    public double getIGV(String idpurchase){
        double _igv = 0;
        try {
            String xquery = "";
            // get year of order purchase
            xquery = "SELECT registrado, sigv FROM logistica_compra WHERE compra_id = ?;";
            ResultSet purchase = new Connect().ExecuteQuery(xquery, new Object[]{ idpurchase });
            String register = "";
            purchase.next();
            if (purchase.getBoolean("sigv") == true)
                register = purchase.getString("registrado");
            else
                return _igv;
            xquery = "SELECT igv FROM home_configuracion WHERE periodo = ?;";
            _igv = (Double)new Connect().ExecuteQuery(xquery, new Object[] { Convert.toDString(register, "yyyy")}, "igv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return _igv;
    }

    public double amountPurchase(String idpurchase){
        double _amount = 0;
        try {
            // #region select query
            String xquery = "SELECT " +
                    "c.compra_id AS codcompra," +
                    "prov.proveedor_id AS provruc," +
                    "prov.razonsocial AS provrazonsocial," +
                    "prov.direccion AS provdireccion," +
                    "c.registrado AS registrado," +
                    "c.traslado AS fechtraslado," +
                    "c.lugent AS lugentrega," +
                    "doc.documento AS documento," +
                    "fpago.pagos AS formapago," +
                    "mon.moneda AS moneda," +
                    "c.status AS estado," +
                    "c.contacto AS contacto," +
                    "c.projects AS proyectos," +
                    "mat.materiales_id AS codmaterial," +
                    "mat.matnom AS material," +
                    "mat.matmed AS matmedida," +
                    "br.brand AS marca," +
                    "mod.model AS modelo," +
                    "unid.uninom AS unidad," +
                    "detc.cantstatic AS cantidad," +
                    "detc.precio AS precio," +
                    "detc.discount AS descuento," +
                    "detc.perception AS percepcion," +
                    "dist.distnom AS distrito," +
                    "detc.observation AS observaciones " +
                    "FROM logistica_compra c " +
                    "INNER JOIN logistica_detcompra detc " +
                    "ON c.compra_id = detc.compra_id " +
                    "INNER JOIN home_proveedor prov " +
                    "ON prov.proveedor_id = c.proveedor_id " +
                    "INNER JOIN home_documentos doc " +
                    "ON doc.documento_id = c.documento_id " +
                    "INNER JOIN home_formapago fpago " +
                    "ON fpago.pagos_id = c.pagos_id " +
                    "INNER JOIN home_moneda mon " +
                    "ON mon.moneda_id = c.moneda_id " +
                    "INNER JOIN home_materiale mat " +
                    "ON mat.materiales_id = detc.materiales_id " +
                    "INNER JOIN home_brand br " +
                    "ON br.brand_id = detc.brand_id " +
                    "INNER JOIN home_model mod " +
                    "ON mod.model_id = detc.model_id " +
                    "INNER JOIN home_unidade unid " +
                    "ON unid.unidad_id = detc.unit_id " +
                    "INNER JOIN home_distrito dist " +
                    "ON dist.distrito_id = prov.distrito_id " +
                    "WHERE c.compra_id = ? ORDER BY material";
            // #endregion  query
            //
            ResultSet rs = new Connect().ExecuteQuery(xquery, new Object[]{idpurchase});
            //Double precio;
            Double discount;
            //Double desctotal = 0.0;
            //Double d;
            List<Purchase> olist = new ArrayList<Purchase>();
            Purchase obj;
            while(rs.next()){
                obj = new Purchase();
                obj.setPrice(rs.getDouble("precio"));
                obj.setQuantity(rs.getDouble("cantidad"));
                obj.setDiscount(rs.getDouble("descuento"));
                obj.setPerception(rs.getDouble("percepcion"));
                discount = (double) 0;
                if(obj.getDiscount() != 0){
                    discount = RoundPlaces.toDouble((obj.getPrice() * obj.getDiscount())/100, 4);
                    obj.setPrice(obj.getPrice() - discount);
                    obj.setDiscounttotal(discount);
                }

                if(obj.getPerception() != 0){
                    obj.setPerception(RoundPlaces.toDouble((obj.getPrice() * obj.getPerception())/100, 4));
                    obj.setPrice(obj.getPrice() + obj.getPerception());
                    System.out.println("perception "+obj.getPerception());
                    System.out.println("price "+ obj.getPrice());
                }

                obj.setAmount(RoundPlaces.toDouble(obj.getPrice() * obj.getQuantity(), 4));
                System.out.println("amount "+ obj.getAmount());
                _amount += obj.getAmount();
                olist.add(obj);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            _amount = 0;
        }
        return _amount;
    }

}
