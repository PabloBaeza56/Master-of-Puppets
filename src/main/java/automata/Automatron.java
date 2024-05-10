package automata;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static scrapper.Waitable.esperaExplicita;
import static scrapper.Waitable.esperaImplicita;

public class Automatron extends IteradorElementoTablaWeb{
    
    @Getter public final Map<String, Integer> indicesSeccionesMain;

    public Automatron(WebDriver driver) {
        super(driver);
        this.indicesSeccionesMain = new HashMap<>();
    }
    
   
    public void procesarElementos(Integer seccionDeseada, String subcadenaParte1, String subcadenaParte2, Consumer<WebElement> consumer) {
        esperaImplicita(super.driver);
        super.setSubcadenaParte1(subcadenaParte1);
        super.setSubcadenaParte2(subcadenaParte2);

        while (super.existeSiguienteElemento()) {
            esperaImplicita(super.driver);
            WebElement elementoBase = super.driver.findElement(By.xpath(super.getXpathActual()));
            consumer.accept(elementoBase);

            super.siguienteElemento();
        }
        super.reiniciarIterador();
    }
    
    public void busquedaIndicesSeccionesMain(){
     
        for (int i = 12; i >= 1; i--) {
            try {
                esperaExplicita(1);
                WebElement sectionElement = this.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                String texto = sectionElement.getText();
                this.indicesSeccionesMain.put(texto, i);
                
            } catch (NoSuchElementException e) {}
        }
    }
    
    
    
}
