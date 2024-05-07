package scrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import modelo.CssSelector;
import modelo.LinkUsuario;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
public class Mineable {

    protected WebDriver driver;

    protected void esperaExplicita(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
        }
    }

    protected void esperaImplicita() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    protected String minarTextoOpcional(WebElement elementoBase, CssSelector selector) {
        try {
            WebElement elemento = elementoBase.findElement(By.xpath(selector.getValue()));
            String[] texto = elemento.getText().split("\\n");
            return texto[0];
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public void settearMinadoOpcional(WebElement elementoBase, CssSelector selector, Consumer<String> setter) {
        String dato = minarTextoOpcional(elementoBase, selector);
        setter.accept(dato);
    }

    protected String minarTextoObligatorio(WebElement elementoBase, CssSelector selector, String nombreElementoMinable) throws MandatoryElementException {
        try {
            WebElement elemento = elementoBase.findElement(By.xpath(selector.getValue()));
            String[] texto = elemento.getText().split("\\n");
            return texto[0];
        } catch (NoSuchElementException e) {
            throw new MandatoryElementException("El elemento concreto " + nombreElementoMinable + " no se pudo obtener (Obligatorio)");
        }
    }

    public void settearMinadoObligatorio(WebElement elementoBase, CssSelector selector, String nombreElementoMinable, Consumer<String> setter) throws MandatoryElementException {
        String dato = minarTextoObligatorio(elementoBase, selector, nombreElementoMinable);
        setter.accept(dato);
    }

    protected String minarTextoObligatorio(WebElement elemento, String nombreElementoMinable) throws MandatoryElementException {
        try {
            return elemento.getText();
        } catch (NoSuchElementException e) {
            throw new MandatoryElementException("El elemento concreto " + nombreElementoMinable + " no se pudo obtener (Obligatorio)");
        }
    }

    public void settearMinadoObligatorio(WebElement elemento, String nombreElementoMinable, Consumer<String> setter) throws MandatoryElementException {
        String dato = minarTextoObligatorio(elemento, nombreElementoMinable);
        setter.accept(dato);
    }

    protected String obtenerLink(WebElement elementoBase) {

        WebElement enlace = elementoBase.findElement(By.tagName("a"));
        String cadenaSalida = enlace.getAttribute("href");

        return cadenaSalida;
    }

    public static <T> ArrayList<T> obtenerElementosNoDuplicados(ArrayList<T> lista) {
        Set<T> set = new HashSet<>(lista);
        return new ArrayList<>(set);
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
    
    

}
