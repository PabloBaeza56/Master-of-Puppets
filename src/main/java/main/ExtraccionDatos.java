package main;

import java.util.ArrayList;
import objetosConcretos.Educacion;
import objetosConcretos.datosBasicos;
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

    
    public ExtraccionDatos(){
        this.minador = new MinadoDatos();
        this.controladorEducacion = new ObtenerEducacion();
        this.controladorExperiencia= new ObtenerExperiencia();
        this.controladorCabecera = new ObtenerDatosCabecera();   
    }
    
    public void PerfilCompleto(String perfilDeseado){
        this.minador.irPagina(perfilDeseado);
        //datosBasicos info = this.controladorCabecera.seccionCabcecera();
        //System.out.println(info);
        
        //ArrayList<Educacion> resultado =this.controladorEducacion.seccionEducacion();
        //System.out.println("Datos Recuperados:");
        //System.out.println(resultado);
        
        this.controladorExperiencia.determinarTipoSecciones();
        //this.controladorExperiencia.seccionExperienciaCasoSimple();
        this.controladorExperiencia.seccionExperienciaCasoCompuesto();
 
    }
    
}
