/**
 *
 */
package icrperusa.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import icrperusa.businesslogical.BLPurchase;

/**
 * @author christian
 *
 */
public class PurchaseTest {

    @SuppressWarnings("deprecation")
    @Test
    public void testIgv(){
        BLPurchase obj = new BLPurchase();
        Double requerido = obj.getIGV("OC17000102");
        Double esperado = 0.0;
        System.out.println("expected "+ requerido);
        assertEquals(requerido, esperado);
    }

}
