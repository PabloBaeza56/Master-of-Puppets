package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ObtenerEducacion extends MinadoDatos{

    public ObtenerEducacion(WebDriver driver) {
        super(driver);
    }
 
    public void seccionEducacion() {
        int seccionDeseada = super.buscarIndiceSeccion("Educación");
        int ContadorElemento = 1;
        while (true) {
            try {
                super.esperarSegundos(1);
                
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section["+ seccionDeseada +"]/div[3]/ul/li["+ ContadorElemento + "]/div/div[2]/div[1]/a"));

                //WebElement puestoElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal"));
                //String[] puesto = puestoElemento.getText().split("\\n");
                System.out.println("Puesto: " + super.scrapyText(elementoBase, "span.t-14.t-normal"));

                //WebElement fechaElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal.t-black--light"));
                //String[] fecha = fechaElemento.getText().split("\\n");
                System.out.println("Fecha: " + super.scrapyText(elementoBase, "span.t-14.t-normal.t-black--light"));
                                    
                //WebElement profesionElemento = elementoBase.findElement(By.cssSelector("div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));
                //String[] profesion = profesionElemento.getText().split("\\n");
                System.out.println("Profesión: " + super.scrapyText(elementoBase, "div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));


                System.out.println("----------------------------------------------------------");
                
            } catch (NoSuchElementException e) {
                break;
            }
            ContadorElemento++;
        }
    }

    
    
   
}


