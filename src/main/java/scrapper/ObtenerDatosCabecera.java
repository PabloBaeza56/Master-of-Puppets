package scrapper;

import modelo.RelativeXpath;
import modelo.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerDatosCabecera extends Mineable implements ScrapeableProduct {

    private datosBasicos cabecera;

    public ObtenerDatosCabecera(WebDriver driver) {
        super(driver);
        this.cabecera = new datosBasicos();
    }

    @Override
    public datosBasicos reclamarDatos() throws MandatoryElementException, MandatorySectionException {
        super.esperaImplicita();

        WebElement elementoBase = null;
        try {
            elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));
        } catch (NoSuchElementException e) {
            throw new MandatorySectionException("Cabecera no encontrada");
        }

        assert elementoBase != null : "elementoBase es null (Cabecera)";

        super.settearMinadoObligatorio(
                elementoBase.findElement(By.xpath("./div[1]/div[1]")),
                new RelativeXpath(".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]"),
                "Nombre Persona",
                this.cabecera::setNombre
        );

        super.settearMinadoObligatorio(
                elementoBase.findElement(By.xpath("./div[1]/div[2]")),
                "Leyenda Persona",
                this.cabecera::setLeyenda
        );

        super.settearMinadoObligatorio(
                elementoBase.findElement(By.xpath("./div[2]")),
                new RelativeXpath(".//span[contains(@class, 'text-body-small inline t-black--light break-words')]"),
                "Ubicacion Persona",
                this.cabecera::setUbicacion
        );

        return this.cabecera;
    }

    

}
