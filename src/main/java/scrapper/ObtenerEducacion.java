package scrapper;

import automata.AutomataDatos;
import java.util.ArrayList;
import objetosConcretos.Educacion;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerEducacion extends MinadoDatos {
    private AutomataDatos movilizador;
    public ObtenerEducacion(WebDriver driver, AutomataDatos movilizador) {
        super(driver); 
        this.movilizador = movilizador;
    }

    public ArrayList<Educacion> seccionEducacion() {
        ArrayList<Educacion> listaEducacion = new ArrayList<>();
       
        super.esperarSegundos(2);
        //System.out.println(this.movilizador.getIndicesSecciones().get("Educación"));
    
        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.movilizador.getIndicesSecciones().get("Educación") + "]/div[3]/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]/div/div[2]/div[1]/a");
        
        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {

            Educacion educacionPersona = new Educacion();
            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));

            try {
                String Universidad = super.scrapyText(elementoBase, "xpath", "./div/div/div/div/span[1]");
                educacionPersona.setCentroEducativo(Universidad);
            } catch (NoSuchElementException e) {}

            try {
                String Carrera = super.scrapyText(elementoBase, "xpath", "./span[1]/span[1]");
                educacionPersona.setGradoAcademico(Carrera);
            } catch (NoSuchElementException e) {}

            try {
                String Fecha = super.scrapyText(elementoBase, "xpath", "./span[2]/span[1]");
                String[] partesFecha = Fecha.split("-");
                educacionPersona.setAnioIngreso(partesFecha[0]);
                educacionPersona.setAnioEgreso(partesFecha[1]);
            } catch (NoSuchElementException e) {}

            
            listaEducacion.add(educacionPersona);
            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
        return listaEducacion;
    }

}
