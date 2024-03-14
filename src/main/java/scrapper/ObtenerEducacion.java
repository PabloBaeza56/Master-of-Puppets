package scrapper;

import automata.AutomataDatos;
import java.util.ArrayList;
import objetosConcretos.Educacion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerEducacion extends MinadoDatos {
    private AutomataDatos movilizador;
    
    public ObtenerEducacion(WebDriver driver, AutomataDatos movilizador) {
        super(driver); 
        this.movilizador = movilizador;
    }

    public ArrayList<Educacion> seccionEducacion() {
        super.esperarFinCargaPagina();
        ArrayList<Educacion> listaEducacion = new ArrayList<>();
        Integer secciondeseada = this.movilizador.getIndicesSeccionesMain().get("Educación");
        super.esperarSegundos(2);
    
        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + secciondeseada + "]/div[3]/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]/div/div[2]/div[1]/a");
        
        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {
            super.esperarFinCargaPagina();
            
            Educacion educacionPersona = new Educacion();
            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));
            
            super.extraerDato(elementoBase, "./div/div/div/div/span[1]", educacionPersona::setCentroEducativo);
            
            super.extraerDato(elementoBase, "./span[1]/span[1]", educacionPersona::setGradoAcademico);
            
            super.extraerDato(elementoBase, "./span[2]/span[1]", fecha -> {
                try{
                String[] partesFecha = fecha.split("-");
                educacionPersona.setAnioIngreso(partesFecha[0]);
                educacionPersona.setAnioEgreso(partesFecha[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    educacionPersona.setAnioIngreso("0");
                    educacionPersona.setAnioEgreso("0");
                }
            });

            
            listaEducacion.add(educacionPersona);
            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
        return listaEducacion;
    }

}
