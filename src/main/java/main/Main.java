package main;

import automata.GeneradorPorCadena;
import iterador.IteradorPorURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.VerificarSecciones;
import static utilidades.Generales.esperarSegundos;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        InicioSesion usuario = new InicioSesion(driver);
        usuario.IniciarSesion();
        esperarSegundos(3);
        
        VerificarSecciones test = new VerificarSecciones(driver);
        
        driver.get("https://www.linkedin.com/in/luis-basto-diaz-41136396/");
        System.out.println(test.buscarIndiceSeccion("Educación"));
        test.buscarElementos(String.valueOf(test.buscarIndiceSeccion("Educación")));
        //obtenerLinksporURL("fmat uady", driver);
        driver.quit();
    }
    
    public static void obtenerLinksporURL(String cadenaDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        String cadenaPreparada = GeneradorPorCadena.metodoURL(cadenaDeseada);
        iterador.iniciarIteracion(cadenaPreparada);
        driver.quit();
    }
    
   
    
}

