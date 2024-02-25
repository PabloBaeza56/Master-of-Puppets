package iterador;

import database.ArrayListQueue;
import org.openqa.selenium.WebDriver;
import scrapper.LinksUsuario;

public class IteradorPorURL extends IteradorPaginas {

    private final WebDriver driver;
    private final ArrayListQueue cola;
    private final LinksUsuario obtenedor;
    private int paginaActual;

    public IteradorPorURL(WebDriver driver) {
        this.driver = driver;
        this.cola = new ArrayListQueue();
        this.obtenedor = new LinksUsuario(driver);
        this.paginaActual = 1;
    }

    public void iniciarIteracion(String varOriginal) {

        while (!this.esUltimaPagina(driver)) {

            driver.get(varOriginal.replace("XXXXX", String.valueOf(this.paginaActual)));

            this.cola.launcherURLS(this.obtenedor.obtenerLinks());

            this.siguientePagina();
        }
    }

    @Override
    public void siguientePagina() {
        this.paginaActual++;
    }

    
}
