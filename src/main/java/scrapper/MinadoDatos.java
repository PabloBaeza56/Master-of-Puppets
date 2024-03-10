package scrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MinadoDatos {
    protected WebDriver driver;

    public MinadoDatos(WebDriver driver) {
        this.driver = driver;
    }
    
    public void irPagina(String url){
        this.driver.get(url);
    }

    protected void esperarSegundos(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}  
    }

    protected int buscarIndiceSeccionMain(String seccionDeseada) {
        int elementoDeseado = -1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        
        for (int i = 12; i >= 1; i--) {
            try {
                    this.esperarSegundos(2);

                    WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                    if (sectionElement.getText().equals(seccionDeseada)){
                        elementoDeseado = i;
                        break;
                    }
               
            } catch (NoSuchElementException e) {}
        }
        
        return elementoDeseado;
    }
    
    protected int buscarIndiceSeccionAside(String seccionDeseada) {
        int elementoDeseado = 0;
        for (int i = 12; i >= 1; i--) {
            try {
            esperarSegundos(1);
            WebElement sectionElement = this.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/aside/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                                                                     
            if (sectionElement.getText().equals(seccionDeseada)){
                elementoDeseado = i;
                break;
            }
            } catch (NoSuchElementException e) {}
        }
        
        return elementoDeseado;
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
    
    protected String obtenerLink(WebElement elementoBase){
        
        WebElement enlace = elementoBase.findElement(By.tagName("a"));
        String cadenaSalida = enlace.getAttribute("href");  
        
        return cadenaSalida;
    }
    
    protected List<String> obtenerLinks() {

        List<WebElement> enlaces = driver.findElements(By.tagName("a"));
        List<String> elementosValidos = new ArrayList<>();

        for (WebElement enlace : enlaces) {
            String url = enlace.getAttribute("href");
            if (url != null && url.contains("https://www.linkedin.com/in/")) {
                char primerChar = url.charAt(28);

                if (Character.isLowerCase(primerChar)) {
                    elementosValidos.add(url);
                }
            }
        }

        List<String> arregloFinal = eliminarDuplicados(elementosValidos);

        return arregloFinal;
    }
    
    
    protected List<String> eliminarDuplicados(List<String> lista) {
        Set<String> conjunto = new HashSet<>(lista);
        return new ArrayList<>(conjunto);
    }
    
    
    
    
}
