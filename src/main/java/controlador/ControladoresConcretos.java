package controlador;

import database.BusquedaDatos;
import database.InserccionDatos;
import database.QuerysMongoDB;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.LinkUsuario;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.MandatoryElementException;
import static scrapper.Waitable.esperaImplicita;
import vista.CambiarColeccion;
import vista.CambiarUsuario;
import vista.IngresarUsuario;
import vista.PantallaPrincipal;
import vista.QueryEmpresasTrabajadas;
import vista.QueryPromdioDuracion;
import vista.QueryPromdioNumeroEmpleos;
import vista.VentanaBuscadorCadena;
import vista.VentanaBuscadorContactosAmigos;
import vista.VentanaBuscadorManual;
import vista.VentanaBuscadorPorURL;
import vista.VentanaExtraccionMultiple;

public class ControladoresConcretos {

    private final Utilidades utilidades;
    private final QuerysMongoDB querysDB;
    private final ControladorMaestro controler;
    private final String coleccionSelecionada;

    public ControladoresConcretos(String coleccionSelecionada) throws IOException, ParseException {
        this.utilidades = new Utilidades();
        this.querysDB = new QuerysMongoDB();
        this.controler = new ControladorMaestro();
        this.coleccionSelecionada = coleccionSelecionada;
    }

    public static <T> void volverInicio(JFrame pantallaPrincipal, T objeto) {
        pantallaPrincipal.setVisible(true);
        if (objeto instanceof JFrame) {
            JFrame frame = (JFrame) objeto;
            frame.dispose();
        } else {
            System.err.println("El objeto no es una instancia de JFrame y no puede ser eliminado.");
        }
    }

    private String intentarInicioDeSesion(String UsuarioTextBox, String ContraseniaTextBox) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/login");

        WebElement inputUser = driver.findElement(By.id("username"));
        inputUser.sendKeys(UsuarioTextBox);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys(ContraseniaTextBox);

        inputPassword.sendKeys(Keys.ENTER);

        esperaImplicita(driver);

        String paginaActual = driver.getCurrentUrl();
        driver.quit();

