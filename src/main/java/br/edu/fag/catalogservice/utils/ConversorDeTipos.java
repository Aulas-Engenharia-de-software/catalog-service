package br.edu.fag.catalogservice.utils;

public final class ConversorDeTipos {
    private ConversorDeTipos() {
    }

    public static String texto(Object valor) {
        return valor instanceof String ? (String) valor : null;
    }

    public static Double numeroDecimal(Object valor) {
        if (valor == null) {
            return null;
        }

        try {
            String numero = valor.toString().trim().replace(',', '.');
            double resultado = Double.parseDouble(numero);
            return Double.isFinite(resultado) ? resultado : null;
        } catch (NumberFormatException excecao) {
            return null;
        }
    }
}
