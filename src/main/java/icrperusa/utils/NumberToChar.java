/**
 *
 */
package icrperusa.utils;

/**
 * @author christian
 *
 */
public class NumberToChar {

    String MONEDA_SINGULAR = "Sol";
    String MONEDA_PLURAL = "Soles";

    String CENTIMOS_SINGULAR = "centimo";
    String CENTIMOS_PLURAL = "centimos";

    long MAX_NUMERO = 999999999999L;

    String[] UNIDADES = new String[]{
            "CERO",
            "UNO",
            "DOS",
            "TRES",
            "CUATRO",
            "CINCO",
            "SEIS",
            "SIETE",
            "OCHO",
            "NUEVE"
    };

    String[] DECENAS = new String[]{
            "DIEZ",
            "ONCE",
            "DOCE",
            "TRECE",
            "CATORCE",
            "QUINCE",
            "DIECISEIS",
            "DIECISIETE",
            "DIECIOCHO",
            "DIECINUEVE"
    };

    String[] DIEZ_DIEZ = new String[]{
            "CERO",
            "DIEZ",
            "VEINTE",
            "TREINTA",
            "CUARENTA",
            "CINCUENTA",
            "SESENTA",
            "SETENTA",
            "OCHENTA",
            "NOVENTA"
    };

    String[] CIENTOS = new String[]{
            "_",
            "CIENTO",
            "DOSCIENTOS",
            "TRESCIENTOS",
            "CUATROSCIENTOS",
            "QUINIENTOS",
            "SEISCIENTOS",
            "SETECIENTOS",
            "OCHOCIENTOS",
            "NOVECIENTOS"
    };

    public String numero_a_letras(double numero){
        int numero_entero = (int)numero;
        if (numero_entero > MAX_NUMERO){
            System.out.println("Número demasiado alto");
        }
        if (numero_entero < 0){
            return String.format("menos %s", numero_a_letras(Math.abs(numero)));
        }
        String letras_decimal = "";
        int parte_decimal = RoundPlaces.toDouble((Math.abs(numero) - Math.abs(numero_entero)) * 100).intValue();
        // print parte_decimal, 'decimal'
        if (parte_decimal == 0){
            letras_decimal = "CON CERO";
        }else if (parte_decimal > 9){
            letras_decimal = String.format("CON %s", numero_a_letras_con(parte_decimal));
        }else if (parte_decimal > 0){
            letras_decimal = String.format("CON CERO %s", numero_a_letras_con(parte_decimal));
        }
        String resultado = "";
        if (numero_entero <= 99){
            resultado = leer_decenas(numero_entero);
        }else if (numero_entero <= 999){
            resultado = leer_centenas(numero_entero);
        }else if (numero_entero <= 999999){
            resultado = leer_miles(numero_entero);
        } else if (numero_entero <= 999999999){
            resultado = leer_millones(numero_entero);
        } else{
            resultado = leer_millardos(numero_entero);
        }
        resultado = resultado.replace("UNO MIL", "UN MIL");
        resultado = resultado.trim();
        resultado = resultado.replace(" _ ", " ");
        resultado = resultado.replace("  ", " ");
        if (parte_decimal >= 0){
            resultado = String.format("%s %s", resultado, letras_decimal);
        }
        return resultado;
    }


    public String numero_a_letras_con(double numero){
        int numero_entero = (int) numero;
        if (numero_entero > MAX_NUMERO)
            System.err.println("Número demasiado alto");
        if (numero_entero < 0)
            return String.format("menos %s", numero_a_letras(Math.abs(numero)));
        String letras_decimal = "";
        int parte_decimal = RoundPlaces.toDouble(((Math.abs(numero) - Math.abs(numero_entero)) * 100)).intValue();
        if (parte_decimal > 9)
            letras_decimal = String.format("CON %s", numero_a_letras(parte_decimal));
        else if (parte_decimal > 0)
            letras_decimal = String.format("CON CERO %s", numero_a_letras(parte_decimal));
        String resultado = "";
        if (numero_entero <= 99)
            resultado = leer_decenas(numero_entero);
        else if (numero_entero <= 999)
            resultado = leer_centenas(numero_entero);
        else if (numero_entero <= 999999)
            resultado = leer_miles(numero_entero);
        else if (numero_entero <= 999999999)
            resultado = leer_millones(numero_entero);
        else
            resultado = leer_millardos(numero_entero);
        resultado = resultado.replace("UNO MIL", "UN MIL");
        resultado = resultado.trim();
        resultado = resultado.replace(" _ ", " ");
        resultado = resultado.replace("  ", " ");
        if (parte_decimal > 0)
            resultado = String.format("%s %s", resultado, letras_decimal);
        return resultado;
    }


