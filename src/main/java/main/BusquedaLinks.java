package main;

import automata.BuscarPorBarraBusqueda;
import automata.IteradorPorURL;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerContactosPivote;
import scrapper.ObtenerNodos;

public class BusquedaLinks {
    
    private final IteradorPorURL iterador;
    private final BuscarPorBarraBusqueda buscador;
    private final ObtenerContactosPivote pivoteador;
    private final ObtenerNodos nodo;
    
    protected BusquedaLinks(WebDriver driver){
        this.buscador = new BuscarPorBarraBusqueda(driver);
        this.iterador = new IteradorPorURL();
        this.pivoteador = new ObtenerContactosPivote();
        this.nodo = new ObtenerNodos();
    }
    
    public void insercionIndirectaBuscadorURL(String cadenaDeseada){
        String cadenaPreparada = this.buscador.metodoURL(cadenaDeseada);
        this.iterador.iniciarIteracion(cadenaPreparada);
    }
    
    public void insercionDirectaBuscador(String cadenaDeseada){
        String rutaObtenida = this.buscador.metodoDirecto(cadenaDeseada);
        rutaObtenida = rutaObtenida.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        rutaObtenida = rutaObtenida.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        this.iterador.iniciarIteracion(rutaObtenida);
    }
    
    public void metodoURL(String urlDeseada){
        urlDeseada = urlDeseada.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        urlDeseada = urlDeseada.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        this.iterador.iniciarIteracion(urlDeseada);
    }
    
    public void pivoteSimple(String urlDeseada){
        this.pivoteador.AccederContactosPivotes(urlDeseada);
    }
    
    public void pivotesPropios_Conectados_(){
        this.pivoteador.ActualizarPivotes();
    }
    
    public void personasRelacionadas(){
        this.nodo.UsuariosRelacionadosA();
        this.nodo.UsuariosRelacionadosB();   
    }
    
    
    
}
