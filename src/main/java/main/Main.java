package main;

import automata.GeneradorPorCadena;
import database.ArrayListQueue;


import iterador.IteradorPorURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.LinksUsuario;
import scrapper.MinadoDatos;
import scrapper.ObtenerContactosPivote;
import scrapper.ObtenerEducacion;
import scrapper.ObtenerExperiencia;
import scrapper.ObtenerNodos;

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
        LinksUsuario yy = new LinksUsuario(driver);
        ObtenerContactosPivote xx = new ObtenerContactosPivote(driver);
        //
        //https://www.linkedin.com/in/luis-basto-diaz-41136396/
        //https://www.linkedin.com/in/victorlavalle/
        //driver.get("https://www.linkedin.com/in/luis-basto-diaz-41136396/");
        //test.determinarTipoSecciones(String.valueOf(test.buscarIndiceSeccion("Experiencia")));
        //xp.determinarTipoSecciones();
        //xp.seccionExperienciaCasoSimple();
        //xp.seccionExperienciaCasoCompuesto();
        //System.out.println(yy.obtenerLinks());
        
        //test.seccionEducacion();
        metodoURL("https://www.linkedin.com/search/results/people/?geoUrn=%5B%22103256664%22%5D&keywords=fmat%20uady&network=%5B%22F%22%2C%22S%22%2C%22O%22%5D&origin=FACETED_SEARCH&sid=b00", driver);
        //colaLanzamiento.vaciarDatos();
        
        
        //xx.ActualizarPivotes();
       
        //test.seccionCabcecera();
        driver.quit();

    }
    
    public static void obtenerLinksporURL(String cadenaDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        String cadenaPreparada = GeneradorPorCadena.metodoURL(cadenaDeseada);
        iterador.iniciarIteracion(cadenaPreparada);
        driver.quit();
    }
    
    public static void obtenerLinksporBOTON(String cadenaDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        GeneradorPorCadena.metodoDirecto(cadenaDeseada, driver);
        iterador.iniciarIteracion(driver.getCurrentUrl());
        
        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {} 
        driver.quit();
    }
    
    public static void metodoURL(String urlDeseada, WebDriver driver){
        IteradorPorURL iterador = new IteradorPorURL(driver);
        
        urlDeseada = urlDeseada.replace("FACETED_SEARCH&", "FACETED_SEARCHL&page=XXXXX&");
        urlDeseada = urlDeseada.replace("SWITCH_SEARCH_VERTICAL&", "SWITCH_SEARCH_VERTICAL&page=XXXXX&");
        iterador.iniciarIteracion(urlDeseada);
        
        
        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {} 
        driver.quit();
    }
    
   
    
}

