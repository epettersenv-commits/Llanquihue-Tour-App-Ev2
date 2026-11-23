package cl.agencia.turismo.util;

/**
 * Validaciones reutilizables
 *
 * @author Elizabeth Pettersen
 */
public class Validador {

    private Validador() {

    }

    /**
     * Revisa que el correo tenga un solo "@", texto antes del "@" y un "." después del "@"
     *
     * @param email correo a revisar
     * @return true si el formato es válido, false si no (o si es nulo)
     */
    public static boolean esEmailValido(String email) {
        if (email == null) {
            return false;
        }
        String correo = email.trim();
        int arroba = correo.indexOf("@");
        boolean unSoloArroba = arroba != -1 && arroba == correo.lastIndexOf("@");
        boolean textoAntes = arroba > 0;
        boolean puntoDespues = correo.indexOf(".", arroba + 2) != -1 && !correo.endsWith(".");

        return unSoloArroba && textoAntes && puntoDespues;
    }

    /**
     * Revisa que el rut contenga un guión y un largo entre 9 y 12 caracteres (ej: 12.345.678-9)
     * No calcula el dígito verificador, solo valida el formato
     *
     * @param rut rut a revisar
     * @return true si el formato es válido, false si no (o si es nulo)
     */
    public static boolean esRutValido(String rut) {
        if (rut == null) {
            return false;
        }
        String r = rut.trim();
        return r.contains("-") && r.length() >= 9 && r.length() <= 12;
    }
}
