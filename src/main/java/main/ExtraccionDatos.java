package main;

import database.MongoDBConnection;
import java.util.ArrayList;
import objetosConcretos.Educacion;
import objetosConcretos.Experiencia;
import objetosConcretos.Usuario;
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
        Usuario user = new Usuario();
        this.minador.irPagina(perfilDeseado);
        datosBasicos info = this.controladorCabecera.seccionCabcecera();
        user.setDatosBasicos(info);
        
        ArrayList<Educacion> resultadoEducacion =this.controladorEducacion.seccionEducacion();
        user.setEducacion(resultadoEducacion);
        
        this.controladorExperiencia.determinarTipoSecciones();
        
        ArrayList<Experiencia> resultadoSimple = this.controladorExperiencia.seccionExperienciaCasoSimple();
        ArrayList<Experiencia> resultadoCompuesto = this.controladorExperiencia.seccionExperienciaCasoCompuesto();
   
        ArrayList<Experiencia> arregloFinal = new ArrayList<>();
        arregloFinal.addAll(resultadoSimple);
        arregloFinal.addAll(resultadoCompuesto);
        user.setExperienciaLaboral(arregloFinal);
        
        System.out.println(user);
        MongoDBConnection db = new MongoDBConnection();
        db.SubirUsuario(user);
    }
    
}
