package scrapper;

import static utilidades.Generales.esperarSegundos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LinksUsuario {
    
    private final WebDriver driver;
    
    public LinksUsuario(WebDriver driver){
        this.driver = driver;   
    }
    
    public List<String> obtenerLinks() {
        esperarSegundos(3);

        List<WebElement> enlaces = driver.findElements(By.tagName("a"));
        List<String> elementosValidos = new ArrayList<>();

        for (WebElement enlace : enlaces) {
            String url = enlace.getAttribute("href");
            if (url != null && url.contains("https://www.linkedin.com/in/")) {
                char primerChar = url.charAt(28);

                if (Character.isLowerCase(primerChar)) {
                    elementosValidos.add(url);
                }
            }
        }

        List<String> arregloFinal = eliminarDuplicados(elementosValidos);

        return arregloFinal;
    }
    
    
    private List<String> eliminarDuplicados(List<String> lista) {
        Set<String> conjunto = new HashSet<>(lista);
        return new ArrayList<>(conjunto);
    }
    
}
