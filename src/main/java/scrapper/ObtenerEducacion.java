package scrapper;

import automata.Automatron;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Educacion;
import modelo.CssSelector;
import modelo.fechasEducacion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static scrapper.Waitable.esperaImplicita;

public class ObtenerEducacion extends Mineable implements ScrapeableProduct {

    private final Automatron movilizador;
    private final ArrayList<Educacion> listaEducacion;

    public ObtenerEducacion(WebDriver driver, Automatron movilizador) {
        super(driver);
        this.movilizador = movilizador;
        this.listaEducacion = new ArrayList<>();
        
        
    }

    @Override
    public Boolean existeSeccion() {
        if (movilizador.getIndicesSeccionesMain().get("Educación") != null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public ArrayList<Educacion> reclamarDatos() throws MandatoryElementException {
        esperaImplicita(this. driver);

        Integer secciondeseada = movilizador.getIndicesSeccionesMain().get("Educación");

        this.movilizador.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + secciondeseada + "]/div[3]/ul/li[");
        this.movilizador.setSubcadenaParte2("]/div/div[2]/div[1]/a");

        while (this.movilizador.existeSiguienteElemento()) {

            Educacion educacionPersona = new Educacion();
            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.getXpathActual()));

            super.settearMinadoObligatorio(elementoBase, new CssSelector("./div/div/div/div/span[1]"), "setCentroEducativo ", educacionPersona::setCentroEducativo);

            super.settearMinadoOpcional(elementoBase, new CssSelector("./span[1]/span[1]"), educacionPersona::setGradoAcademico);

            

            
                super.settearMinadoOpcional(elementoBase, new CssSelector("./span[2]/span[1]"), (String fecha) -> {
                    fechasEducacion fechaFormateada = new fechasEducacion(fecha);
                    educacionPersona.setDuracion(fechaFormateada);

                });
            

            listaEducacion.add(educacionPersona);
            this.movilizador.siguienteElemento();
        }
        this.movilizador.reiniciarIterador();
        return this.listaEducacion;
    }

    public static void main(String[] args) throws IOException, ParseException {

        Testeable test = new Testeable();
        String url = "https://www.linkedin.com/in/lizeth-susana-vel%C3%A1zquez-lemus-3764542b4/";
        test.fastTest(url, driver -> {

            Automatron movilizador = new Automatron(driver);
            movilizador.busquedaIndicesSeccionesMain();
            System.out.println(movilizador.getIndicesSeccionesMain());

            ObtenerEducacion xp = new ObtenerEducacion(driver, movilizador);
            try {
                System.out.println(xp.reclamarDatos());
            } catch (MandatoryElementException ex) {
                Logger.getLogger(ObtenerEducacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
