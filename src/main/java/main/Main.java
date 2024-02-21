package main;

import automata.GeneradorPorCadena;
import database.ArrayListQueue;
import iterador.IteradorPorURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.MinadoDatos;
import scrapper.ObtenerEducacion;
import scrapper.ObtenerExperiencia;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        InicioSesion usuario = new InicioSesion(driver);
        ArrayListQueue colaLanzamiento = new ArrayListQueue();
        usuario.IniciarSesion();
        
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {} 
        
        ObtenerExperiencia xp = new ObtenerExperiencia(driver);
        ObtenerEducacion test = new ObtenerEducacion(driver);
        MinadoDatos prueba = new MinadoDatos(driver);
        //
        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        driver.get("https://www.linkedin.com/in/ecambranes/");
        //test.determinarTipoSecciones(String.valueOf(test.buscarIndiceSeccion("Experiencia")));
        xp.determinarTipoSecciones();
        xp.seccionExperienciaCasoSimple();
        xp.seccionExperienciaCasoCompuesto();
        //test.seccionEducacion();
        //obtenerLinksporURL("fmat uady", driver);
        //colaLanzamiento.vaciarDatos();

        //test.seccionCabcecera();
        driver.quit();

    }
    
    public static void obtenerLinksporURL(String cadenaDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        String cadenaPreparada = GeneradorPorCadena.metodoURL(cadenaDeseada);
        iterador.iniciarIteracion(cadenaPreparada);
        driver.quit();
    }
    
   
    
}

