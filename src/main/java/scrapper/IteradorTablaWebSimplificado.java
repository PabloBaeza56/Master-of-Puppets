package scrapper;

import automata.IteradorElementoTablaWeb;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IteradorTablaWebSimplificado extends IteradorElementoTablaWeb{
    
    @Getter public final Map<String, Integer> indicesSeccionesMain;

    public IteradorTablaWebSimplificado(WebDriver driver) {
        super(driver);
        this.indicesSeccionesMain = new HashMap<>();
    }
    
    public void esperarSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }
    
    public void procesarElementos(Integer seccionDeseada, String subcadenaParte1, String subcadenaParte2, Consumer<WebElement> consumer) {
        super.esperaImplicita();
        super.setSubcadenaParte1(subcadenaParte1);
        super.setSubcadenaParte2(subcadenaParte2);

        while (super.existeSiguienteElemento()) {
            super.esperaImplicita();
            WebElement elementoBase = super.driver.findElement(By.xpath(super.getXpathActual()));
            consumer.accept(elementoBase);

            super.siguienteElemento();
        }
        super.reiniciarIterador();
    }
    
    public void busquedaIndicesSeccionesMain(){
     
        for (int i = 12; i >= 1; i--) {
            try {
                this.esperarSegundos(1);
                WebElement sectionElement = this.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                String texto = sectionElement.getText();
                this.indicesSeccionesMain.put(texto, i);
                
            } catch (NoSuchElementException e) {}
        }
    }
    
}
