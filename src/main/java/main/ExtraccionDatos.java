package main;

import java.util.ArrayList;
import lombok.NoArgsConstructor;
import objetosConcretos.Educacion;
import objetosConcretos.Usuario;
import org.openqa.selenium.WebDriver;
import scrapper.ObtenerEducacion;
import automata.AutomataDatos;

@NoArgsConstructor
public class ExtraccionDatos {

    public void PerfilCompleto(WebDriver driver, Usuario user) {
        AutomataDatos movilizador = new AutomataDatos(driver);
        movilizador.busquedaIndicesSeccionesMain();

        ObtenerEducacion controladorEducacion = new ObtenerEducacion(driver, movilizador);
        //ObtenerDatosCabecera controladorCabecera = new ObtenerDatosCabecera(driver);

        //datosBasicos datosCabecera = controladorCabecera.seccionCabcecera();
        //user.setInformacionPersonal(datosCabecera);
        ArrayList<Educacion> resultadoEducacion = (ArrayList<Educacion>) controladorEducacion.seccionEducacion();
        user.setEducacion(resultadoEducacion);

        System.out.println(user);

    }

}
