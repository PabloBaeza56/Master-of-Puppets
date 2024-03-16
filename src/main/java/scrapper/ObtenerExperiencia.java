package scrapper;

import java.util.ArrayList;
import modelo.Experiencia;
import modelo.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import automata.AutomataDatos;

public final class ObtenerExperiencia extends MinadoDatos {

    private final Integer seccionDeseada;
    private final ArrayList<Integer> indicesCasoSimple;
    private final ArrayList<Integer> indicesCasoCompuesto;
    private final AutomataDatos movilizador;
    private final  ArrayList<Experiencia> elementoCasoSimple;
    private final  ArrayList<Experiencia> elementoCasoCompuesto;
    

    public ObtenerExperiencia(WebDriver driver, AutomataDatos movilizador) {
        super(driver);
        this.movilizador = movilizador;
        this.indicesCasoSimple = new ArrayList<>();
        this.indicesCasoCompuesto = new ArrayList<>(); 
        this.elementoCasoSimple = new ArrayList<>();
        this.elementoCasoCompuesto = new ArrayList<>();     
        this.seccionDeseada = this.movilizador.getIndicesSeccionesMain().get("Experiencia");
    }
    
    public ArrayList<Experiencia> seccionExperiencia(){
        this.determinarTipoSecciones();
        this.seccionExperienciaCasoSimple();
        this.seccionExperienciaCasoCompuesto();
        
        ArrayList<Experiencia> acumuladoTotal = new ArrayList<>();
        acumuladoTotal.addAll(this.elementoCasoSimple);
        acumuladoTotal.addAll(this.elementoCasoCompuesto);
        
        return acumuladoTotal;
    }

    private void determinarTipoSecciones() {
        this.movilizador.iteradorTabla.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[");
        this.movilizador.iteradorTabla.setSubcadenaParte2("]/div/div[2]");

        while (this.movilizador.iteradorTabla.existeSiguienteElemento()) {

            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual()));

            try {
                WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                puestoElement.getText();
                this.indicesCasoCompuesto.add(this.movilizador.iteradorTabla.getIndiceFilatabla());
            } catch (NoSuchElementException e) {
                this.indicesCasoSimple.add(this.movilizador.iteradorTabla.getIndiceFilatabla());
            }

            this.movilizador.iteradorTabla.siguienteElemento();
        }
        this.movilizador.iteradorTabla.reiniciarIterador();
    }

    private void seccionExperienciaCasoSimple() {
        
        for (int i : this.indicesCasoSimple) {
            Experiencia elementoExperiencia = new Experiencia();

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

            super.extraerDato(elementoBase, ".//div[@class='display-flex flex-wrap align-items-center full-height']", elementoExperiencia::setPuestoEmpleado);
            
            super.extraerDato(elementoBase, ".//span[@class='t-14 t-normal']", dato -> {
                String primeraSubcadena = dato.split("·")[0].trim();
                elementoExperiencia.setNombreEmpresa(primeraSubcadena);
            });
            
            super.extraerDato(elementoBase, ".//span[contains(@class, 't-14 t-normal t-black--light')][1]", cadenaFecha -> {
                Fechas fechaFormateada = new Fechas(cadenaFecha);
                elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
            });
            
            super.extraerDato(elementoBase, ".//span[contains(@class, 't-14 t-normal t-black--light')][2]", elementoExperiencia::setUbicacionEmpleado);
 
            this.elementoCasoSimple.add(elementoExperiencia);
        }
    }

    private void seccionExperienciaCasoCompuesto() {


        for (int i : this.indicesCasoCompuesto) {

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));
            
            String NombreEmpresa = super.obtenerTexto(elementoBase, ".//div[@class='display-flex flex-wrap align-items-center full-height']");
  
            this.movilizador.iteradorTabla.setSubcadenaParte1("./div[2]/ul/li[");
            this.movilizador.iteradorTabla.setSubcadenaParte2("]/div/div[2]/div/a");
            while (this.movilizador.iteradorTabla.existeSiguienteElemento(elementoBase)) {
                Experiencia elementoExperiencia = new Experiencia();
           
                WebElement elementoConcreto = elementoBase.findElement(By.xpath(this.movilizador.iteradorTabla.getXpathActual())); 

                elementoExperiencia.setNombreEmpresa(NombreEmpresa);
                   
                super.extraerDato(elementoConcreto, ".//div[@class='display-flex flex-wrap align-items-center full-height']", elementoExperiencia::setPuestoEmpleado);
                    
                super.extraerDato(elementoConcreto, ".//span[contains(@class, 't-14 t-normal t-black--light')][1]", Duracion -> {
                    Fechas fechaFormateada = new Fechas(Duracion);
                    elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                });
                    
                super.extraerDato(elementoConcreto, ".//span[contains(@class, 't-14 t-normal t-black--light')][2]", elementoExperiencia::setUbicacionEmpleado);
                    
                this.elementoCasoCompuesto.add(elementoExperiencia);
                this.movilizador.iteradorTabla.siguienteElemento();
            }
            this.movilizador.iteradorTabla.reiniciarIterador();
        }
    }
    
    

}
