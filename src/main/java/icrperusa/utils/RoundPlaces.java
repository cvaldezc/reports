/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icrperusa.utils;

/**
 *
 * @author juan
 * @author Christian
 * 
 */
public class RoundPlaces {

	public static Double toDouble(Double valor, int places){
        Double query = 0.0;
        try {
        	String pattern = "1%1$0" + places + "d";
        	double round = Double.parseDouble(String.format(pattern, 0));
            query = (Math.round(valor*round)/round);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return query;
    }
    
    public static Double toDouble(Double value){
		return toDouble(value, 2);
    }
    
}
