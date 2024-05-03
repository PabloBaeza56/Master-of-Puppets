package automata;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomataDatos{
    private final WebDriver driver;
    public IteradorElementoTablaWeb iteradorTabla;
    public IteradorPorURL iteradorURL;
    @Getter private final Map<String, Integer> indicesSeccionesMain;

    public AutomataDatos(WebDriver driver) {
        this.driver = driver;
        this.indicesSeccionesMain = new HashMap<>();
        this.iteradorTabla = new IteradorElementoTablaWeb(this.driver);
        this.iteradorURL = new IteradorPorURL(this.driver);
    }
    
    public void esperarSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }

    public void esperarFinCargaPagina() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
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
