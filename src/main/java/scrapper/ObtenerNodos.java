package scrapper;

import automata.AutomataDatos;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// No utilizado en la Iteracion Actual
public class ObtenerNodos extends MinadoDatos{
    private AutomataDatos movilizador;

    public ObtenerNodos(WebDriver driver, AutomataDatos movilizador) {
        super(driver);
        this.movilizador = movilizador;
    }
    
    public void UsuariosRelacionadosA() {
 
        int seccionDeseada = this.movilizador.getIndicesSeccionesAside().get("Otros perfiles vistos");

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/aside/section[" + seccionDeseada + "]/div[3]/div/div/div/a"));
        elementoBase.sendKeys(Keys.ENTER);                                                     
        super.esperarSegundos(2);
        
        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[3]/div/div/div[2]/div/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]");
        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {
            
            WebElement elementoInterno = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));
                 
            String url = super.obtenerLink(elementoInterno);
            System.out.println(url);

            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
    }
    
    public void UsuariosRelacionadosB() {
    
        int seccionDeseada = this.movilizador.getIndicesSeccionesAside().get("Gente que podrías conocer");

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/aside/section[" + seccionDeseada + "]/div[3]/div/div/div/a"));                                                    
        elementoBase.sendKeys(Keys.ENTER);                                                     
        super.esperarSegundos(2);

        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[3]/div/div/div[2]/div/div[3]/div/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]");
        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {
           
            WebElement elementoInterno = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));
                
            String url = super.obtenerLink(elementoInterno);
            System.out.println(url);

            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
    }
    
}
