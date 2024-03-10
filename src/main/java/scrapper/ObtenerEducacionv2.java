package scrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objetosConcretos.Educacion;

public class ObtenerEducacionv2 {

    private WebDriver driver;

    public ObtenerEducacionv2(WebDriver driver) {
        this.driver = driver;
    }

    public List<Educacion> obtenerEducacion() {
        List<Educacion> educaciones = new ArrayList<>();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Esperar a que la sección de educación esté presente
            WebElement educationSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Educación')]")));

            // Obtener todos los elementos de educación
            List<WebElement> educationItems = educationSection.findElements(By.xpath("./following-sibling::ul//li"));

            // Iterar sobre los elementos de educación
            for (WebElement educationItem : educationItems) {
                Educacion educacion = new Educacion();

                // Extraer datos de la educación
                educacion.setCentroEducativo(obtenerTexto(educationItem, ".//div/div/div/div/span[1]"));
                educacion.setGradoAcademico(obtenerTexto(educationItem, ".//span[1]/span[1]"));

                String fecha = obtenerTexto(educationItem, ".//span[2]/span[1]");
                String[] partesFecha = fecha.split("-");
                educacion.setAnioIngreso(partesFecha[0].trim());
                educacion.setAnioEgreso(partesFecha[1].trim());

                // Agregar educación a la lista
                educaciones.add(educacion);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Elemento de educación no encontrado");
        }

        return educaciones;
    }

    private String obtenerTexto(WebElement element, String xpath) {
        try {
            WebElement subElement = element.findElement(By.xpath(xpath));
            return subElement.getText().trim();
        } catch (NoSuchElementException e) {
            System.out.println("Elemento no encontrado: " + xpath);
            return "";
        }
    }
}
