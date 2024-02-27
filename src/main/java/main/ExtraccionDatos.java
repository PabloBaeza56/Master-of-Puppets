package main;

import org.openqa.selenium.WebDriver;
import scrapper.MinadoDatos;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerEducacion;
import scrapper.ObtenerExperiencia;

public class ExtraccionDatos {

    private final ObtenerEducacion controladorEducacion;
    private final ObtenerDatosCabecera controladorCabecera;
    private final ObtenerExperiencia controladorExperiencia;
    private final MinadoDatos minador;
    WebDriver driver;
    
    public ExtraccionDatos(WebDriver driver){
        this.minador = new MinadoDatos();
        this.controladorEducacion = new ObtenerEducacion();
        this.controladorExperiencia= new ObtenerExperiencia();
        this.controladorCabecera = new ObtenerDatosCabecera();
        this.driver = driver;

        
    }
    
    public void PerfilCompleto(String perfilDeseado){
        this.driver.get(perfilDeseado);
        this.controladorCabecera.seccionCabcecera();
        this.controladorEducacion.seccionEducacion();
        this.controladorExperiencia.determinarTipoSecciones();
        this.controladorExperiencia.seccionExperienciaCasoSimple();
        this.controladorExperiencia.seccionExperienciaCasoCompuesto();
 
    }
    
}
