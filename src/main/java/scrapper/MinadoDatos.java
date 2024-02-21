package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MinadoDatos {
    protected WebDriver driver;

    public MinadoDatos(WebDriver driver) {
        this.driver = driver;
    }

    public void esperarSegundos(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}  
    }

    public int buscarIndiceSeccion(String seccionDeseada) {
        int elementoDeseado = 0;
        for (int i = 12; i >= 1; i--) {
            try {
            esperarSegundos(1);
            WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
       
            if (sectionElement.getText().equals(seccionDeseada)){
                elementoDeseado = i;
                break;
            }
            } catch (NoSuchElementException e) {}
        }
        
        return elementoDeseado;
    }
    
   
    public String scrapyText(WebElement elementoBase, String tipoSelector, String selector ) {
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
}
