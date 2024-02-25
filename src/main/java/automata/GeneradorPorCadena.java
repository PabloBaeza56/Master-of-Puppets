package automata;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneradorPorCadena {
    
    public static String metodoURL(String cadena) {
        String[] lineas = cadena.split(" ");
        String cadenaSalida;

        String[] nuevoArreglo = new String[lineas.length];
        int index = 0;
        for (String linea : lineas) {
            String lineaLimpia = linea.trim();
            if (!lineaLimpia.isEmpty()) {
                nuevoArreglo[index] = lineaLimpia;
                index++;
            }
        }

        StringBuilder preCadena = new StringBuilder();
        for (int i = 0; i < index - 1; i++) {
            String subCadena = nuevoArreglo[i];
            preCadena.append(subCadena).append("%20");
        }

        preCadena.append(nuevoArreglo[index - 1]);

        cadenaSalida = "https://www.linkedin.com/search/results/people/?keywords=" + preCadena.toString() + "&origin=GLOBAL_SEARCH_HEADER&page=XXXXX&sid=";
        
        //Si llega a existir este caso se ejecutara
        cadenaSalida = cadenaSalida.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        
             
        return cadenaSalida;
    }
    
    public static void metodoDirecto(String cadena, WebDriver driver) {
        // Encontrar el campo de entrada por su XPath
        WebElement inputField = driver.findElement(By.xpath("/html/body/div[5]/header/div/div/div/div[1]/input"));
        inputField.sendKeys(cadena);
        inputField.sendKeys(Keys.ENTER);
        
        //Hacer que la pagina espere hasta que cargue la pagina
       try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {}  

        
       int IndicePersonas = 0;
       for (int i = 1; i < 7; i++){
           try {
                WebElement button = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/section/div/nav/div/ul/li["+i+"]/button"));
                if (button.getText().equals("Personas")){        
                    IndicePersonas = i;
                    break; 
                }
           } catch (NoSuchElementException e) {}                                               
       }
       
      

        //WebElement button = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/section/div/nav/div/ul/li[1]/button"));

        // Enfocar el botón (opcional)
        //button.sendKeys(Keys.ENTER);

        WebElement button = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/section/div/nav/div/ul/li["+IndicePersonas+"]/button"));
                                                                                                
        // Enfocar el botón (opcional)
        button.sendKeys(Keys.ENTER);
    }
  
}
