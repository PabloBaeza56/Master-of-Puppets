package main;

import java.io.IOException;
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
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {System.out.println("Espera error main 2");} 


        Set<Cookie> cookies = driver.manage().getCookies();
        Map<String, String> cookiesMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookiesMap.put(cookie.getName(), cookie.getValue());
        }
        driver.quit();

         
        //-------------------------------------------------------------------------------
        String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/rodrigo-urtecho/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/"};

        for (String elemento : arregloStrings) {
            
            WebDriver newDriver = new ChromeDriver();
            newDriver.get("https://www.linkedin.com/login");
            for (Map.Entry<String, String> entry : cookiesMap.entrySet()) {
                newDriver.manage().addCookie(new Cookie(entry.getKey(), entry.getValue()));
            }
            
            Usuario user = new Usuario();
            ExtraccionDatos extractor2 = new ExtraccionDatos();
            System.out.println(elemento);
            newDriver.get(elemento);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {} 
            newDriver.navigate().refresh();
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {} 

            extractor2.PerfilCompleto(newDriver, user); 
            newDriver.close(); 
        }


            
      

        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        
        
        

    } 
}

