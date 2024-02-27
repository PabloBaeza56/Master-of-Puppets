package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerDatosCabecera extends MinadoDatos{

    public ObtenerDatosCabecera() {
        super();
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
