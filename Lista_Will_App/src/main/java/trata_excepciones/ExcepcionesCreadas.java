package trata_excepciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcepcionesCreadas {

	@SuppressWarnings("unused")
	static void validarEmail(String email) throws FormatoIncorrectoException {
		Pattern modelo = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
		Matcher matcher = modelo.matcher(email);
		if (!matcher.matches()) {
			throw new FormatoIncorrectoException("DNI con formato incorrecto");
		}
	}

	public static void comprobarDni(String dni) throws FormatoIncorrectoException {
		Pattern modelo = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
		Matcher matcher = modelo.matcher(dni);
		if (!matcher.matches()) {
			throw new FormatoIncorrectoException("DNI con formato incorrecto");
		}
	}
	
}
