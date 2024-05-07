package scrapper;

public interface ScrapeableProduct<T> {

    public T reclamarDatos() throws MandatoryElementException, NotFoundFatalSectionException;

    public Boolean existeSeccion();
   

}
