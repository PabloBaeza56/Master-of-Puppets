package scrapper;

import automata.Automatron;
import controlador.ControladorMaestro;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import modelo.Experiencia;
import modelo.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.RelativeXpath;
import org.openqa.selenium.chrome.ChromeDriver;

public final class ObtenerExperiencia extends Mineable implements ScrapeableProduct {

    private Integer seccionDeseada;
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
        
    }
    
    @Override
    public Boolean existeSeccion() {
       if (movilizador.getIndicesSeccionesMain().get("Experiencia") != null){
           this.seccionDeseada = this.movilizador.getIndicesSeccionesMain().get("Experiencia");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Experiencia> reclamarDatos() throws MandatoryElementException {
        if (this.existeSeccion()){
            this.determinarTipoElementosExperiencia();
            this.seccionExperienciaCasoSimple();
            this.seccionExperienciaCasoCompuesto();
            return this.elementosExperiencia;
        } else {
            return null;
        }
    }

    private void determinarTipoElementosExperiencia() {

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

    private void seccionExperienciaCasoSimple() throws MandatoryElementException {

        for (int i : this.indicesCasoSimple) {
           
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
               
                
                super.settearMinadoObligatorio(elementoBase, new RelativeXpath("./div[1]/div/span[2]/span[1]"),
                        "Fechas A",
                        cadenaFecha -> {
                            Fechas fechaFormateada = new Fechas(cadenaFecha);
                            elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                        });


                super.settearMinadoOpcional(elementoBase, new RelativeXpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);

                this.elementosExperiencia.add(elementoExperiencia);
            
        }
    }

   private void seccionExperienciaCasoCompuesto() throws MandatoryElementException {


        for (int i : this.indicesCasoCompuesto) {

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));
            
            String NombreEmpresa = super.minarTextoObligatorio(elementoBase, new RelativeXpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"), "Nombre Empresa");
  
            this.movilizador.setSubcadenaParte1("./div[2]/ul/li[");
            this.movilizador.setSubcadenaParte2("]/div/div[2]/div/a");
            while (this.movilizador.existeSiguienteElemento(elementoBase)) {
                Experiencia elementoExperiencia = new Experiencia();
           
                WebElement elementoConcreto = elementoBase.findElement(By.xpath(this.movilizador.getXpathActual())); 

                elementoExperiencia.setNombreEmpresa(NombreEmpresa);
                
                   
                super.settearMinadoObligatorio(elementoConcreto, new RelativeXpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"), "Puesto Empleo Usuario",elementoExperiencia::setPuestoEmpleado);
                
                try {
                    super.settearMinadoObligatorio(elementoConcreto, new RelativeXpath("./span[1]/span[1]"), "Duracion Empleo Usuario", Duracion -> {
                   
                        Fechas fechaFormateada = new Fechas(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });
                } catch (ArrayIndexOutOfBoundsException e){
                    super.settearMinadoObligatorio(elementoConcreto, new RelativeXpath("./span[2]/span[2]"), "Duracion Empleo Usuario", Duracion -> {
             
                        Fechas fechaFormateada = new Fechas(Duracion);
                        elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                    });
                }

                    
                super.settearMinadoOpcional(elementoConcreto, new RelativeXpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"), elementoExperiencia::setUbicacionEmpleado);
                    
                this.elementosExperiencia.add(elementoExperiencia);
                this.movilizador.siguienteElemento();
            }
            this.movilizador.reiniciarIterador();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        
        WebDriver newDriver = null;
        try {
            ControladorMaestro controller = new ControladorMaestro();
             newDriver = new ChromeDriver();
            Automatron movilizador2 = new Automatron(newDriver);

            controller.inyectarCookies(newDriver);

            newDriver.get("https://www.linkedin.com/in/alejandro-behau/");
            movilizador2.busquedaIndicesSeccionesMain();

            ObtenerExperiencia xp = new ObtenerExperiencia(newDriver, movilizador2);
            System.out.println(xp.reclamarDatos());

        } catch ( MandatoryElementException ex) {
            Logger.getLogger(ObtenerExperiencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            newDriver.close();
        }


      

    }

    

}
