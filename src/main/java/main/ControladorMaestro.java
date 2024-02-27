package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ControladorMaestro {
    private String correo;
    private String contrasenia;
    private final WebDriver driver;

    private ControladorMaestro() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        this.driver = new ChromeDriver(chromeOptions);
        
        cargarPropiedades();
    }
    
    private void cargarPropiedades() {
        Properties propiedades = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            propiedades.load(input);
            setCorreo(propiedades.getProperty("correo"));
            setContrasenia(propiedades.getProperty("contrasenia"));
        } catch (IOException e) {}
        this.iniciarSesion();
    }

    private void iniciarSesion() {
        this.driver.get("https://www.linkedin.com/login");
        
        WebElement inputUser =  this.driver.findElement(By.id("username"));
        inputUser.sendKeys(this.correo);

        WebElement inputPassword =  this.driver.findElement(By.id("password"));
        inputPassword.sendKeys(this.contrasenia);

        inputPassword.sendKeys(Keys.ENTER);
    }

    private void setCorreo(String correo) {
        this.correo = correo;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public WebDriver getDriver() {
        return  this.driver;
    }
    
    public static ControladorMaestro getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    private static class NewSingletonHolder {
        private static final ControladorMaestro INSTANCE = new ControladorMaestro();
    }
}
