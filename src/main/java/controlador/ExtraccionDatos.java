package controlador;

import modelo.Usuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerEducacion;
import database.BusquedaDatos;
import database.InserccionDatos;
import java.util.List;
import modelo.LinkUsuario;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Educacion;
import modelo.Experiencia;
import modelo.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import scrapper.MandatoryElementException;
import scrapper.NotFoundFatalSectionException;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerExperiencia;
import static scrapper.Waitable.esperaExplicita;


public class ExtraccionDatos {

    public HashMap<String, Integer> indicesSeccionesMain;

    public ExtraccionDatos() {

        this.indicesSeccionesMain = new HashMap<>();
    }

    public void busquedaIndicesSeccionesMain(WebDriver driver) {

        for (int i = 12; i >= 1; i--) {
            try {
                esperaExplicita(1);
                WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[1]"));
                String texto = sectionElement.getText();
                this.indicesSeccionesMain.put(texto, i);

            } catch (NoSuchElementException e) {}
        }
    }

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

            db.marcarDocumentoComoVisitado(elemento.get_id(), coleccionSelecionada);

            newDriver.close();
        }
    }

    private Usuario PerfilCompleto(WebDriver driver) {
        this.busquedaIndicesSeccionesMain(driver);

        try {
            Usuario usuario = new Usuario.UsuarioBuilder()
                    .informacionPersonal((datosBasicos) new ObtenerDatosCabecera(driver).minarTemplate())
                    .experienciaLaboral((ArrayList<Experiencia>) new ObtenerExperiencia(driver, this.indicesSeccionesMain).minarTemplate())
                    .educacion((ArrayList<Educacion>) new ObtenerEducacion(driver, this.indicesSeccionesMain).minarTemplate())
                    .build();
            return usuario;
        } catch (MandatoryElementException | NotFoundFatalSectionException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return null;
        }

    }

}
