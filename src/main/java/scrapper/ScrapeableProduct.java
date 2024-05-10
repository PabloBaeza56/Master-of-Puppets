package scrapper;


import static scrapper.Waitable.esperaExplicita;


public interface ScrapeableProduct<T> {

    public T reclamarDatos() throws MandatoryElementException, NotFoundFatalSectionException;

    public Boolean existeSeccion();

    default T minarTemplate() throws MandatoryElementException, NotFoundFatalSectionException {
       
        esperaExplicita(2);

        if (existeSeccion()) {
            return reclamarDatos();
        } else {
            return null;
        }
    }
}
