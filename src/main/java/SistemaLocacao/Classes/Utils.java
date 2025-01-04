/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaLocacao.Classes;

/**
 *
 * @author leticia
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Utils {

    private static final DecimalFormat BRL_FORMATTER;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.'); // Ponto para separar milhares
        symbols.setDecimalSeparator(','); // Vírgula para separar decimais

        BRL_FORMATTER = new DecimalFormat("#,##0.00", symbols);
    }

    public static String formatarMonetario(double valor) {
        return BRL_FORMATTER.format(valor);
    }
}