        return paginaActual;
    }

    public void cambiarUsuarioLinkedin(String UsuarioTextBox, String ContraseniaTextBox, CambiarUsuario objeto) throws IOException, ParseException {
        String correoGuardado = this.utilidades.leerValorProperties("config.properties", "correo");
        String contraseniaGuardada = this.utilidades.leerValorProperties("config.properties", "contrasenia");

        if (correoGuardado.equals(UsuarioTextBox) && contraseniaGuardada.equals(ContraseniaTextBox)) {
            //System.out.println("No se tienen que actualizar las cookies"); 
            objeto.cambiarPanel();
        } else {
            String paginaActual = this.intentarInicioDeSesion(UsuarioTextBox, ContraseniaTextBox);

            if (paginaActual.equals("https://www.linkedin.com/feed/")) {
                //System.out.println("Inicio de sesion correcto");
                //System.out.println("Actualizando cookies sesion valida");
                this.utilidades.modificarArchivoProperties("correo", UsuarioTextBox);
                this.utilidades.modificarArchivoProperties("contrasenia", ContraseniaTextBox);
                this.utilidades.escribirEnArchivoFechaActualMas10Dias();

                objeto.cambiarPanel();
            } else {
                //System.out.println("Inicio de sesion FALLIDO");
                JOptionPane.showMessageDialog(objeto, "Error: Usuario y/o Contraseña incorrectos", "Error al iniciar sesión", JOptionPane.WARNING_MESSAGE);
                objeto.cambiarPanel();
            }
        }
    }

    public void IngresarUsuario(String UsuarioTextBox, String ContraseniaTextBox, IngresarUsuario objeto) throws IOException, ParseException {
        String paginaActual = this.intentarInicioDeSesion(UsuarioTextBox, ContraseniaTextBox);

        if (paginaActual.equals("https://www.linkedin.com/feed/")) {
            //System.out.println("Inicio de sesion correcto");

            String correoGuardado = this.utilidades.leerValorProperties("config.properties", "correo");
            String contraseniaGuardada = this.utilidades.leerValorProperties("config.properties", "contrasenia");

            if (!correoGuardado.equals(UsuarioTextBox) || !contraseniaGuardada.equals(ContraseniaTextBox)) {
                // Las cookies se deben de actualizar
                this.utilidades.modificarArchivoProperties("correo", UsuarioTextBox);
                this.utilidades.modificarArchivoProperties("contrasenia", ContraseniaTextBox);
                this.utilidades.escribirEnArchivoFechaActualMas10Dias();
            }

            objeto.dispose();
            CambiarColeccion cambiarColeccion = new vista.CambiarColeccion();
            cambiarColeccion.setVisible(true);
        } else {
            //System.out.println("Inicio de sesion FALLIDO");  
            JOptionPane.showMessageDialog(objeto, "Error: Usuario y/o Contraseña incorrectos", "Error al iniciar sesión", JOptionPane.WARNING_MESSAGE);
            //objeto.cambiarPanel();
        }
    }

    public void QueryEmpresasTrabajadas(Map<String, String> datos, QueryEmpresasTrabajadas objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            Map<String, Integer> resultado = this.querysDB.obtenerEmpresasTrabajadas(datos);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Empresa");
            model.addColumn("Cantidad");

            for (Map.Entry<String, Integer> entry : resultado.entrySet()) {
                model.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }

            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(objeto, new JScrollPane(table), "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();

        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void QueryPromdioDuracion(Map<String, String> datos, QueryPromdioDuracion objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            double resultado = this.querysDB.obtenerPromedioDuracionEmpleo(datos);

            JOptionPane.showMessageDialog(objeto, resultado + "   Meses en Promedio", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void QueryPromdioNumeroEmpleos(Map<String, String> datos, QueryPromdioNumeroEmpleos objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            double resultado = this.querysDB.obtenerPuestosExperienciaPorUsuario(datos);

            JOptionPane.showMessageDialog(objeto, resultado + "   Empleos en Promedio", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void VentanaBuscadorCadena(String CadenaBusqueda, VentanaBuscadorCadena objeto) {

        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            WebDriver driver = new ChromeDriver();
            this.controler.inyectarCookies(driver);

            BusquedaLinks buscador = new BusquedaLinks(driver);
            buscador.insercionIndirectaBuscadorURL(CadenaBusqueda);
            driver.close();
            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void AccionActualizarPivotes(PantallaPrincipal objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            WebDriver driverA = new ChromeDriver();
            this.controler.inyectarCookies(driverA);
            BusquedaLinks buscadorA = new BusquedaLinks(driverA);
            buscadorA.pivotesPropios_Conectados_();
            driverA.close();
            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);

        }
    }

    public void VentanaBuscadorContactosAmigos(String CadenaBusqueda, VentanaBuscadorContactosAmigos objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            BusquedaDatos db = new BusquedaDatos();
            String URLasociadoNombre = db.buscarUrlAsociadoConNombrePivote(CadenaBusqueda);

            WebDriver driverB = new ChromeDriver();
            this.controler.inyectarCookies(driverB);
            BusquedaLinks buscadorB = new BusquedaLinks(driverB);
            buscadorB.pivoteSimple(URLasociadoNombre);
            driverB.close();
            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void VentanaBuscadorManual(String CadenaBusqueda, VentanaBuscadorManual objeto) {

        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            LinkUsuario user = new LinkUsuario();
            InserccionDatos db = new InserccionDatos();

            if (CadenaBusqueda.startsWith("https://www.linkedin.com/in/")) {
                user.setUrlUsuario(CadenaBusqueda);
                db.InsertarDocumento(user);
            }
            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void VentanaBuscadorPorURL(String CadenaBusqueda, VentanaBuscadorPorURL objeto) {
        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            WebDriver driver = new ChromeDriver();
            this.controler.inyectarCookies(driver);

            BusquedaLinks buscador = new BusquedaLinks(driver);
            buscador.metodoURL(CadenaBusqueda);
            driver.quit();

            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void VentanaExtraccionMultiple(int elementosObtener, VentanaExtraccionMultiple objeto) throws MandatoryElementException {

        int opcion = JOptionPane.showConfirmDialog(objeto, "¿Estas seguro de continuar?, el proceso no se puede parar...", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {

            ExtraccionDatos extractor = new ExtraccionDatos();
            extractor.MinadoUsuariosTotal(this.controler, elementosObtener, this.coleccionSelecionada);

            JOptionPane.showMessageDialog(objeto, "La operación ha sido completada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            objeto.dispose();
        } else {
            JOptionPane.showMessageDialog(objeto, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

}
