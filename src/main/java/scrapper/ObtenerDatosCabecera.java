package scrapper;

import java.time.Duration;
import objetosConcretos.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObtenerDatosCabecera {
    WebDriver driver;
    public ObtenerDatosCabecera(WebDriver driver) {
       
        this.driver = driver;
        
    }
 
     protected String scrapyText(WebElement elementoBase, String tipoSelector, String selector ) {
        WebElement elemento;

        if (tipoSelector.equalsIgnoreCase("css")) {
            elemento = elementoBase.findElement(By.cssSelector(selector));
        } else if (tipoSelector.equalsIgnoreCase("xpath")) {
            elemento = elementoBase.findElement(By.xpath(selector));
        } else {
            throw new IllegalArgumentException("Tipo de selector no válido: " + tipoSelector);
        }

        String[] texto = elemento.getText().split("\\n");
        return texto[0];
    }
    public datosBasicos seccionCabcecera() {
        datosBasicos cabecera = new datosBasicos();
        int seccionDeseada = 1;
        
        try {
          Duration duracion = Duration.ofSeconds(10);  
        // Esperar hasta 10 segundos para que el elemento esté presente
        WebDriverWait wait = new WebDriverWait(driver, duracion);
        
       
        
        
        WebElement elementoBase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[2]/div[2]")));    
        
        
         
                                                        
            
                WebElement elementoA = elementoBase.findElement(By.xpath("./div[1]/div[1]"));
                String Nombre = this.scrapyText(elementoA, "xpath", ".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]");
                cabecera.setNombre(Nombre);
            

         
                WebElement elementoB = elementoBase.findElement(By.xpath("./div[1]/div[2]"));
                String Leyenda = elementoB.getText();
                cabecera.setLeyenda(Leyenda);
           

          
                WebElement elementoC = elementoBase.findElement(By.xpath("./div[2]"));
                String Ubicacion = this.scrapyText(elementoC, "xpath", ".//span[contains(@class, 'text-body-small inline t-black--light break-words')]");
                cabecera.setUbicacion(Ubicacion);
         } catch (NoSuchElementException | TimeoutException e) {
        System.out.println("Error: Elemento no encontrado.");
        }   

        
        return cabecera;
    }
    
}
