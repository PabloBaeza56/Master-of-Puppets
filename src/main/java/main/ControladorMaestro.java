package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import lombok.Setter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ControladorMaestro {
    @Setter private String correo;
    @Setter private String contrasenia;
    

    public ControladorMaestro(WebDriver driver) {
       
        
        cargarPropiedades(driver);
    }
    
    private void cargarPropiedades(WebDriver driver) {
        Properties propiedades = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            propiedades.load(input);
            setCorreo(propiedades.getProperty("correo"));
            setContrasenia(propiedades.getProperty("contrasenia"));
        } catch (IOException e) {}
        this.iniciarSesion(driver);
    }

    private void iniciarSesion(WebDriver driver) {
        driver.get("https://www.linkedin.com/login");
        
        WebElement inputUser =  driver.findElement(By.id("username"));
        inputUser.sendKeys(this.correo);

        WebElement inputPassword =  driver.findElement(By.id("password"));
        inputPassword.sendKeys(this.contrasenia);

        inputPassword.sendKeys(Keys.ENTER);
    }

    

    
   

  
}
