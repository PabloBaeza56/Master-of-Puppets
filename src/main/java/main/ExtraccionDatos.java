package main;


import java.util.ArrayList;
import lombok.NoArgsConstructor;
import objetosConcretos.Educacion;
import objetosConcretos.Usuario;
import objetosConcretos.datosBasicos;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerEducacion;


@NoArgsConstructor
public class ExtraccionDatos {

  
    public void PerfilCompleto(WebDriver driver, Usuario user){
        ObtenerEducacion controladorEducacion = new ObtenerEducacion(driver);
        ObtenerDatosCabecera controladorCabecera = new ObtenerDatosCabecera(driver);
        
        datosBasicos datosCabecera = controladorCabecera.seccionCabcecera();
        user.setInformacionPersonal(datosCabecera);
        
        ArrayList<Educacion> resultadoEducacion = (ArrayList<Educacion>) controladorEducacion.seccionEducacion();
        user.setEducacion(resultadoEducacion);
        
        System.out.println(user);
  
    }
    
}
