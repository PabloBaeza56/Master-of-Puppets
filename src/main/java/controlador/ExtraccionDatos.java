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
import scrapper.NotFoundFatalSectionException;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerExperiencia;

@NoArgsConstructor
public class ExtraccionDatos {

    public void MinadoUsuariosTotal(ControladorMaestro controler, int numeroLinksBuscar, String coleccionSelecionada) {
     
        InserccionDatos db = new InserccionDatos();
        BusquedaDatos busqueda = new BusquedaDatos();
        db.eliminarDocumentosDuplicadosSinColecciones();
        List<LinkUsuario> documentos = busqueda.obtenerDocumentos(numeroLinksBuscar, coleccionSelecionada);

        for (LinkUsuario elemento : documentos) {

            WebDriver newDriver = new ChromeDriver();
            controler.inyectarCookies(newDriver);

            newDriver.get(elemento.getUrlUsuario());

            Usuario usuario = this.PerfilCompleto(newDriver);

            if (usuario != null) {
                db.InsertarDocumento(usuario, coleccionSelecionada);
            }

            db.marcarDocumentoComoVisitado(elemento.get_id(),coleccionSelecionada);

            newDriver.close();
        }
    }

    private Usuario PerfilCompleto(WebDriver driver) {
        Automatron movilizador = new Automatron(driver);
        movilizador.busquedaIndicesSeccionesMain();
     
        try {
            Usuario usuario = new Usuario.UsuarioBuilder()
                    .informacionPersonal(new ObtenerDatosCabecera(driver).reclamarDatos())
                    .experienciaLaboral(new ObtenerExperiencia(driver, movilizador).reclamarDatos())
                    .educacion(new ObtenerEducacion(driver, movilizador).reclamarDatos())
                    .build();
            return usuario;
        } catch (MandatoryElementException | NotFoundFatalSectionException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return null;
        }

    }

}
