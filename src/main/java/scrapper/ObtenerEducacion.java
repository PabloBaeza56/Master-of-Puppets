package scrapper;

import java.time.Duration;
import java.util.ArrayList;
import objetosConcretos.Educacion;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ObtenerEducacion {
  
    private WebDriver driver;
    public ObtenerEducacion(WebDriver driver) {
        this.driver = driver;
       
    }
    
    protected int buscarIndiceSeccionMain(String seccionDeseada) {
        int elementoDeseado = -1;
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        for (int i = 12; i >= 1; i--) {
            try {
                    try {
                       Thread.sleep(4 * 1000);
                   } catch (InterruptedException e) {System.out.println("Espera errorA");} 

                    WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                     System.out.println(sectionElement.getText());
                    if (sectionElement.getText().equals(seccionDeseada)){
                        elementoDeseado = i;
                        break;
                    }
               
            } catch (NoSuchElementException e) {System.out.println("Elemento no encontrado" + i);}
        }
        
        System.out.println(elementoDeseado + " elemento deseado");
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

    public ArrayList<Educacion> seccionEducacion() {
        
        ArrayList<Educacion> listaEducacion = new ArrayList<>();
        int seccionDeseada = this.buscarIndiceSeccionMain("Educación");
        
        

        int ContadorElemento = 1;
        while (true) {
            try {
               try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {System.out.println("Espera errorA");} 
               
                Educacion educacionPersona = new Educacion();
              WebElement elementoBase = this.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li[" + ContadorElemento + "]/div/div[2]/div[1]/a"));
                                                                     
              try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Espera error B");
                }
                try {
                    String Universidad = this.scrapyText(elementoBase, "xpath", "./div/div/div/div/span[1]");
                    educacionPersona.setCentroEducativo(Universidad);
                } catch (NoSuchElementException e) {System.out.println("seccionEducacion A ");}
                
                try {
                    String Carrera = this.scrapyText(elementoBase, "xpath", "./span[1]/span[1]");
                    educacionPersona.setGradoAcademico(Carrera);
                } catch (NoSuchElementException e) {System.out.println("seccionEducacion B ");}

                try {
                    String Fecha = this.scrapyText(elementoBase, "xpath", "./span[2]/span[1]");
                    String[] partesFecha = Fecha.split("-");
                    educacionPersona.setAnioIngreso(partesFecha[0]);
                    educacionPersona.setAnioEgreso(partesFecha[1]);
                } catch (NoSuchElementException e) {System.out.println("seccionEducacion C ");}
                
                
                listaEducacion.add(educacionPersona);
                System.out.println(educacionPersona);
            
            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
        return listaEducacion;
    }

    
    
    
    
}
