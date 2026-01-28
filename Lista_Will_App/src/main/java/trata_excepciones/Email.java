package trata_excepciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import trata_excepciones.EmailIncorrectoException;

public class Email {

private static void validarEmail(String email) throws EmailIncorrectoException {
    Pattern modelo = Pattern.compile("^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{3})$");
    Matcher matcher = modelo.matcher(email);
    if (!matcher.matches()) {
        throw new EmailIncorrectoException("DNI con formato incorrecto");
    }
}
}
