package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InicioSesion {
    private String correo;
    private String contrasenia;
    private WebDriver driver;

    protected InicioSesion(WebDriver driver){
        Properties propiedades = new Properties();
    	try {
            propiedades.load(new FileReader("config.properties"));
        	 
            setCorreo(propiedades.getProperty("correo"));
            this.driver = driver;
            setContrasenia(propiedades.getProperty("contrasenia"));
 
        } catch (IOException e) {}
    }
    
    protected void IniciarSesion(){
        driver.get("https://www.linkedin.com/home");

        WebElement inputUser = driver.findElement(By.name("session_key"));
        inputUser.sendKeys(this.correo);

        WebElement inputPassword = driver.findElement(By.name("session_password"));
        inputPassword.sendKeys(this.contrasenia);

        inputPassword.sendKeys(Keys.ENTER);

    }
    
    private void setCorreo(String correo) {
        this.correo = correo;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
}
