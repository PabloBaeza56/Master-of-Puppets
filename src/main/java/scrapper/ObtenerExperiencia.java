package scrapper;

import automata.IteradorElementoTablaWeb;
import controlador.ExtraccionDatos;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Experiencia;
import modelo.fechasExperiencia;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CssSelector;
import static scrapper.Waitable.esperaImplicita;

public final class ObtenerExperiencia extends Mineable implements ScrapeableProduct {

    private Integer seccionDeseada;
    private final ArrayList<Integer> indicesCasoSimple;
    private final ArrayList<Integer> indicesCasoCompuesto;
    private final HashMap<String, Integer> indiceSeccion;
    private final ArrayList<Experiencia> elementosExperiencia;
    private final IteradorElementoTablaWeb movilizador;

    public ObtenerExperiencia(WebDriver driver, HashMap<String, Integer> indiceSeccion) {
        super(driver);
        this.indiceSeccion = indiceSeccion;
        this.indicesCasoSimple = new ArrayList<>();
        this.indicesCasoCompuesto = new ArrayList<>();
        this.elementosExperiencia = new ArrayList<>();
        this.movilizador = new IteradorElementoTablaWeb(driver);

    }

    @Override
    public Boolean existeSeccion() {
        if (this.indiceSeccion.get("Experiencia") != null) {
            this.seccionDeseada = this.indiceSeccion.get("Experiencia");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Experiencia> reclamarDatos() throws MandatoryElementException {
        esperaImplicita(this.driver);

        this.determinarTipoElementosExperiencia();
        this.seccionExperienciaCasoSimple();
        this.seccionExperienciaCasoCompuesto();
        return this.elementosExperiencia;

    }

    private void determinarTipoElementosExperiencia() {

        
        this.movilizador.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[");
        this.movilizador.setSubcadenaParte2("]/div/div[2]");
        
        while (this.movilizador.existeSiguienteElemento()) {
            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.getXpathActual()));
            try {
                WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                puestoElement.getText();
                this.indicesCasoCompuesto.add(this.movilizador.getIndiceFilatabla());
            } catch (NoSuchElementException e) {
                this.indicesCasoSimple.add(this.movilizador.getIndiceFilatabla());
            }

            this.movilizador.siguienteElemento();
        }
        this.movilizador.reiniciarIterador();

    }

    private void seccionExperienciaCasoSimple() throws MandatoryElementException {

        for (int i : this.indicesCasoSimple) {

            Experiencia elementoExperiencia = new Experiencia();

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

            super.settearMinadoObligatorio(elementoBase, new CssSelector(".//div[@class='display-flex flex-wrap align-items-center full-height']"),
                    "PuestoEmpleado Usuario (Experiencia)",
                    elementoExperiencia::setPuestoEmpleado);

            super.settearMinadoObligatorio(elementoBase, new CssSelector(".//span[@class='t-14 t-normal']"),
                    "NombreEmpresa Usuario (Experiencia)",
                    dato -> {
                        String primeraSubcadena = dato.split("·")[0].trim();
                        elementoExperiencia.setNombreEmpresa(primeraSubcadena);
                    });

            super.settearMinadoObligatorio(elementoBase, new CssSelector("./div[1]/div/span[2]/span[1]"),
                    "Fechas A",
                    cadenaFecha -> {
                        fechasExperiencia fechaFormateada = new fechasExperiencia(cadenaFecha);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });

            super.settearMinadoOpcional(elementoBase, new CssSelector(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);

            this.elementosExperiencia.add(elementoExperiencia);

        }
    }

    private void seccionExperienciaCasoCompuesto() throws MandatoryElementException {

        for (int i : this.indicesCasoCompuesto) {

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));

            String NombreEmpresa = super.minarTextoObligatorio(elementoBase, new CssSelector(".//div[@class='display-flex flex-wrap align-items-center full-height']"), "Nombre Empresa");

            this.movilizador.setSubcadenaParte1("./div[2]/ul/li[");
            this.movilizador.setSubcadenaParte2("]/div/div[2]/div/a");
            while (this.movilizador.existeSiguienteElemento(elementoBase)) {
                Experiencia elementoExperiencia = new Experiencia();

                WebElement elementoConcreto = elementoBase.findElement(By.xpath(this.movilizador.getXpathActual()));

                elementoExperiencia.setNombreEmpresa(NombreEmpresa);

                super.settearMinadoObligatorio(elementoConcreto, new CssSelector(".//div[@class='display-flex flex-wrap align-items-center full-height']"), "Puesto Empleo Usuario", elementoExperiencia::setPuestoEmpleado);

                try {
                    super.settearMinadoObligatorio(elementoConcreto, new CssSelector("./span[1]/span[1]"), "Duracion Empleo Usuario", Duracion -> {

                        fechasExperiencia fechaFormateada = new fechasExperiencia(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });
                } catch (ArrayIndexOutOfBoundsException e) {
                    super.settearMinadoObligatorio(elementoConcreto, new CssSelector("./span[2]/span[2]"), "Duracion Empleo Usuario", Duracion -> {

                        fechasExperiencia fechaFormateada = new fechasExperiencia(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });
                }

                super.settearMinadoOpcional(elementoConcreto, new CssSelector(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);

                this.elementosExperiencia.add(elementoExperiencia);
                this.movilizador.siguienteElemento();
            }
            this.movilizador.reiniciarIterador();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {

        Testeable test = new Testeable();
        String url = "https://www.linkedin.com/in/ecambranes/";
        test.fastTest(url, driver -> {

            ExtraccionDatos movilizador = new ExtraccionDatos();
            movilizador.busquedaIndicesSeccionesMain(driver);

            ObtenerExperiencia xp = new ObtenerExperiencia(driver, movilizador.indicesSeccionesMain);
            try {
                System.out.println(xp.minarTemplate());
            } catch (MandatoryElementException ex) {
                Logger.getLogger(ObtenerEducacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotFoundFatalSectionException ex) {
                Logger.getLogger(ObtenerExperiencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
