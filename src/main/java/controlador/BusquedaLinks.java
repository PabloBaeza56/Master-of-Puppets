package controlador;

import automata.BuscarPorBarraBusqueda;
import automata.IteradorPorURL;
import database.InserccionDatos;
import java.util.ArrayList;
import modelo.LinkUsuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerContactosPivote;
import scrapper.Mineable;

public class BusquedaLinks {

    private final IteradorPorURL iterador;
    private final BuscarPorBarraBusqueda buscador;
    private final ObtenerContactosPivote pivoteador;
    private final WebDriver driver;
    private final InserccionDatos mongo;
    private final Mineable minable;

    public BusquedaLinks(WebDriver driver) {
        this.driver = driver;
        this.buscador = new BuscarPorBarraBusqueda(driver);
        this.iterador = new IteradorPorURL(driver);
        this.pivoteador = new ObtenerContactosPivote(driver);
        this.mongo = new InserccionDatos();
        this.minable = new Mineable(driver);
    }

    public void insercionIndirectaBuscadorURL(String cadenaDeseada) {
        String cadenaPreparada = this.buscador.metodoURL(cadenaDeseada);

        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(cadenaPreparada.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.minable.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }

    public void insercionDirectaBuscador(String cadenaDeseada) {
        String rutaObtenida = this.buscador.metodoDirecto(cadenaDeseada);
        rutaObtenida = rutaObtenida.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        rutaObtenida = rutaObtenida.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");

        while (!this.iterador.esUltimaPagina(this.driver)) {
            driver.get(rutaObtenida.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.minable.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }

    public void metodoURL(String urlDeseada) {
        urlDeseada = urlDeseada.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        urlDeseada = urlDeseada.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");

        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(urlDeseada.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = this.minable.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }
    }

    public void pivoteSimple(String urlDeseada) {
        this.pivoteador.AccederContactosPivotes(urlDeseada);
    }

    public void pivotesPropios_Conectados_() {
        this.pivoteador.ActualizarPivotes();
    }
}
