package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerEducacion extends MinadoDatos {

    public ObtenerEducacion(WebDriver driver) {
        super(driver);
    }

    public void seccionEducacion() {
        int seccionDeseada = super.buscarIndiceSeccion("Educación");
        int ContadorElemento = 1;
        while (true) {
            try {
                super.esperarSegundos(1);

                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li[" + ContadorElemento + "]/div/div[2]/div[1]/a"));

                System.out.println("Puesto: " + super.scrapyText(elementoBase, "css", "span.t-14.t-normal"));

                System.out.println("Fecha: " + super.scrapyText(elementoBase, "css", "span.t-14.t-normal.t-black--light"));

                System.out.println("Profesión: " + super.scrapyText(elementoBase, "css", "div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));


            } catch (NoSuchElementException e) {
                break;
            }
            ContadorElemento++;
        }
    }

    public void seccionCabcecera() {
        int seccionDeseada = 1;
        try {
            super.esperarSegundos(1);

            WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[2]/div[2]"));

            try {
                WebElement elementoA = elementoBase.findElement(By.xpath("./div[1]/div[1]"));
                System.out.println("Nombre: " + super.scrapyText(elementoA, "xpath", ".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]"));
            } catch (NoSuchElementException e) {}

            try {
                WebElement elementoB = elementoBase.findElement(By.xpath("./div[1]/div[2]"));
                System.out.println("Leyenda:  " + elementoB.getText());
            } catch (NoSuchElementException e) {}

            try {
                WebElement elementoC = elementoBase.findElement(By.xpath("./div[2]"));
                System.out.println("Ubicacion: " + super.scrapyText(elementoC, "xpath", ".//span[contains(@class, 'text-body-small inline t-black--light break-words')]"));
            } catch (NoSuchElementException e) {}

        } catch (NoSuchElementException e) {}
    }

    
    
}
