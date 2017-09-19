package icrperusa.interfaces;

public interface IPurchase {

    double getIGV(String idpurchase);

    //    double getDiscountGlobal(String idpurchase);

    double amountPurchase(String idpurchase);

}
