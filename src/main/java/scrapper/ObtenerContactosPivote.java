package scrapper;

import iterador.IteradorPorURL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ObtenerContactosPivote extends MinadoDatos {
    
    WebDriver driver;
    public ObtenerContactosPivote(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    
    public void AccederContactos(){
    
        
        WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/ul/li"));
        WebElement enlace = elementoBase.findElement(By.tagName("a"));
        String cadenaSalida = enlace.getAttribute("href");  
        System.out.println(cadenaSalida);
        super.esperarSegundos(8);

        cadenaSalida = cadenaSalida.replace("&origin=MEMBER_PROFILE_CANNED_SEARCH", "&origin=MEMBER_PROFILE_CANNED_SEARCH&page=XXXXX");

        IteradorPorURL iterador = new IteradorPorURL(this.driver);
        iterador.iniciarIteracion(cadenaSalida);
        
    }
    
    public void ActualizarPivotes(){
        driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");
        int i = 1;
        while (true){
            try {
                super.esperarSegundos(1);

                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div/div/div[2]/div/div/main/div/section/div[2]/div[1]/ul/li["+i+"]"));

                WebElement enlace = elementoBase.findElement(By.tagName("a"));
                String url = enlace.getAttribute("href");
                System.out.println(url);


            } catch (NoSuchElementException e) {
                break;
            }
            i++;
            
            
            
        }
        
    }
    
}
