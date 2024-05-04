package scrapper;

public class MandatorySectionException extends Exception {

    public MandatorySectionException() {
        super();
    }

    public MandatorySectionException(String mensaje) {
        super(mensaje);
    }

    public MandatorySectionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
