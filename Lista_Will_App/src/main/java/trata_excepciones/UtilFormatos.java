package trata_excepciones;

import java.time.LocalDate;

public class UtilFormatos {
	public static boolean dniFormato(String dni) {
		try {
			ExcepcionesCreadas.comprobarDni(dni);
			return true;
		}
		catch(FormatoIncorrectoException e){
			return false;
		}
	}
	
	public static boolean correoFormato(String correo) {
		try {
			ExcepcionesCreadas.validarEmail(correo);
			return true;
		}catch(FormatoIncorrectoException e) {
			return false;
		}
	}
	
	public static boolean formatoNumero(String numero) {
		try {
			ExcepcionesCreadas.validarNumero(numero);
			return true;
		}catch(FormatoIncorrectoException e) {
			return false;
		}
	}
	
	public static boolean mayorDeEdad(LocalDate fechaNac) {
		try {
			ExcepcionesCreadas.validarSiEsMayor(fechaNac);
			return true;
		}catch(FormatoIncorrectoException e) {
			return false;
		}
	}
	
	public static boolean mayorDeEdad16(LocalDate fechaNac) {
		try {
			ExcepcionesCreadas.validarSiEsMayor16(fechaNac);
			return true;
		}catch(FormatoIncorrectoException e) {
			return false;
		}
	}
	
	
}
