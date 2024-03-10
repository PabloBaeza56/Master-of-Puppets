package scrapper;

import java.util.ArrayList;
import objetosConcretos.Educacion;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ObtenerEducacion extends MinadoDatos{
  
    public ObtenerEducacion(WebDriver driver) {
        super(driver);
    }
    
    public ArrayList<Educacion> seccionEducacion() {
        
        ArrayList<Educacion> listaEducacion = new ArrayList<>();
        int seccionDeseada = super.buscarIndiceSeccionMain("Educación");
        super.esperarSegundos(2);
        int ContadorElemento = 1;
        
        while (true) {
            try {

              Educacion educacionPersona = new Educacion();
              WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li[" + ContadorElemento + "]/div/div[2]/div[1]/a"));

                try {
                    String Universidad = super.scrapyText(elementoBase, "xpath", "./div/div/div/div/span[1]");
                    educacionPersona.setCentroEducativo(Universidad);
                } catch (NoSuchElementException e) {}
                
                try {
                    String Carrera = super.scrapyText(elementoBase, "xpath", "./span[1]/span[1]");
                    educacionPersona.setGradoAcademico(Carrera);
                } catch (NoSuchElementException e) {}

                try {
                    String Fecha = super.scrapyText(elementoBase, "xpath", "./span[2]/span[1]");
                    String[] partesFecha = Fecha.split("-");
                    educacionPersona.setAnioIngreso(partesFecha[0]);
                    educacionPersona.setAnioEgreso(partesFecha[1]);
                } catch (NoSuchElementException e) {}
                
                
                listaEducacion.add(educacionPersona);
            
            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
        return listaEducacion;
    }

    
    
    
    
}
