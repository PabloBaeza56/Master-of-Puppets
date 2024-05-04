package controlador;

import lombok.NoArgsConstructor;
import modelo.Usuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerEducacion;

import database.BusquedaDatos;
import database.InserccionDatos;
import java.util.List;
import modelo.LinkUsuario;
import org.openqa.selenium.chrome.ChromeDriver;
import automata.Automatron;
import scrapper.MandatoryElementException;
import scrapper.MandatorySectionException;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerExperiencia;


@NoArgsConstructor
public class ExtraccionDatos {
    
    
    public void MinadoUsuariosTotal(ControladorMaestro controler, Integer numeroLinksBuscar) {
        
        InserccionDatos db = new InserccionDatos();
        BusquedaDatos busqueda = new BusquedaDatos();
        db.eliminarDuplicadosPorUrlUsuario();
        List<LinkUsuario> documentos = busqueda.obtenerDocumentos(numeroLinksBuscar);
     
        for (LinkUsuario elemento : documentos) {
            
            WebDriver newDriver = new ChromeDriver();
            controler.inyectarCookies(newDriver);
 
            newDriver.get(elemento.getUrlUsuario());
            
            Usuario usuario;
            try {
                usuario = this.PerfilCompleto(newDriver);
                db.InsertarDocumento(usuario);
            } catch (MandatoryElementException | MandatorySectionException ex) {} finally {
                db.marcarDocumentoComoVisitado(elemento.get_id());
            }
            
            
            newDriver.close(); 
        }
    }
    
    private Usuario PerfilCompleto(WebDriver driver) throws MandatoryElementException, MandatorySectionException {
        Automatron movilizador = new Automatron(driver);
        movilizador.busquedaIndicesSeccionesMain();
 
        try {
            Usuario usuario = new Usuario.UsuarioBuilder()
                        .informacionPersonal(new ObtenerDatosCabecera(driver).reclamarDatos())
                        .experienciaLaboral(new ObtenerExperiencia(driver, movilizador).reclamarDatos())
                        .educacion(new ObtenerEducacion(driver, movilizador).reclamarDatos())
                        .build();
            return usuario;
        } catch (MandatoryElementException| MandatorySectionException e){
            return null;
        }

        
        
    }

}
