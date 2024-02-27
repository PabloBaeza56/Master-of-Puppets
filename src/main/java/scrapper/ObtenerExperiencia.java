package scrapper;

import java.util.ArrayList;
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

        for (int i : this.elementosCasoSimple) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]
                                                                       
                try {
                    System.out.println("Puesto: " + super.scrapyText(elementoBase, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                } catch (NoSuchElementException e) {}


                try {
                    System.out.println("Empresa: " + super.scrapyText(elementoBase, "xpath", ".//span[@class='t-14 t-normal']"));
                } catch (NoSuchElementException e) {}

                
                try {
                    System.out.println("Fecha: " + super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]"));
                } catch (NoSuchElementException e) {}


                try {
                    System.out.println("Ubicacion: " + super.scrapyText(elementoBase, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]"));
                } catch (NoSuchElementException e) {}

                try {
                    System.out.println("Descripción: " + super.scrapyText(elementoBase, "xpath", ".//div[contains(@class, 'pv-shared-text-with-see-more')]//span"));
                } catch (NoSuchElementException e) {}

                //String codigoHTML = elementoBase.getAttribute("outerHTML");
                //System.out.println(codigoHTML);
                System.out.println("---------------------");
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
                    try {
                        WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + contador + "]/div/div[2]/div/a")); // /a
                        
                        System.out.println("Empresa: " + Titulo);   
                        
                        try {
                            System.out.println("Puesto: " + super.scrapyText(fechaElemento, "xpath", ".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Tipo de contrato: " + super.scrapyText(fechaElemento, "xpath", ".//span[@class='t-14 t-normal']"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Duracion: " + super.scrapyText(fechaElemento, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][1]"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Ubicacion: " + super.scrapyText(fechaElemento, "xpath", ".//span[contains(@class, 't-14 t-normal t-black--light')][2]"));
                        } catch (NoSuchElementException e) {}

                        
                        try {
                            System.out.println("Ubicacion: " + super.scrapyText(elementoBase, "xpath", "./div[2]/ul/li[" + contador + "]/div/div[2]/div[2]/ul/li/div/ul/li/div/div/div"));
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
    
    

}
