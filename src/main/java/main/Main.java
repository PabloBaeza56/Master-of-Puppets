package main;

import automata.BuscarPorBarraBusqueda;
import database.MongoDBConnection;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//Turkish March Mozart - Rondo Alla Turca
//https://www.linkedin.com/in/luis-basto-diaz-41136396/
//https://www.linkedin.com/in/victorlavalle/
//Error educacion Caso Cambranes
//Error ubicacion vacia
public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        
        
        Map<String, String> datos = new HashMap<String, String>() {{
            put("acronimoUniversidad", "UADY");
            put("nombreUniversidadEspaniol", "Universidad Autónoma de Yucatán");
            put("nombreUniversidadIngles", "University of Yucatan Autonomous");
            
            put("nombreCarreraEspaniol", "Ingeniería de Software");
            put("nombreCarreraIngles", "Software Engineer");
        }};
        
        

        WebDriver driver = new ChromeDriver(); 
        ControladorMaestro controler = new ControladorMaestro();
        
        
            driver.get("https://www.linkedin.com/login");
            Map<String, String> cookies = controler.leerCookiesDesdeArchivo("cookies.txt");
            controler.cargarCookiesInicioSesion(cookies, driver);
            driver.navigate().refresh();
            
        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.insercionIndirectaBuscadorURL("uady software fmat");
        driver.quit();
        //ExtraccionDatos scraper = new ExtraccionDatos();
        //scraper.MinadoUsuariosTotal(controler);
        


         
    } 
}

