package scrapper;

import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import modelo.CssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public class Mineable {

    protected WebDriver driver;

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

}
