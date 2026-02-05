package trata_excepciones;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcepcionesCreadas {

	// formato de gmail
	public static void validarEmail(String email) throws FormatoIncorrectoException {
		Pattern modelo = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
		Matcher matcher = modelo.matcher(email);
		if (!matcher.matches()) {
			throw new FormatoIncorrectoException("Gmail con formato incorrecto");
		}
	}

	// formato dni
	public static void comprobarDni(String dni) throws FormatoIncorrectoException {
		
		Pattern modelo = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
		Matcher matcher = modelo.matcher(dni);
		if (!matcher.matches()) {
			throw new FormatoIncorrectoException("DNI con formato incorrecto");
		}
		/*
		final String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

		int numero = Integer.parseInt(dni.substring(0, 8));
		char letraIntroducida = dni.charAt(8);
		char letraCorrecta = letras.charAt(numero % 23);

		if (letraIntroducida != letraCorrecta) {
			throw new FormatoIncorrectoException("DNI con letra incorrecta la letra es "+letraIntroducida);
		}*/
	}

	// formato numero telefono
	public static void validarNumero(String numero) throws FormatoIncorrectoException {

		Pattern modelo = Pattern.compile("\\d{9}");
		Matcher matcher = modelo.matcher(numero);

		if (!matcher.matches()) {
			throw new FormatoIncorrectoException("Numero con formato incorrecto");
		}
	}

	// Excepcion si es menor de edad
	public static void validarSiEsMayor(LocalDate fechaNac) throws FormatoIncorrectoException {

		LocalDate hoy = LocalDate.now();

		int edad = Period.between(fechaNac, hoy).getYears();

		if (edad < 18) {
			throw new FormatoIncorrectoException("Tiene que ser mayor de edad");
		}
	}

	// lo mismo pero con 16
	public static void validarSiEsMayor16(LocalDate fechaNac) throws FormatoIncorrectoException {

		LocalDate hoy = LocalDate.now();

		int edad = Period.between(fechaNac, hoy).getYears();

		if (edad < 16) {
			throw new FormatoIncorrectoException("Tiene que ser mayor de edad");
		}
	}
}
