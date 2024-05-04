package automata;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IteradorElementoTablaWeb implements IteradorElementosListaWeb {
    
    @Getter private Integer IndiceFilatabla;
    public final WebDriver driver;
    @Getter @Setter public String subcadenaParte1;
    @Getter @Setter public String subcadenaParte2;
    
    public IteradorElementoTablaWeb(WebDriver driver){
        this.driver = driver;
        this.IndiceFilatabla = 1;
    }
    
    public String getXpathActual(){
        return this.subcadenaParte1 + this.IndiceFilatabla + this.subcadenaParte2;
    }
    
    @Override
    public void siguienteElemento() {
        this.IndiceFilatabla++;
    }

    @Override
    public boolean existeSiguienteElemento() {
        if (this.driver.findElements(By.xpath(this.subcadenaParte1 + this.IndiceFilatabla + this.subcadenaParte2)).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean existeSiguienteElemento(WebElement driver) {
        if (driver.findElements(By.xpath(this.subcadenaParte1 + this.IndiceFilatabla + this.subcadenaParte2)).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void reiniciarIterador(){
        this.IndiceFilatabla = 1;
    }

    public void esperaImplicita() {
         WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    
    
}
