package scrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
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

    protected String minarTextoOpcional(WebElement elementoBase, String selector) {
        try {
            WebElement elemento = elementoBase.findElement(By.xpath(selector));
            String[] texto = elemento.getText().split("\\n");
            return texto[0];
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public void settearMinadoOpcional(WebElement elementoBase, String selector, Consumer<String> setter) {
        String dato = minarTextoOpcional(elementoBase, selector);
        setter.accept(dato);
    }

    protected String minarTextoObligatorio(WebElement elementoBase, String selector, String nombreElementoMinable) throws MandatoryElementException {
        try {
            WebElement elemento = elementoBase.findElement(By.xpath(selector));
            String[] texto = elemento.getText().split("\\n");
            return texto[0];
        } catch (NoSuchElementException e) {
            throw new MandatoryElementException("El elemento concreto " + nombreElementoMinable + " no se pudo obtener (Obligatorio)");
        }
    }

    public void settearMinadoObligatorio(WebElement elementoBase, String selector, String nombreElementoMinable, Consumer<String> setter) throws MandatoryElementException {
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
    
    public Integer MinadoSeccionObligatoria(IteradorTablaWebSimplificado movilizador, String seccion) throws MandatorySectionException{
        try {
            return movilizador.getIndicesSeccionesMain().get(seccion);
        } catch (NullPointerException e) {
            throw new MandatorySectionException("El seccion concreta " + seccion + " no se pudo obtener (Obligatorio)");
        }
        
    }

}
