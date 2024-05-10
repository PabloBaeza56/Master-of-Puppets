package automata;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IteradorElementoTablaWeb implements IteradorElementosListaWeb {
    
    @Getter private Integer IndiceFilatabla;
    protected final WebDriver driver;
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

  

    
    
}
