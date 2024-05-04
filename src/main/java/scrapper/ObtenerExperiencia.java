package scrapper;

import automata.Automatron;
import java.util.ArrayList;
import modelo.Experiencia;
import modelo.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.function.Consumer;
import modelo.RelativeXpath;

public final class ObtenerExperiencia extends Mineable implements ScrapeableProduct {

    private final Integer seccionDeseada;
    private final ArrayList<Integer> indicesCasoSimple;
    private final ArrayList<Integer> indicesCasoCompuesto;
    private final Automatron movilizador;
    private final ArrayList<Experiencia> elementosExperiencia;

    public ObtenerExperiencia(WebDriver driver, Automatron movilizador) {
        super(driver);
        this.movilizador = movilizador;
        this.indicesCasoSimple = new ArrayList<>();
        this.indicesCasoCompuesto = new ArrayList<>();
        this.elementosExperiencia = new ArrayList<>();
        this.seccionDeseada = this.movilizador.getIndicesSeccionesMain().get("Experiencia");
    }

    @Override
    public ArrayList<Experiencia> reclamarDatos() throws MandatorySectionException {
        this.determinarTipoSecciones();
        this.seccionExperienciaCasoSimple();

        this.seccionExperienciaCasoCompuesto();

        return this.elementosExperiencia;
    }

    private void determinarTipoSecciones() {

        String subcadenaParte1 = "/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[";
        String subcadenaParte2 = "]/div/div[2]";
        Consumer<WebElement> iterable = elementoBase -> {

            elementoBase = super.driver.findElement(By.xpath(this.movilizador.getXpathActual()));

            try {
                WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                puestoElement.getText();
                this.indicesCasoCompuesto.add(this.movilizador.getIndiceFilatabla());
            } catch (NoSuchElementException e) {
                this.indicesCasoSimple.add(this.movilizador.getIndiceFilatabla());
            }

        };
        movilizador.procesarElementos(this.seccionDeseada, subcadenaParte1, subcadenaParte2, iterable);

    }

    private void seccionExperienciaCasoSimple() {

        for (int i : this.indicesCasoSimple) {
            try {
                Experiencia elementoExperiencia = new Experiencia();

                WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

                super.settearMinadoObligatorio(elementoBase, new RelativeXpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"),
                        "PuestoEmpleado Usuario (Experiencia)",
                        elementoExperiencia::setPuestoEmpleado);

                super.settearMinadoObligatorio(elementoBase, new RelativeXpath(".//span[@class='t-14 t-normal']"),
                        "NombreEmpresa Usuario (Experiencia)",
                        dato -> {
                            String primeraSubcadena = dato.split("·")[0].trim();
                            elementoExperiencia.setNombreEmpresa(primeraSubcadena);
                        });

                /*
                super.settearMinadoObligatorio(elementoBase, ".//span[contains(@class, 't-14 t-normal t-black--light')][1]",
                        "Fechas Usuario(Experiencia)",
                        cadenaFecha -> {
                            Fechas fechaFormateada = new Fechas(cadenaFecha);
                            elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                        });
                */

                super.settearMinadoOpcional(elementoBase, new RelativeXpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);

                this.elementosExperiencia.add(elementoExperiencia);
            } catch (MandatoryElementException e) {
                break;
            }
        }
    }

    private void seccionExperienciaCasoCompuesto() {

        for (int i : this.indicesCasoCompuesto) {

            WebElement elementoCimiento = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));
            String NombreEmpresa = super.minarTextoOpcional(elementoCimiento, new RelativeXpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
            if (NombreEmpresa.equals("")) {
                break;
            }

            String subcadenaParte1 = "./div[2]/ul/li[";
            String subcadenaParte2 = "]/div/div[2]/div/a";
            Consumer<WebElement> iterable = elementoBase -> {
                try {

                    Experiencia elementoExperiencia = new Experiencia();

                    WebElement elementoConcreto = elementoBase.findElement(By.xpath(this.movilizador.getXpathActual()));

                    elementoExperiencia.setNombreEmpresa(NombreEmpresa);

                    super.settearMinadoObligatorio(elementoConcreto, new RelativeXpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"), "PuestoEmpleado Usuario (Xp Compuesta)", elementoExperiencia::setPuestoEmpleado);

                    /*
                    super.settearMinadoObligatorio(elementoConcreto, ".//span[contains(@class, 't-14 t-normal t-black--light')][1]", "Duracion Usuario Xp Compuesta", Duracion -> {
                        Fechas fechaFormateada = new Fechas(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });
                     */
                    super.settearMinadoOpcional(elementoConcreto, new RelativeXpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);
                    this.elementosExperiencia.add(elementoExperiencia);
                } catch (MandatoryElementException e) {
                }
            };
            movilizador.procesarElementos(this.seccionDeseada, subcadenaParte1, subcadenaParte2, iterable);

        }
    }

}
