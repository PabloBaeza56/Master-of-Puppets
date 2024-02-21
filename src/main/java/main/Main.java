package main;

import automata.GeneradorPorCadena;
import database.ArrayListQueue;
import iterador.IteradorPorURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.MinadoDatos;
import scrapper.ObtenerEducacion;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        InicioSesion usuario = new InicioSesion(driver);
        ArrayListQueue colaLanzamiento = new ArrayListQueue();
        usuario.IniciarSesion();
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {} 
        
        
        ObtenerEducacion test = new ObtenerEducacion(driver);
        MinadoDatos prueba = new MinadoDatos(driver);
        //
        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        driver.get("https://www.linkedin.com/in/luis-basto-diaz-41136396/");
        //test.determinarTipoSecciones(String.valueOf(test.buscarIndiceSeccion("Experiencia")));
        //test.seccionExperienciaCasoSimple(String.valueOf(test.buscarIndiceSeccion("Experiencia")));
        //test.seccionExperienciaCasoCompuesto(String.valueOf(test.buscarIndiceSeccion("Experiencia")));
        //test.seccionEducacion();
        //obtenerLinksporURL("fmat uady", driver);
        //colaLanzamiento.vaciarDatos();
        System.out.println(prueba.buscarIndiceSeccion("Educación"));
        test.seccionEducacion();
        driver.quit();

    }
    
    public static void obtenerLinksporURL(String cadenaDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        String cadenaPreparada = GeneradorPorCadena.metodoURL(cadenaDeseada);
        iterador.iniciarIteracion(cadenaPreparada);
        driver.quit();
    }
    
   
    
}

