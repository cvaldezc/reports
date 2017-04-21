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

    public BLPurchase(String RUC){
        this.setEnterprise(RUC);;
    }

    @Override
    public double getIGV(String idpurchase){
        double _igv = 0;
        try {
            String xquery = "";
            // get year of order purchase
            xquery = "SELECT registrado, sigv FROM logistica_compra WHERE compra_id = ?;";
            ResultSet purchase = new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[]{ idpurchase });
            String register = "";
            purchase.next();
            if (purchase.getBoolean("sigv") == true)
                register = purchase.getString("registrado");
            else
                return _igv;
            xquery = "SELECT igv FROM home_configuracion WHERE periodo = ?;";
            _igv = (Double)new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[] { Convert.toDString(register, "yyyy")}, "igv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return _igv;
    }

    @Override
    public double amountPurchase(String idpurchase){
        double _amount = 0;
        try {
            // #region select query
            String xquery = "SELECT d.precio, d.cantstatic, d.discount, d.perception FROM logistica_detcompra d "+
                    " LEFT JOIN home_unidade u"+
                    " ON d.unit_id = u.unidad_id"+
                    " WHERE d.compra_id = ?; ";
            // #endregion  query
            //
            ResultSet rs = new Connect(this.getEnterprise()).ExecuteQuery(xquery, new Object[]{idpurchase});
            //Double precio;
            Double discount;
            //Double desctotal = 0.0;
            //Double d;
            List<Purchase> olist = new ArrayList<Purchase>();
            Purchase obj;
            while(rs.next()){
                System.out.println("PRECIO de DETALLE "+ rs.getDouble("precio"));
                System.out.println("CANTIDAD de DETALLE "+ rs.getObject("cantstatic"));
                obj = new Purchase();
                obj.setPrice(rs.getDouble("precio"));
                obj.setQuantity(rs.getDouble("cantstatic"));
                obj.setDiscount(rs.getDouble("discount"));
                obj.setPerception(rs.getDouble("perception"));
                if(obj.getDiscount() != 0){
                    discount = (double) 0;
                    discount = RoundPlaces.toDouble((obj.getPrice() * obj.getDiscount())/100, 4);
                    obj.setPrice(obj.getPrice() - discount);
                    obj.setDiscounttotal(discount);
                }

                if(obj.getPerception() != 0){
                    obj.setPerception(RoundPlaces.toDouble((obj.getPrice() * obj.getPerception())/100, 4));
                    obj.setPrice(obj.getPrice() + obj.getPerception());
                    System.out.println("p "+obj.getPerception());
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
