package scrapper;

import automata.Automatron;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CssSelector;
import modelo.datosBasicos;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static scrapper.Waitable.esperaImplicita;

public class ObtenerDatosCabecera extends Mineable implements ScrapeableProduct {

    private final datosBasicos cabecera;

    public ObtenerDatosCabecera(WebDriver driver) {
        super(driver);
        this.cabecera = new datosBasicos();
    }

    @Override
    public Boolean existeSeccion() {
        try {
            WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

  

    @Override
    public datosBasicos reclamarDatos() throws MandatoryElementException {
        esperaImplicita(this.driver);

        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/div[2]"));

        assert elementoBase != null : "elementoBase es null (Cabecera)";

        super.settearMinadoObligatorio(
                elementoBase.findElement(By.xpath("./div[1]/div[1]")),
                new CssSelector(".//span[contains(@class, 'artdeco-hoverable-trigger artdeco-hoverable-trigger--content-placed-bottom')]"),
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
                new CssSelector(".//span[contains(@class, 'text-body-small inline t-black--light break-words')]"),
                "Ubicacion Persona",
                this.cabecera::setUbicacion
        );

        return this.cabecera;
    }
    
     public static void main(String[] args) throws IOException, ParseException {

        Testeable test = new Testeable();
        String url = "https://www.linkedin.com/in/lizeth-susana-vel%C3%A1zquez-lemus-3764542b4/";
        test.fastTest(url, (WebDriver driver1) -> {
            Automatron movilizador = new Automatron(driver1);
            movilizador.busquedaIndicesSeccionesMain();
            System.out.println(movilizador.getIndicesSeccionesMain());
            ObtenerDatosCabecera xp = new ObtenerDatosCabecera(driver1);
            try {
                System.out.println(xp.reclamarDatos());
            } catch (MandatoryElementException ex) {
                Logger.getLogger(ObtenerEducacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
