package main;

import lombok.NoArgsConstructor;
import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerEducacion;
import automata.AutomataDatos;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerExperiencia;

@NoArgsConstructor
public class ExtraccionDatos {
    
    
    public void MinadoUsuariosTotal(ControladorMaestro controler){
        String[] arregloStrings = {"https://www.linkedin.com/in/victorlavalle/" ,"https://www.linkedin.com/in/ecambranes/", "https://www.linkedin.com/in/manuelmartinrico/"};
        for (String elemento : arregloStrings) {
            
            WebDriver newDriver = new ChromeDriver();
            newDriver.get("https://www.linkedin.com/login");
            Map<String, String> cookies = controler.leerCookiesDesdeArchivo("cookies.txt");
            controler.cargarCookiesInicioSesion(cookies, newDriver);
            newDriver.navigate().refresh();
          
            
            ExtraccionDatos extractor = new ExtraccionDatos();
            
            newDriver.get(elemento);
            Usuario usuario = extractor.PerfilCompleto(newDriver);
            System.out.println(usuario);
            newDriver.close(); 
        }
    }
    
    private Usuario PerfilCompleto(WebDriver driver) {
        AutomataDatos movilizador = new AutomataDatos(driver);
        movilizador.busquedaIndicesSeccionesMain();
        
        ObtenerDatosCabecera obtenerCabecera = new ObtenerDatosCabecera(driver);
        ObtenerExperiencia obtenerExperiencia = new ObtenerExperiencia(driver, movilizador);
        ObtenerEducacion obtenerEducacion = new ObtenerEducacion(driver, movilizador);
        
        
        Usuario usuario = new Usuario.UsuarioBuilder()
                    .informacionPersonal(obtenerCabecera.seccionCabcecera())
                    .experienciaLaboral(obtenerExperiencia.seccionExperiencia())
                    .educacion(obtenerEducacion.seccionEducacion())
                    .build();
        
        return usuario;
    }

}
