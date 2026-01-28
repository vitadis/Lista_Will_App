package trata_excepciones;

public class DniIncorrectoException extends Exception {

    public DniIncorrectoException(String mensaje) {
        super(mensaje);
    }
}