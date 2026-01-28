package trata_excepciones;

class EmailIncorrectoException extends Exception {
	public EmailIncorrectoException(String mensaje) {
		super(mensaje);
	}
}