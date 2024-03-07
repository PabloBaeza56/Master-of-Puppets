package scrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import objetosConcretos.Experiencia;
import objetosConcretos.Fechas;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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

    public void seccionExperienciaCasoSimple() {
        ArrayList<Experiencia> elementosExperiencia = new ArrayList<>();

        for (int i : this.elementosCasoSimple) {
            Experiencia elementoExperiencia = new Experiencia();
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
                                                                       
                try {
                    String Puesto = super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
                    elementoExperiencia.setPuesto(Puesto);
                } catch (NoSuchElementException e) {}


                try {
                    String Empresa = super.scrapyText(elementoBase, "xpath", ".//span[@class='t-14 t-normal']");
                    elementoExperiencia.setNombreEmpresa(Empresa);
                } catch (NoSuchElementException e) {}

                
                try {
                    String Fecha = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]");
                    Fechas fechaCorregida = this.ObtenerFecha(Fecha);
                    elementoExperiencia.setFecha(fechaCorregida);
                } catch (NoSuchElementException e) {}

                try {
                    String Ubicacion = super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]");
                    elementoExperiencia.setUbicacion(Ubicacion);
                } catch (NoSuchElementException e) {}

                try {
                    String Descripcion = super.scrapyText(elementoBase, "xpath", ".//div[contains(@class, 'pv-shared-text-with-see-more')]//span");
                    elementoExperiencia.setDescripcion(Descripcion);
                } catch (NoSuchElementException e) {}

                elementosExperiencia.add(elementoExperiencia);
            } catch (NoSuchElementException e) {}
        }
    }

    public void seccionExperienciaCasoCompuesto() {

        for (int i : this.elementosCasoCompuesto) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
                                                                       
                String Titulo = "";
                try {
                    Titulo = super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']");
                } catch (NoSuchElementException e) {}

                int contador = 1;
                while (true) {
                    Experiencia elementoExperiencia = new Experiencia();
                    try {
                        WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + contador + "]/div/div[2]/div/a")); // /a
                        
                        System.out.println("Empresa: " + Titulo);   
                        
                        try {
                            System.out.println("Puesto: " + super.scrapyText(fechaElemento, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                            //elementoExperiencia.setPuesto(Titulo);
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Tipo de contrato: " + super.scrapyText(fechaElemento, "xpath", ".//span[@class='t-14 t-normal']"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Duracion: " + super.scrapyText(fechaElemento, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("AUbicacion: " + super.scrapyText(fechaElemento, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("BUbicacion: " + super.scrapyText(elementoBase, "xpath", "./div[2]/ul/li[" + contador + "]/div/div[2]/div[2]/ul/li/div/ul/li/div/div/div"));
                        } catch (NoSuchElementException e) {}

                        contador++;
                        System.out.println("---------------------");
                    } catch (NoSuchElementException e) {break;}
                }

                //String codigoHTML = elementoBase.getAttribute("outerHTML");
                //System.out.println(codigoHTML);
                i++;
            } catch (NoSuchElementException e) {break;}
        }
    }
    
    public Fechas ObtenerFecha( String Fecha){
        Fechas manejadorFechas = new Fechas();
        String[] elementos = Fecha.split(" - | · ");
        
        manejadorFechas.setFechaInicio(elementos[0]);
        
        if (elementos[1].contains("actualidad")){
            manejadorFechas.setFechaFin(manejadorFechas.ConvertirActualidadEnFecha());
        } else {
            manejadorFechas.setFechaFin(elementos[1]);
        }
        
        manejadorFechas.setDuracion(manejadorFechas.ObtenerDuracionMeses(elementos[2]));
        return manejadorFechas;
    }

}
