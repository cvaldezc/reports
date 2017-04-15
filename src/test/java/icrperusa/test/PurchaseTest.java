/**
 * 
 */
package icrperusa.test;

import static org.junit.Assert.*;

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
        Double requerido = obj.getIGV("OC17000001");
        Double esperado = 18.0;
        System.out.println("expected "+ requerido);
        assertEquals(requerido, esperado);
    }

}
