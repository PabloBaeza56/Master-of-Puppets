package main;

import java.io.IOException;
import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//https://www.linkedin.com/in/luis-basto-diaz-41136396/
//https://www.linkedin.com/in/victorlavalle/

public class Main {

    public static void main(String[] args) throws IOException {
        
        ControladorMaestro controler = new ControladorMaestro();
          
        String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/rodrigo-urtecho/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/"};

        for (String elemento : arregloStrings) {
            WebDriver newDriver = new ChromeDriver();
            controler.cargarCookiesInicioSesion(newDriver);
            
            Usuario user = new Usuario();
            ExtraccionDatos extractor = new ExtraccionDatos();
            
            newDriver.get(elemento);      
            newDriver.navigate().refresh();           

            extractor.PerfilCompleto(newDriver, user); 
            newDriver.close(); 
        }
    } 
}

