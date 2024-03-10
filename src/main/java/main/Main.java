package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import objetosConcretos.Usuario;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Main {

    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver(); 
        ControladorMaestro controler = new ControladorMaestro(driver);
        //-------------------------------------------------------------------------------
        try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {System.out.println("Espera error main 2");} 

        System.out.println("1");
        // Realizar inicio de sesión...
        

        Set<Cookie> cookies = driver.manage().getCookies();

        // Guardar las cookies en memoria
        Map<String, String> cookiesMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookiesMap.put(cookie.getName(), cookie.getValue());
        }
        System.out.println("2");
        // Cerrar el navegador
        driver.quit();

       System.out.println("3");
        
         
         //------------------------------------------------------------------------------
      
         
         String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/rodrigo-urtecho/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/"};
         System.out.println("4");
        // Iterar sobre el arreglo usando un bucle for
        for (String elemento : arregloStrings) {
            
            WebDriver newDriver = new ChromeDriver();
            newDriver.get("https://www.linkedin.com/login");
            for (Map.Entry<String, String> entry : cookiesMap.entrySet()) {
                newDriver.manage().addCookie(new Cookie(entry.getKey(), entry.getValue()));
            }
            
            
            
            
            
            Usuario user2 = new Usuario();
            ExtraccionDatos extractor2 = new ExtraccionDatos();
            System.out.println(elemento);
            newDriver.get(elemento); // Utiliza newDriver para navegar a la URL
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {System.out.println("Espera error main 2");} 
            newDriver.navigate().refresh();
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {System.out.println("Espera error main 2");} 

            extractor2.PerfilCompleto(newDriver, user2); // Utiliza n
            newDriver.close();
            
            
        }
     
         
         
             // Segundo perfil
   


            
      

        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        
        
        

    } 
}

