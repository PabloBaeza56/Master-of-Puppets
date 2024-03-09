package automata;

import database.ArrayListQueue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import scrapper.MinadoDatos;

public class IteradorPorURL extends MinadoDatos implements IteradorPaginas {

    private final ArrayListQueue cola;
    private int paginaActual;

    public IteradorPorURL(WebDriver driver) {
        super(driver);
        this.cola = new ArrayListQueue();
        this.paginaActual = 1;
    }

    public void iniciarIteracion(String varOriginal) {

        while (!this.esUltimaPagina(driver)) {

            driver.get(varOriginal.replace("XXXXX", String.valueOf(this.paginaActual)));
            this.cola.launcherURLS(super.obtenerLinks());
            this.siguientePagina();
            
        }
    }

    @Override
    public void siguientePagina() {
        this.paginaActual++;
    }
    
    @Override
    public boolean esUltimaPagina(WebDriver driver) {
        boolean fin = false;

        List<WebElement> elementosH2 = driver.findElements(By.tagName("h2"));

        for (WebElement elemento : elementosH2) {
            String cadena = elemento.getText();
            if (cadena.contains("Ningún resultado encontrado")) {
                fin = true;
                break;
            }
        }
        return fin;
    }

    @Override
    public void reiniciar() {
        this.paginaActual = 1;
    }

   

    
}
