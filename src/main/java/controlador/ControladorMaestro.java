package controlador;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public final class ControladorMaestro {

    @Setter private String correo;
    @Setter private String contrasenia;
    private Map<String, String> cookiesMap;
    @Getter Cache<Object, Object> cache;

    public ControladorMaestro() throws IOException, ParseException {
        Utilidades manejadorFechas = new Utilidades();

        Date fechaArchivo = manejadorFechas.leerFechaArchivo("fecha_actual.txt");
        Date fechaActualDate = manejadorFechas.obtenerFechaActual().getTime();

        if (manejadorFechas.haPasadoUnaSemana( fechaArchivo, fechaActualDate)) {
            System.out.println("Las cookies del archivo han expirado");
            WebDriver driver = new ChromeDriver();
            this.cargarPropiedades();
            this.iniciarSesion(driver);
            this.guardarCookiesInicioSesion(driver);
            manejadorFechas.escribirEnArchivoFechaActual();
            driver.quit();
            System.out.println("Actualizacion finalizada");
        } else {
            System.out.println("Las cookies del archivo estan vigentes");
        }
        cache = Caffeine.newBuilder().maximumSize(1000).build();
        Map<String, String> cookies = this.leerCookiesDesdeArchivo("cookies.txt");
        cache.put(1L, cookies);
    }

    private void cargarPropiedades() {
        Properties propiedades = new Properties();

        try (FileInputStream input = new FileInputStream("config.properties")) {
            propiedades.load(input);
            setCorreo(propiedades.getProperty("correo"));
            setContrasenia(propiedades.getProperty("contrasenia"));
        } catch (IOException e) {}
    }

    private void iniciarSesion(WebDriver driver) {
        driver.get("https://www.linkedin.com/login");

        WebElement inputUser = driver.findElement(By.id("username"));
        inputUser.sendKeys(this.correo);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys(this.contrasenia);

        inputPassword.sendKeys(Keys.ENTER);
    }

    private void guardarCookiesInicioSesion(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        this.cookiesMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            this.cookiesMap.put(cookie.getName(), cookie.getValue());
        }
        guardarCookiesEnArchivo("cookies.txt");

    }

    private void guardarCookiesEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Map.Entry<String, String> entry : cookiesMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Cookies guardadas en el archivo '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.err.println("Error al guardar las cookies: " + e.getMessage());
        }
    }

    public Map<String, String> leerCookiesDesdeArchivo(String nombreArchivo) {
        this.cookiesMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("=", 2);
                if (partes.length == 2) {
                    cookiesMap.put(partes[0], partes[1]);
                }
            }
            System.out.println("Cookies leídas desde el archivo '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.err.println("Error al leer las cookies desde el archivo: " + e.getMessage());
        }

        return cookiesMap;
    }

    protected void cargarCookiesInicioSesion(Map<String, String> cookiesMap, WebDriver driver) {
        for (Map.Entry<String, String> entry : cookiesMap.entrySet()) {
            Cookie cookie = new Cookie(entry.getKey(), entry.getValue());
            driver.manage().addCookie(cookie);
        }
    }

    public void inyectarCookies(WebDriver driver) {
        System.out.println("Agregando las cookies desde el cache");
        driver.get("https://www.linkedin.com/login");
        //Map<String, String> cookies = this.leerCookiesDesdeArchivo("cookies.txt");
        Map<String, String> cookies = (Map<String, String>) cache.getIfPresent(1L);
        this.cargarCookiesInicioSesion(cookies, driver);
        driver.navigate().refresh();

    }
}