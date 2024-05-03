package controlador;

import database.BusquedaDatos;
import database.InserccionDatos;
import database.QuerysMongoDB;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vista.CambiarUsuario;
import vista.IngresarUsuario;
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

    public ControladoresConcretos() throws IOException, ParseException {
        utilidades = new Utilidades();
        querysDB = new QuerysMongoDB();
        controler = new ControladorMaestro();
    }

    public static <T> void volverInicio(JFrame pantallaPrincipal, T objeto) {
        pantallaPrincipal.setVisible(true); // Mostrar la pantalla principal
        if (objeto instanceof JFrame) { // Verificar si el objeto es una instancia de JFrame
            JFrame frame = (JFrame) objeto; // Realizar un casting del objeto a JFrame
            frame.dispose(); // Eliminar el objeto JFrame
        } else {
            // Si el objeto no es una instancia de JFrame, imprimir un mensaje de error
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

        this.esperarFinCargaPagina(driver);

        String paginaActual = driver.getCurrentUrl();
        driver.quit();

        return paginaActual;
    }

    private void esperarFinCargaPagina(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    public void cambiarUsuarioLinkedin(String UsuarioTextBox, String ContraseniaTextBox, CambiarUsuario objeto) {
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

    public void IngresarUsuario(String UsuarioTextBox, String ContraseniaTextBox, IngresarUsuario objeto) {
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

            objeto.cambiarPanel();
        } else {
            //System.out.println("Inicio de sesion FALLIDO");  
            JOptionPane.showMessageDialog(objeto, "Error: Usuario y/o Contraseña incorrectos", "Error al iniciar sesión", JOptionPane.WARNING_MESSAGE);
            objeto.cambiarPanel();
        }
    }

    public void QueryEmpresasTrabajadas(Map<String, String> datos, QueryEmpresasTrabajadas objeto) {
        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

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
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void QueryPromdioDuracion(Map<String, String> datos, QueryPromdioDuracion objeto) {
        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        double resultado = this.querysDB.obtenerPromedioDuracionEmpleo(datos);

        JOptionPane.showMessageDialog(objeto, resultado + "   Meses en Promedio", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);

        objeto.dispose();
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void QueryPromdioNumeroEmpleos(Map<String, String> datos, QueryPromdioNumeroEmpleos objeto) {
        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        double resultado = this.querysDB.obtenerPuestosExperienciaPorUsuario(datos);

        JOptionPane.showMessageDialog(objeto, resultado + "   Empleos en Promedio", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);

        objeto.dispose();
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void VentanaBuscadorCadena(String CadenaBusqueda, VentanaBuscadorCadena objeto) {

        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        WebDriver driver = new ChromeDriver();
        this.controler.inyectarCookies(driver);

        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.insercionIndirectaBuscadorURL(CadenaBusqueda);
        driver.quit();

        objeto.dispose();
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void VentanaBuscadorContactosAmigos(String CadenaBusqueda, VentanaBuscadorContactosAmigos objeto) {
        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        BusquedaDatos db = new BusquedaDatos();
        String URLasociadoNombre = db.buscarUrlAsociadoConNombrePivote(CadenaBusqueda);

        WebDriver driver = new ChromeDriver();
        this.controler.inyectarCookies(driver);

        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.pivoteSimple(URLasociadoNombre);
        driver.quit();

        InserccionDatos dbx = new InserccionDatos();
        dbx.eliminarDuplicadosPorUrlUsuario();

        objeto.dispose();

        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void VentanaBuscadorManual(String CadenaBusqueda, VentanaBuscadorManual objeto) {

        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        LinkUsuario user = new LinkUsuario();
        InserccionDatos db = new InserccionDatos();

        if (CadenaBusqueda.startsWith("https://www.linkedin.com/in/")) {
            user.setVisitado(Boolean.FALSE);
            user.setUrlUsuario(CadenaBusqueda);
            db.InsertarDocumento(user);
        }

        objeto.dispose();
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void VentanaBuscadorPorURL(String CadenaBusqueda, VentanaBuscadorPorURL objeto) {
        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        WebDriver driver = new ChromeDriver();
        this.controler.inyectarCookies(driver);

        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.metodoURL(CadenaBusqueda);
        driver.quit();

        objeto.dispose();

        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void VentanaExtraccionMultiple(int elementosObtener, VentanaExtraccionMultiple objeto) {

        JOptionPane.showMessageDialog(objeto, "Por favor, espere a que finalice el proceso...", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        ExtraccionDatos extractor = new ExtraccionDatos();
        extractor.MinadoUsuariosTotal(this.controler, elementosObtener);

        objeto.dispose();
        JOptionPane.showMessageDialog(objeto, "Proceso finalizado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

}
