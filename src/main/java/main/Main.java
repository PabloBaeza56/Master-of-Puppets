package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//https://www.linkedin.com/in/luis-basto-diaz-41136396/
//https://www.linkedin.com/in/victorlavalle/

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        ControladorMaestro controler = new ControladorMaestro();

        /*
        Date fechaArchivo = controler.leerFechaArchivo("fecha_actual.txt");
        Calendar fechaActual = controler.obtenerFechaActual();
        
        if (controler.haPasadoUnaSemana(fechaArchivo, fechaActual)) {
            System.out.println("Ha pasado una semana desde la fecha del archivo.");
        } else {
            System.out.println("No ha pasado una semana desde la fecha del archivo.");
        }
        */
        
        
        
        
        String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/rodrigo-urtecho/", "https://www.linkedin.com/in/carlos-morales-reinisch-9b6687221/"};
        for (String elemento : arregloStrings) {
            
            WebDriver newDriver = new ChromeDriver();
            newDriver.get("https://www.linkedin.com/login");
            Map<String, String> cookies = controler.leerCookiesDesdeArchivo("cookies.txt");
            controler.cargarCookiesInicioSesion(cookies, newDriver);
            newDriver.navigate().refresh();
            

            
            Usuario user = new Usuario();
            ExtraccionDatos extractor = new ExtraccionDatos();
            newDriver.get(elemento);

            extractor.PerfilCompleto(newDriver, user);
            newDriver.close(); 
        }
         
    } 
}