    public String numero_a_moneda(double numero){
        int numero_entero = (int)numero;
        int parte_decimal = RoundPlaces.toDouble((((Math.abs(numero) - Math.abs(numero_entero)) * 100))).intValue();
        String centimos = "";
        if (parte_decimal == 1)
            centimos = CENTIMOS_SINGULAR;
        else
            centimos = CENTIMOS_PLURAL;
        String moneda = "";
        if (numero_entero == 1)
            moneda = MONEDA_SINGULAR;
        else
            moneda = MONEDA_PLURAL;
        String letras = numero_a_letras(numero_entero);
        letras = letras.replace("uno", "un");
        String letras_decimal = String.format("con %s %s", numero_a_letras(parte_decimal).replace("uno", "un"), centimos);
        letras = String.format("%s %s %s", letras, moneda, letras_decimal);
        return letras;
    }

    public String leer_decenas(int numero){
        if (numero < 10)
            return UNIDADES[numero];
        int decena, unidad;
        decena = (numero/ 10);
        unidad = (numero % 10);
        String resultado = "";
        if (numero <= 19){
            resultado = DECENAS[unidad];
        }else if (numero <= 29 && numero > 20){
            resultado = String.format("VEINTI%s", UNIDADES[unidad]);
        }else{
            resultado = DIEZ_DIEZ[decena];
            if (unidad > 0)
                resultado = String.format("%s Y %s", resultado, UNIDADES[unidad]);
        }
        return resultado;
    }

    public String leer_centenas(int numero){
        String resultado = "";
        int centena, decena;
        centena = (numero / 100);
        decena = (numero % 100);
        if (numero == 0){
            resultado = "CIEN";
        }else{
            resultado = CIENTOS[centena];
            if (decena > 0)
                resultado = String.format("%s %s", resultado, leer_decenas(decena));
        }
        return resultado;
    }

    public String leer_miles(int numero){
        int millar, centena;
        millar = (numero / 1000);
        centena = (numero % 1000);
        String resultado = "";
        if (millar == 1)
            resultado = "";
        if ((millar >= 2) && (millar <= 9))
            resultado = UNIDADES[millar];
        else if ((millar >= 10) && (millar <= 99))
            resultado = leer_decenas(millar);
        else if ((millar >= 100) && (millar <= 999))
            resultado = leer_centenas(millar);
        resultado = String.format("%s MIL", resultado);
        if (centena > 0)
            resultado = String.format("%s %s", resultado, leer_centenas(centena));
        return resultado;
    }

    public String leer_millones(int numero){
        int millon, millar;
        millon = (numero / 1000000);
        millar = (numero % 1000000);
        String resultado = "";
        if (millon == 1)
            resultado = " UN MILLON ";
        if ((millon >= 2) && (millon <= 9))
            resultado = UNIDADES[millon];
        else if ((millon >= 10) && (millon <= 99))
            resultado = leer_decenas(millon);
        else if ((millon >= 100) && (millon <= 999))
            resultado = leer_centenas(millon);
        if (millon > 1)
            resultado = String.format("%s MILLONES", resultado);
        if ((millar > 0) && (millar <= 999))
            resultado = String.format("%s %s", resultado, leer_centenas(millar));
        else if ((millar >= 1000) && (millar <= 999999))
            resultado = String.format("%s %s", resultado, leer_miles(millar));
        return resultado;
    }

    public String leer_millardos(int numero){
        int millardo, millon;
        millardo = (numero / 1000000);
        millon = (numero / 1000000);
        return String.format("%s MILLONES %s", leer_miles(millardo), leer_millones(millon));
    }

}
