package scrapper;

import objetosConcretos.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerDatosCabecera extends MinadoDatos {

    public ObtenerDatosCabecera(WebDriver driver) {
        super(driver);
    }

    public datosBasicos seccionCabcecera() {
        datosBasicos cabecera = new datosBasicos();
        
        try {
            super.esperarFinCargaPagina();

            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));

            /*
            WebElement elementoA = elementoBase.findElement(By.xpath("./div[1]/div[1]"));
            String Nombre = super.scrapyText(elementoA, ".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]");
            cabecera.setNombre(Nombre);

            WebElement elementoB = elementoBase.findElement(By.xpath("./div[1]/div[2]"));
            String Leyenda = elementoB.getText();
            cabecera.setLeyenda(Leyenda);

            WebElement elementoC = elementoBase.findElement(By.xpath("./div[2]"));
            String Ubicacion = super.scrapyText(elementoC,".//span[contains(@class, 'text-body-small inline t-black--light break-words')]");
            cabecera.setUbicacion(Ubicacion);
            */
            WebElement elementoA = elementoBase.findElement(By.xpath("./div[1]/div[1]"));
            super.extraerDato(elementoA, ".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]", cabecera::setNombre);

            WebElement elementoB = elementoBase.findElement(By.xpath("./div[1]/div[2]"));
            cabecera.setLeyenda(elementoB.getText());

            WebElement elementoC = elementoBase.findElement(By.xpath("./div[2]"));
            super.extraerDato(elementoC, ".//span[contains(@class, 'text-body-small inline t-black--light break-words')]", cabecera::setUbicacion);
            
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Error: Elemento no encontrado.");
        }

        return cabecera;
    }

}
