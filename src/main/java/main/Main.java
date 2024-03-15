package main;

import automata.BuscarPorBarraBusqueda;
import database.MongoDBConnection;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosConcretos.LinkUsuario;
import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//Turkish March Mozart - Rondo Alla Turca
//https://www.linkedin.com/in/luis-basto-diaz-41136396/
//https://www.linkedin.com/in/victorlavalle/

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        
        /*
        Map<String, String> datos = new HashMap<String, String>() {{
            put("acronimoUniversidad", "UADY");
            put("nombreUniversidadEspaniol", "Universidad Autónoma de Yucatán");
            put("nombreUniversidadIngles", "University of Yucatan Autonomous");
            
            put("nombreCarreraEspaniol", "Ingeniería de Software");
            put("nombreCarreraIngles", "Software Engineer");
        }};
*/
        
         WebDriver driver = new ChromeDriver();
        try {
            ControladorMaestro controler = new ControladorMaestro();
            controler.inyectarCookies( driver );
        } catch (IOException | ParseException ex) {
            Logger.getLogger(VentanaBuscadorCadena.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.pivotesPropios_Conectados_();
        driver.quit();
       
       //
            //MongoDBConnection db = MongoDBConnection.getInstance();
            
       //db.eliminarDuplicadosPorUrlUsuario();

        /*
        
        WebDriver driver = new ChromeDriver(); 
        
        controler.inyectarCookies( driver );
            
        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.insercionIndirectaBuscadorURL("uady software fmat");
        driver.quit();
*/
        //ExtraccionDatos scraper = new ExtraccionDatos();
        //scraper.MinadoUsuariosTotal(controler);
        


         
    } 
}

