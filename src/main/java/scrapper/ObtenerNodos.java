package scrapper;

import automata.AutomataDatos;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerNodos extends MinadoDatos{

    private final AutomataDatos movedor;
    public ObtenerNodos(WebDriver driver) {
        super(driver);
        this.movedor = new AutomataDatos(driver);
    }
    
    public void UsuariosRelacionadosA() {
        int ContadorElemento = 1;
        int seccionDeseada = movedor.buscarIndiceSeccionAside("Otros perfiles vistos");

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/aside/section[" + seccionDeseada + "]/div[3]/div/div/div/a"));
        elementoBase.sendKeys(Keys.ENTER);                                                     
        super.esperarSegundos(1);
        
        while (true) {
            try {

                WebElement elementoInterno = super.driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/ul/li["+ContadorElemento+"]"));
                 
                String url = super.obtenerLink(elementoInterno);
                System.out.println(url);

            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
    }
    
    public void UsuariosRelacionadosB() {
        int ContadorElemento = 1;
        int seccionDeseada = movedor.buscarIndiceSeccionAside("Gente que podrías conocer");

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/aside/section[" + seccionDeseada + "]/div[3]/div/div/div/a"));                                                    
        elementoBase.sendKeys(Keys.ENTER);                                                     
        super.esperarSegundos(10);
        
        while (true) {
            try {
                                                                         
                WebElement elementoInterno = super.driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div[3]/div/ul/li["+ContadorElemento+"]"));
                
                String url = super.obtenerLink(elementoInterno);
                System.out.println(url);

            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
    }
    
}
