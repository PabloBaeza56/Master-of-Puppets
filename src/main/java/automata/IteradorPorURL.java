package automata;

import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IteradorPorURL implements IteradorPaginasBusqueda {

    @Getter
    private int paginaActual;
    private final WebDriver driver;

    public IteradorPorURL(WebDriver driver) {
        this.driver = driver;
        this.paginaActual = 1;
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

}
