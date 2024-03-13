package scrapper;

import java.util.ArrayList;
import objetosConcretos.Experiencia;
import objetosConcretos.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import automata.AutomataDatos;

public final class ObtenerExperiencia extends MinadoDatos {

    private final Integer seccionDeseada;
    private final ArrayList<Integer> elementosCasoSimple;
    private final ArrayList<Integer> elementosCasoCompuesto;
    private final AutomataDatos movilizador;

    public ObtenerExperiencia(WebDriver driver, AutomataDatos movilizador) {
        super(driver);
        this.elementosCasoSimple = new ArrayList<>();
        this.elementosCasoCompuesto = new ArrayList<>();
        this.movilizador = movilizador;
        this.seccionDeseada = this.movilizador.getIndicesSecciones().get("Experiencia");
    }

    public void determinarTipoSecciones() {
        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]/div/div[2]");

        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {

            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));

            try {
                WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                puestoElement.getText();
                this.elementosCasoCompuesto.add(this.movilizador.iteradorTabla.getIndiceFilatabla());
            } catch (NoSuchElementException e) {
                this.elementosCasoSimple.add(this.movilizador.iteradorTabla.getIndiceFilatabla());
            }

            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
    }

    public ArrayList<Experiencia> seccionExperienciaCasoSimple() {
        ArrayList<Experiencia> elementosExperiencia = new ArrayList<>();

        for (int i : this.elementosCasoSimple) {
            Experiencia elementoExperiencia = new Experiencia();

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

            try {
                String Puesto = super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
                elementoExperiencia.setPuestoEmpleado(Puesto);
            } catch (NoSuchElementException e) {
            }

            try {
                String Empresa = super.scrapyText(elementoBase, "xpath", ".//span[@class='t-14 t-normal']");
                String[] partes = Empresa.split("·");
                String primeraSubcadena = partes[0].trim();
                elementoExperiencia.setNombreEmpresa(primeraSubcadena);
            } catch (NoSuchElementException e) {
            }

            try {
                String cadenaFecha = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]");
                Fechas fechaFormateada = new Fechas(cadenaFecha);
                elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
            } catch (NoSuchElementException e) {
            }

            try {
                String Ubicacion = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]");
                elementoExperiencia.setUbicacionEmpleado(Ubicacion);
            } catch (NoSuchElementException e) {
            }

            elementosExperiencia.add(elementoExperiencia);

        }
        return elementosExperiencia;
    }

    public ArrayList<Experiencia> seccionExperienciaCasoCompuesto() {
        ArrayList<Experiencia> elementosExperiencia = new ArrayList<>();

        for (int i : this.elementosCasoCompuesto) {

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
            
            String NombreEmpresa = "";
            try {
                NombreEmpresa = super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
            } catch (NoSuchElementException e) {}

            int contador = 1;
            while (true) {
                Experiencia elementoExperiencia = new Experiencia();
                try {

                    WebElement elementoConcreto = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + contador + "]/div/div[2]/div/a")); // /a

                    elementoExperiencia.setNombreEmpresa(NombreEmpresa);

                    try {
                        String Puesto = super.scrapyText(elementoConcreto, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
                        elementoExperiencia.setPuestoEmpleado(Puesto);
                    } catch (NoSuchElementException e) {}

                    try {
                        String Duracion = super.scrapyText(elementoConcreto, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]");
                        Fechas fechaFormateada = new Fechas(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    } catch (NoSuchElementException e) {}

                    try {
                        String Ubicacion = super.scrapyText(elementoConcreto, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]");
                        elementoExperiencia.setUbicacionEmpleado(Ubicacion);
                    } catch (NoSuchElementException e) {}

                    elementosExperiencia.add(elementoExperiencia);
                    contador++;
                } catch (NoSuchElementException e) {break;}
                
            }
            i++;

        }
        return elementosExperiencia;
    }

}
