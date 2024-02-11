package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static utilidades.Generales.esperarSegundos;

public class VerificarSecciones {
    private final WebDriver driver;

    public VerificarSecciones(WebDriver driver) {
        this.driver = driver;  
    }
    
    public int buscarIndiceSeccion(String seccionDeseada) {
        int elementoDeseado = 0;
        for (int i = 12; i >= 1; i--) {
            try {
            esperarSegundos(1);
            WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[2]"));
       
            //System.out.println("Content of Section 7: " + sectionElement.getText());
            if (sectionElement.getText().equals(seccionDeseada)){
                elementoDeseado = i;
                break;
            }
            } catch (NoSuchElementException e) {}
        }
        
        return elementoDeseado;
    }
   
    
    public void buscarElementos(String seccionDeseada) {
        int ContadorElemento = 1;
        Boolean finResulatdo = false;
        while (finResulatdo == false) {
            try {
                esperarSegundos(1);
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section["+ seccionDeseada +"]/div[3]/ul/li["+ ContadorElemento + "]/div/div[2]/div[1]/a"));

                WebElement puestoElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal"));
                String[] puesto = puestoElemento.getText().split("\\n");
                System.out.println("Puesto: " + puesto[0]);

                WebElement fechaElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal.t-black--light"));
                String[] fecha = fechaElemento.getText().split("\\n");
                System.out.println("Fecha: " + fecha[0]);
                                    
                WebElement profesionElemento = elementoBase.findElement(By.cssSelector("div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));
                String[] profesion = profesionElemento.getText().split("\\n");
                System.out.println("Profesión: " + profesion[0]);


                System.out.println("----------------------------------------------------------");
                ContadorElemento++;

            } catch (NoSuchElementException e) {
                finResulatdo = true;
                break;
            }
        }
    }

    public void buscarExperiencia(String seccionDeseada) {
        for (int i = 0; i<6; i++){
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li["+ i +"]/div/div[2]"));///div/div/div/div/div/div/span[1]
            
       
                //------------NO MOVER
                try{
                   WebElement puestoElement = elementoBase.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                    String[] titulo = puestoElement.getText().split("\\n");
                    System.out.println("Título: " + titulo[0]);
                }catch (NoSuchElementException e) {}
          
                //------------NO MOVER
                try{
                     WebElement empresaElemento = elementoBase.findElement(By.xpath(".//span[@class='t-14 t-normal']"));
                    String[] puesto = empresaElemento.getText().split("\\n");
                    System.out.println("Empresa: " + puesto[0]);
                }catch (NoSuchElementException e) {}
                
                
                //------------NO MOVER
                try{
                    WebElement fechaElemento = elementoBase.findElement(By.xpath(".//span[contains(@class, 't-14')][3]"));
                    String[] fecha = fechaElemento.getText().split("\\n");
                    System.out.println("Fecha: " + fecha[0]);
                }catch (NoSuchElementException e) {}
                
                try{
                    WebElement descripcionElemento = elementoBase.findElement(By.xpath(".//div[contains(@class, 'pv-shared-text-with-see-more')]//span"));
                    String[] descripcion = descripcionElemento.getText().split("\\n");
                    System.out.println("Descripción: " + descripcion[0]);
                }catch (NoSuchElementException e) {}

              
                //String codigoHTML = elementoBase.getAttribute("outerHTML");
                //System.out.println(codigoHTML);
                
                System.out.println("---------------------");
            } catch (NoSuchElementException e) {}
            
        }
       
                                                                      
             
        
    }
}


