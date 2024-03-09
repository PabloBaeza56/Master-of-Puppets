package main;

import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Main {

    public static void main(String[] args) {
        
         ControladorMaestro controlador = ControladorMaestro.getInstance();
         
         try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {} 
         
         
             // Segundo perfil
    Usuario user2 = new Usuario();
    ExtraccionDatos extractor2 = new ExtraccionDatos();
    WebDriver driver2 = controlador.getDriver(); // Nuevo WebDriver
    driver2.get("https://www.linkedin.com/in/imreyesjorge/");
    try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {} 
    extractor2.PerfilCompleto(driver2, user2);


    // Primer perfil
    Usuario user1 = new Usuario();
    ExtraccionDatos extractor1 = new ExtraccionDatos();
    WebDriver driver1 = controlador.getDriver();
    driver1.get("https://www.linkedin.com/in/rodrigo-urtecho/");
      try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {} 
    extractor1.PerfilCompleto(driver1, user1);

    // Primer perfil
    Usuario user3 = new Usuario();
    ExtraccionDatos extractor3 = new ExtraccionDatos();
    WebDriver driver3 = controlador.getDriver();
    driver3.get("https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/");
      try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {} 
    extractor3.PerfilCompleto(driver3, user3);

           
            
      

        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        
        
        

    } 
}

