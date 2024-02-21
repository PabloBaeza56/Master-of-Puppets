package iterador;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class IteradorPaginas {
    
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
    public abstract void siguientePagina();
    
}
