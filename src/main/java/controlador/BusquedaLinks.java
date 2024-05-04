package controlador;

import automata.BuscarPorBarraBusqueda;
import automata.IteradorPorURL;
import database.InserccionDatos;
import java.util.ArrayList;
import java.util.List;
import modelo.LinkUsuario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import scrapper.Mineable;
import static scrapper.Mineable.obtenerElementosNoDuplicados;
import scrapper.ObtenerContactosPivote;

public class BusquedaLinks {
    
    private final IteradorPorURL iterador;
    private final BuscarPorBarraBusqueda buscador;
    private final ObtenerContactosPivote pivoteador;
    private final Mineable minador ;
    private final WebDriver driver;
    private final InserccionDatos mongo;
    
    public BusquedaLinks(WebDriver driver){
        this.driver = driver;
        this.buscador = new BuscarPorBarraBusqueda(driver);
        this.iterador = new IteradorPorURL(driver);
        this.pivoteador = new ObtenerContactosPivote(driver);
        this.minador =  new Mineable(driver);
        this.mongo = new InserccionDatos();
    }
    
    
     public ArrayList<LinkUsuario> obtenerLinksUsuariosLinkedIn() {
        
        List<WebElement> enlaces = driver.findElements(By.tagName("a"));

        ArrayList<LinkUsuario> elementosValidos = new ArrayList<>();

        for (WebElement enlace : enlaces) {
            String url = enlace.getAttribute("href");
            if (url != null && url.contains("https://www.linkedin.com/in/")) {
                char primerChar = url.charAt(28);

                if (Character.isLowerCase(primerChar)) {
                    LinkUsuario user = new LinkUsuario();
                    user.setVisitado(Boolean.FALSE);
                    user.setUrlUsuario(url);
                    elementosValidos.add(user);
                }
            }
        }

        ArrayList<LinkUsuario> arregloFinal = obtenerElementosNoDuplicados(elementosValidos);

        return arregloFinal;
    }
    
    public void insercionIndirectaBuscadorURL(String cadenaDeseada){
        String cadenaPreparada = this.buscador.metodoURL(cadenaDeseada);

        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(cadenaPreparada.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }
    
    public void insercionDirectaBuscador(String cadenaDeseada){
        String rutaObtenida = this.buscador.metodoDirecto(cadenaDeseada);
        rutaObtenida = rutaObtenida.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        rutaObtenida = rutaObtenida.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        
       while (!this.iterador.esUltimaPagina(this.driver)) {
            driver.get(rutaObtenida.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }
    
    public void metodoURL(String urlDeseada){
        urlDeseada = urlDeseada.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        urlDeseada = urlDeseada.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        
        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(urlDeseada.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }
    
    public void pivoteSimple(String urlDeseada){
        this.pivoteador.AccederContactosPivotes(urlDeseada);
    }
    
    public void pivotesPropios_Conectados_(){
        this.pivoteador.ActualizarPivotes();
    }  
}
