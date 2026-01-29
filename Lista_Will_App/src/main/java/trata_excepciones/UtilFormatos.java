package trata_excepciones;

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
	
	
}
