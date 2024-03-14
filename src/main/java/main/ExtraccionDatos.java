package main;

import lombok.NoArgsConstructor;
import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerEducacion;
import automata.AutomataDatos;
import scrapper.ObtenerDatosCabecera;
import scrapper.ObtenerExperiencia;

@NoArgsConstructor
public class ExtraccionDatos {
    
    public Usuario PerfilCompleto(WebDriver driver) {
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
