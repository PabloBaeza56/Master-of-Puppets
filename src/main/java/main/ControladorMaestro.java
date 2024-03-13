package main;

import java.io.FileInputStream;
import java.io.IOException;
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

public class ControladorMaestro {
    @Setter private String correo;
    @Setter private String contrasenia;
    private final WebDriver driver;
    Map<String, String> cookiesMap;

    public ControladorMaestro() {
        this.driver = new ChromeDriver();  
        this.cargarPropiedades();
        this.iniciarSesion(this.driver);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {}
        this.guardarCookiesInicioSesion();
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
    
    private void guardarCookiesInicioSesion(){
        Set<Cookie> cookies = driver.manage().getCookies();
        this.cookiesMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            this.cookiesMap.put(cookie.getName(), cookie.getValue());
        }
        this.driver.quit();
    }
    
    protected void cargarCookiesInicioSesion(WebDriver newDriver){
        newDriver.get("https://www.linkedin.com/login");
        for (Map.Entry<String, String> entry : this.cookiesMap.entrySet()) {
            newDriver.manage().addCookie(new Cookie(entry.getKey(), entry.getValue()));
        }
    }

    

    
   

  
}
