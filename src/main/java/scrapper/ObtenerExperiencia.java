package scrapper;

import java.util.ArrayList;
import objetosConcretos.Experiencia;
import objetosConcretos.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public final class ObtenerExperiencia extends MinadoDatos{

    private ArrayList<Integer> elementosCasoSimple;
    private ArrayList<Integer> elementosCasoCompuesto;
    private int seccionDeseada;


    public ObtenerExperiencia() {
        super();
        this.elementosCasoSimple = new ArrayList<>();
        this.elementosCasoCompuesto = new ArrayList<>();    
    }

    public void determinarTipoSecciones() {
        this.seccionDeseada = super.buscarIndiceSeccionMain("Experiencia");

        int i = 1;
        while (true) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));

                try {
                    WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                    WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));

                    this.elementosCasoCompuesto.add(i);
                } catch (NoSuchElementException e) {this.elementosCasoSimple.add(i);}

                i++;
            } catch (NoSuchElementException e) {break;}
        }
    }

    public ArrayList<Experiencia> seccionExperienciaCasoSimple() {
        ArrayList<Experiencia> elementosExperiencia = new ArrayList<>();

        for (int i : this.elementosCasoSimple) {
            Experiencia elementoExperiencia = new Experiencia();
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
                                                                       
                try {
                    String Puesto = super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
                    elementoExperiencia.setPuestoEmpleado(Puesto);
                } catch (NoSuchElementException e) {}


                try {
                    String Empresa = super.scrapyText(elementoBase, "xpath", ".//span[@class='t-14 t-normal']");
                    String[] partes = Empresa.split("·");
                    String primeraSubcadena = partes[0].trim();
                    elementoExperiencia.setNombreEmpresa(primeraSubcadena);
                } catch (NoSuchElementException e) {}

                
                try {
                    String cadenaFecha = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]");
                    Fechas fechaFormateada = new Fechas(cadenaFecha);
                    elementoExperiencia.setPermanenciaEmpleado(fechaFormateada);
                } catch (NoSuchElementException e) {}

                try {
                    String Ubicacion = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]");
                    elementoExperiencia.setUbicacionEmpleado(Ubicacion);
                } catch (NoSuchElementException e) {}

                //try {
                    //String Descripcion = super.scrapyText(elementoBase, "xpath", ".//div[contains(@class, 'pv-shared-text-with-see-more')]//span");
                    //elementoExperiencia.setDescripcion(Descripcion);
                //} catch (NoSuchElementException e) {}

                elementosExperiencia.add(elementoExperiencia);
            } catch (NoSuchElementException e) {}
        }
        return elementosExperiencia;
    }

    public ArrayList<Experiencia> seccionExperienciaCasoCompuesto() {
        ArrayList<Experiencia> elementosExperiencia = new ArrayList<>();

        for (int i : this.elementosCasoCompuesto) {
            
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
                                                                       
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
                            String Puesto=  super.scrapyText(elementoConcreto, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
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
            } catch (NoSuchElementException e) {break;}
        }
        return elementosExperiencia;
    }
    
    

}
