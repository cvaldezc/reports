/**
 * 
 */
package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author christian
 *
 */
public class NumberTOWords {

    String [] unidades = {"CERO","UNO","DOS","TRES","CUATRO","CINCO","SEIS",
            "SIETE","OCHO","NUEVE"};
    String [] decenas = {"DIEZ","ONCE","DOCE","TRECE","CATORCE","QUINCE","DIECISEIS",
            "DIECISIETE","DIECIOCHO","DIECINUEVE"};
    String [] diez_diez = {"CERO","DIEZ","VEdoubleE","TREdoubleA","CUARENTA","CINCUENTA",
            "SESENTA","SETENTA","OCHENTA","NOVENTA"};
    String [] cientos = {"_","CIENTO","DOSCIENTOS","TRESCIENTOS","CUATROCIENTOS",
            "QUINIENTOS","SEISCIENTOS","SETECIENTOS","OCHOCIENTOS","NOVECIENTOS"};
    String centimos_singular = "centimo";
    String centimos_plural = "centimos";
    String moneda_singular = "NUEVO SOL";
    String moneda_plural = "NUEVOS SOLES";

    double MAX_NUMERO = 999999999;
    public String numero_a_letras(double numero){
        String resultado = null;
        double numero_entero =numero;
        try {
            if(numero_entero > MAX_NUMERO){
                System.out.println("Numer alto");
            }
            if(numero_entero < 0){
                return "menos "+numero_a_letras(Math.abs(numero));
            }
            String letras_decimal = "";
            double parte_decimal = Math.round((Math.abs(numero) - Math.abs(numero_entero))*100);
            System.out.println("decimal "+parte_decimal);
            if(parte_decimal == 0){
                letras_decimal = " CON CERO ";
            }else if(parte_decimal > 9){
                letras_decimal = " CON " + numero_a_letras_con(parte_decimal);
            }else if(parte_decimal > 0){
                letras_decimal = " CON CERO " + numero_a_letras_con(parte_decimal);
            }
            if(numero_entero <= 99){
                resultado = leer_decenas(numero_entero);
            }else if(numero_entero <= 999){
                resultado = leer_centenas(numero_entero);
            }else if(numero_entero <= 999999){
                resultado = leer_miles(numero_entero);
            }else if(numero_entero <= 999999999){
                resultado = leer_millones(numero_entero);
            }else{
                resultado = leer_millardos(numero_entero);
            }
            resultado = resultado.replace("UNO MIL", "UN MIL");
            resultado = resultado.replace(" _ ", " ");
            resultado = resultado.replace("  ", " ");
            if(parte_decimal >=0){
                resultado = resultado + letras_decimal;
            }

        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }

        return resultado;
    }

    public String numero_a_letras_con(double numero){
        String resultado = null;
        double numero_entero = numero;

        try {

            if(numero_entero > MAX_NUMERO){
                System.out.println("numero alto");
            }
            if(numero_entero < 0){
                return "menos "+numero_a_letras(Math.abs(numero));
            }
            String letras_decimal = "";
            double parte_decimal = Math.round((Math.abs(numero)-Math.abs(numero_entero))*100);
            System.out.println("decimal "+parte_decimal);
            if(parte_decimal > 9){
                letras_decimal = " CON "+numero_a_letras(parte_decimal);
            }else if(parte_decimal > 0){
                letras_decimal = " CON CERO "+numero_a_letras(parte_decimal);
            }

            if(numero_entero <= 99){
                resultado = leer_decenas(numero_entero);
            }else if(numero_entero <= 999){
                resultado = leer_centenas(numero_entero);
            }else if(numero_entero <= 999999){
                resultado = leer_miles(numero_entero);
            }else if(numero_entero <= 999999999){
                resultado = leer_millones(numero_entero);
            }else{
                resultado = leer_millardos(numero_entero);
            }
            resultado = resultado.replace("UNO MIL", "UN MIL");
            resultado = resultado.replace(" _ ", " ");
            resultado = resultado.replace("  ", " ");
            if(parte_decimal > 0){
                resultado = resultado + letras_decimal;
            }
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public String numero_a_moneda(double numero){
        String letras = null;
        double numero_entero = numero;
        double parte_decimal = Math.round((Math.abs(numero)-Math.abs(numero_entero))*100);
        String centimos = "";
        String letras_decimal = null;
        try {
            if(parte_decimal == 1){
                centimos = centimos_singular;
            }else{
                centimos = centimos_plural;
            }

            String moneda = "";
            if(numero_entero == 1){
                moneda = moneda_singular;
            }else{
                moneda = moneda_plural;
            }

            letras = numero_a_letras(numero_entero);
            letras = letras.replace("uno", "un");
            letras_decimal = "con "+numero_a_letras(parte_decimal).replace("uno", "un")+" "+centimos;
            letras = letras+" "+moneda+" "+letras_decimal;
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return letras;
    }


    public String leer_decenas(double numero){
        String resultado=null;
        try {
            if(numero < 10){
                return unidades[(int)numero];
            }
            //doubleeger x = doubleeger.valueOf(numero);
            int decena = (int)numero / 10;
            int unidad = (int)numero % 10;
            System.out.println("decena " +decena);
            System.out.println("unidad " +unidad);
            if(numero <= 19){
                resultado = decenas[unidad];
            }else if(numero <=29 && numero >20){
                resultado = "VEdoubleI"+unidades[unidad];
            }else{
                resultado = diez_diez[decena];
                if(unidad > 0){
                    resultado = resultado + " Y " +unidades[unidad];
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public String leer_centenas(double numero){
        String resultado = null;
        double centena = numero / 100;
        double decena = numero % 100;
        try {
            if(numero == 0){
                resultado = "CIEN";
            }else{
                resultado = cientos[(int)centena];
                if(decena > 0){
                    resultado = resultado +" "+ leer_decenas(decena);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public String leer_miles(double numero){
        String resultado = "";
        double millar = numero / 1000;
        double centena = numero % 1000;
        try {
            if(millar == 1){
                resultado = "";
            }
            if(millar >= 2 && millar <=9 ){
                resultado = unidades[(int)millar];
            }else if(millar >=10 && millar <=99){
                resultado = leer_decenas(millar);
            }else if (millar >= 100 && millar <= 999){
                resultado = leer_centenas(millar);
            }
            resultado = resultado+" MIL";

            if(centena > 0){
                resultado = resultado +" "+ leer_centenas(centena);
            }
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }

        return resultado;
    }

    public String leer_millones(double numero){
        String resultado = "";
        double millon = numero / 1000000;
        double millar = numero % 1000000;
        try {
            if(millon == 1){
                resultado = " UN MILLON ";
            }

            if(millon >= 2 && millon <= 9){
                resultado = unidades[(int)millon];
            }else if(millon >= 10 && millon <= 99){
                resultado = leer_decenas(millon);
            }else if(millon >= 100 && millon <= 999){
                resultado = leer_centenas(millon);
            }

            if(millon > 1){
                resultado = resultado +" MILLONES";
            }
            if(millar > 0 && millar <= 999){
                resultado = resultado +" "+ leer_centenas(millar);
            }else if(millar >= 1000 && millar <= 999999){
                resultado = resultado +" "+ leer_miles(millar);
            }

        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public String leer_millardos(double numero){
        double millardo = numero / 1000000;
        double millon = numero % 1000000;
        return leer_miles(millardo)+" MILLONES" + leer_millones(millon);
    }

}
