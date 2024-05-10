package scrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.LinkUsuario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ObtenerLinksLinkedin extends Mineable {

    public ObtenerLinksLinkedin(WebDriver driver) {
        super(driver);
    }

    protected String obtenerLink(WebElement elementoBase) {

        WebElement enlace = elementoBase.findElement(By.tagName("a"));
        String cadenaSalida = enlace.getAttribute("href");

        return cadenaSalida;
    }

    public ArrayList<LinkUsuario> obtenerLinksUsuariosLinkedIn() {

        List<WebElement> enlaces = driver.findElements(By.tagName("a"));

        ArrayList<LinkUsuario> elementosValidos = new ArrayList<>();

        for (WebElement enlace : enlaces) {
            String url = enlace.getAttribute("href");
            if (url != null && url.contains("https://www.linkedin.com/in/")) {
                char primerChar = url.charAt(28);

                if (Character.isLowerCase(primerChar)) {
                    LinkUsuario user = new LinkUsuario();
                    user.setUrlUsuario(url);
                    elementosValidos.add(user);
                }
            }
        }

        ArrayList<LinkUsuario> arregloFinal = obtenerElementosNoDuplicados(elementosValidos);

        return arregloFinal;
    }
    
     public static <T> ArrayList<T> obtenerElementosNoDuplicados(ArrayList<T> lista) {
        Set<T> set = new HashSet<>(lista);
        return new ArrayList<>(set);
    }

}
