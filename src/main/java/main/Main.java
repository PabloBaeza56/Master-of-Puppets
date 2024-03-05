package main;

import org.openqa.selenium.WebDriver;



public class Main {

    public static void main(String[] args) {
        
        ControladorMaestro controlador = ControladorMaestro.getInstance();
        WebDriver driver = controlador.getDriver();
        //BusquedaLinks buscador = new BusquedaLinks(driver);
        ExtraccionDatos extractor = new ExtraccionDatos();
        extractor.PerfilCompleto("https://www.linkedin.com/in/luis-basto-diaz-41136396/");
        //buscador.insercionDirectaBuscador("fmat uady");
        
        
      
        
       

        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        
        
        
        driver.quit();
    }
    
    
    
   
    
}

