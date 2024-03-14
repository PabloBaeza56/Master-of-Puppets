package main;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//https://www.linkedin.com/in/luis-basto-diaz-41136396/
//https://www.linkedin.com/in/victorlavalle/
//Error educacion Caso Cambranes
//Error ubicacion vacia
public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        ControladorMaestro controler = new ControladorMaestro();

        String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/ecambranes/", "https://www.linkedin.com/in/manuelmartinrico/"};
        for (String elemento : arregloStrings) {
            
            WebDriver newDriver = new ChromeDriver();
            newDriver.get("https://www.linkedin.com/login");
            Map<String, String> cookies = controler.leerCookiesDesdeArchivo("cookies.txt");
            controler.cargarCookiesInicioSesion(cookies, newDriver);
            newDriver.navigate().refresh();
          
            
            ExtraccionDatos extractor = new ExtraccionDatos();
            
            newDriver.get(elemento);
            Usuario usuario = extractor.PerfilCompleto(newDriver);
            System.out.println(usuario);
            newDriver.close(); 
        }
         
    } 
}

