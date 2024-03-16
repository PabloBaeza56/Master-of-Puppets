package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
    Map<String, String> cookiesMap;

    public ControladorMaestro() throws IOException, ParseException {
        
        Date fechaArchivo = this.leerFechaArchivo("fecha_actual.txt");
        Calendar fechaActual = this.obtenerFechaActual();
        
        if (this.haPasadoUnaSemana(fechaArchivo, fechaActual)) {
            System.out.println("Las cookies del archivo han expirado");
            WebDriver driver = new ChromeDriver(); 
            this.cargarPropiedades();
            this.iniciarSesion(driver);
            this.guardarCookiesInicioSesion(driver);
            this.escribirEnArchivoFechaActual();
            driver.quit();   
        } else {
            System.out.println("Las cookies del archivo estan vigentes");
        }
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
        
        WebElement inputUser =  driver.findElement(By.id("username"));
        inputUser.sendKeys(this.correo);

        WebElement inputPassword =  driver.findElement(By.id("password"));
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
     
    protected Map<String, String> leerCookiesDesdeArchivo(String nombreArchivo) {
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

    
    private void escribirEnArchivoFechaActual(){

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = formato.format(fechaActual);

        String nombreArchivo = "fecha_actual.txt";

        String rutaArchivo = System.getProperty("user.dir") + File.separator + nombreArchivo;

        try {
            try (FileWriter escritor = new FileWriter(rutaArchivo)) {
                escritor.write(fechaFormateada);
            }
            System.out.println("Se ha creado el archivo '" + nombreArchivo + "' correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        
    }
    
    protected Date leerFechaArchivo(String rutaArchivo) throws IOException, ParseException {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String fechaArchivoTexto = lector.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formato.parse(fechaArchivoTexto);
        }
    }
    
    protected Calendar obtenerFechaActual() {
        return Calendar.getInstance();
    }
    
    // Función para verificar si ha pasado una semana desde la fecha del archivo
    protected boolean haPasadoUnaSemana(Date fechaArchivo, Calendar fechaActual) {
        fechaActual.add(Calendar.DAY_OF_YEAR, -7); // Retroceder una semana
        return fechaActual.getTime().after(fechaArchivo);
    }
    

    public void inyectarCookies(WebDriver driver ){
         driver.get("https://www.linkedin.com/login");
            Map<String, String> cookies = this.leerCookiesDesdeArchivo("cookies.txt");
            this.cargarCookiesInicioSesion(cookies, driver);
            driver.navigate().refresh();
        
    }

    
   

  
}
