package scrapper;

public class MandatoryElementException extends Exception {
    public MandatoryElementException() {
        super();
    }
    
    public MandatoryElementException(String mensaje) {
        super(mensaje);
    }

    public MandatoryElementException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
