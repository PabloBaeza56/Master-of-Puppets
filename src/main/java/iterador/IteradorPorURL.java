package iterador;

import database.ArrayListQueue;
import static utilidades.Generales.esperarSegundos;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import scrapper.LinksUsuario;

public class IteradorPorURL {
    private final WebDriver driver;
    private ArrayListQueue cola;
    
    public IteradorPorURL(WebDriver driver){
        this.driver = driver;   
        this.cola = new ArrayListQueue();
    }
    
    public void iniciarIteracion(String varOriginal) { 
        varOriginal = varOriginal.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        LinksUsuario obtenedor = new LinksUsuario(driver);

        boolean esUltimaPagina = esUltimaPagina();

        int paginaVisitar = 1;
        while (!esUltimaPagina) {
            
            String rutamodificada = varOriginal.replace("XXXXX", String.valueOf(paginaVisitar));
            driver.get(rutamodificada);

            esUltimaPagina = esUltimaPagina();
            
            
            List<String> datos = obtenedor.obtenerLinks();
            for (String elemento : datos) {
                this.cola.launcherURLS(elemento);
            }
            paginaVisitar++;
        }
        cola.vaciarDatos();
    }
    
    public boolean esUltimaPagina() {
        boolean fin = false;

        esperarSegundos(3);
        
        List<WebElement> elementosH2 = driver.findElements(By.tagName("h2"));

        esperarSegundos(3);

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
