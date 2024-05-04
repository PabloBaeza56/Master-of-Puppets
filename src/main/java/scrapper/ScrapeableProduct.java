package scrapper;

public interface ScrapeableProduct<T> {
    T reclamarDatos() throws MandatoryElementException, MandatorySectionException;   
}
