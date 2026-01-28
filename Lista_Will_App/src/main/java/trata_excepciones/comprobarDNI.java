package trata_excepciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import trata_excepciones.DniIncorrectoException;

public class comprobarDNI{

public static void comprobarDni(String dni) throws DniIncorrectoException {
    Pattern modelo = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
    Matcher matcher = modelo.matcher(dni);
    if (!matcher.matches()) {
        throw new DniIncorrectoException("DNI con formato incorrecto");
    }
	}
}
