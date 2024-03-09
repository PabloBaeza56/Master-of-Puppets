package main;

import database.MongoDBConnection;
import java.time.Duration;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import objetosConcretos.Educacion;
import objetosConcretos.Experiencia;
import objetosConcretos.Usuario;
import objetosConcretos.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerEducacion;
import scrapper.ObtenerExperiencia;

@NoArgsConstructor

public class ExtraccionDatos {

  
    
    
  
    public void PerfilCompleto(WebDriver driver, Usuario user){
        ObtenerEducacion controladorEducacion = new ObtenerEducacion(driver);
        Duration duracion = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duracion); // Espera un máximo de 10 segundos

    

        
        // Extraer datos
        ArrayList<Educacion> resultadoEducacion = controladorEducacion.seccionEducacion();
        user.setEducacion(resultadoEducacion);
        
        // Mostrar resultados
        System.out.println(user);
  
        //MongoDBConnection db = new MongoDBConnection();
        //db.SubirUsuario(user);
    }
    
}
