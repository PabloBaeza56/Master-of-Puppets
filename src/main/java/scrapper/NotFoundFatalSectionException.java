package scrapper;

public class NotFoundFatalSectionException extends Exception {

    public NotFoundFatalSectionException() {
        super();
    }

    public NotFoundFatalSectionException(String mensaje) {
        super(mensaje);
    }

    public NotFoundFatalSectionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
