package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerEducacion extends MinadoDatos {

    public ObtenerEducacion() {
        super();
    }

    public void seccionEducacion() {
        int seccionDeseada = super.buscarIndiceSeccionMain("Educación");
        int ContadorElemento = 1;
        while (true) {
            try {
                super.esperarSegundos(1);

                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li[" + ContadorElemento + "]/div/div[2]/div[1]/a"));

                System.out.println("Puesto: " + super.scrapyText(elementoBase, "css", "span.t-14.t-normal"));

                System.out.println("Fecha: " + super.scrapyText(elementoBase, "css", "span.t-14.t-normal.t-black--light"));

                System.out.println("Profesión: " + super.scrapyText(elementoBase, "css", "div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));


            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
    }

}
