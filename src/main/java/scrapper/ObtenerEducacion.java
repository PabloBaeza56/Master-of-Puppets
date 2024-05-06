package scrapper;

import automata.Automatron;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Educacion;
import modelo.RelativeXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        if (movilizador.getIndicesSeccionesMain().get("Educacion") != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Educacion> reclamarDatos() throws MandatoryElementException {
        if (this.existeSeccion()) {
            return this.minarDatosEducacion();
        } else {
            return null;
        }
    }

    private ArrayList<Educacion> minarDatosEducacion() throws MandatoryElementException {

        Integer secciondeseada = movilizador.getIndicesSeccionesMain().get("Educacion");

        this.movilizador.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + secciondeseada + "]/div[3]/ul/li[");
        this.movilizador.setSubcadenaParte2("]/div/div[2]/div[1]/a");

        while (this.movilizador.existeSiguienteElemento()) {

            Educacion educacionPersona = new Educacion();
            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.getXpathActual()));

            super.settearMinadoObligatorio(elementoBase, new RelativeXpath("./div/div/div/div/span[1]"), "setCentroEducativo ", educacionPersona::setCentroEducativo);

            super.settearMinadoOpcional(elementoBase, new RelativeXpath("./span[1]/span[1]"), educacionPersona::setGradoAcademico);

            super.settearMinadoOpcional(elementoBase, new RelativeXpath("./span[2]/span[1]"), (String fecha) -> {

                String[] partesFecha = fecha.split("-");
                educacionPersona.setAnioIngreso(Educacion.convertirFechaAFechaLegiblePorLaBaseDeDatos(partesFecha[0].trim()));
                educacionPersona.setAnioEgreso(Educacion.convertirFechaAFechaLegiblePorLaBaseDeDatos(partesFecha[1].trim()));

            });

            listaEducacion.add(educacionPersona);
            this.movilizador.siguienteElemento();
        }
        this.movilizador.reiniciarIterador();
        return listaEducacion;
    }

}
