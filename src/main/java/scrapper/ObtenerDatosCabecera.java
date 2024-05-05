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
    public Boolean existeSeccion() {
        try {
            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
    
    @Override
    public datosBasicos reclamarDatos() throws MandatoryElementException, NotFoundFatalSectionException {
         if (this.existeSeccion()){
            return this.minarDatos();
        } else {
            throw new NotFoundFatalSectionException("No se encontro la cabecera");
        }
    }


    public datosBasicos minarDatos() throws MandatoryElementException {
        super.esperaImplicita();

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));

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
