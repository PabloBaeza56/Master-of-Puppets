package scrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static utilidades.Generales.esperarSegundos;

public class VerificarSecciones {
    private final WebDriver driver;

    public VerificarSecciones(WebDriver driver) {
        this.driver = driver;  
    }
    
    public int buscarIndiceSeccion(String seccionDeseada) {
        int elementoDeseado = 0;
        for (int i = 12; i >= 1; i--) {
            try {
            esperarSegundos(1);
            WebElement sectionElement = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + i + "]/div[2]/div/div/div/h2/span[2]"));
       
            //System.out.println("Content of Section 7: " + sectionElement.getText());
            if (sectionElement.getText().equals(seccionDeseada)){
                elementoDeseado = i;
                break;
            }
            } catch (NoSuchElementException e) {}
        }
        
        return elementoDeseado;
    }
   
    
    public void buscarElementos(String seccionDeseada) {
    for (int i = 5; i >= 1; i--) {
        try {
            esperarSegundos(1);
            WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section["+ seccionDeseada +"]/div[3]/ul/li["+ i + "]/div/div[2]/div[1]/a"));
            

            
            WebElement puestoElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal"));
            String[] puesto = puestoElemento.getText().split("\\n");
            System.out.println("Puesto: " + puesto[0]);
          

            // Obtener la fecha
            WebElement fechaElemento = elementoBase.findElement(By.cssSelector("span.t-14.t-normal.t-black--light"));
            String[] fecha = fechaElemento.getText().split("\\n");
            System.out.println("Fecha: " + fecha[0]);
            
            
           

            // Obtener la profesión                                    
            WebElement profesionElemento = elementoBase.findElement(By.cssSelector("div.display-flex.full-width > div.display-flex.align-items-center.mr1.hoverable-link-text.t-bold"));
            String[] profesion = profesionElemento.getText().split("\\n");
            System.out.println("Profesión: " + profesion[0]);
            
            
            System.out.println("----------------------------------------------------------");
            
        } catch (NoSuchElementException e) {}
    }
}

    
}


/*
Content of Section 7: Intereses
Content of Section 7: Idiomas
Content of Section 7: Conocimientos y aptitudes
Content of Section 7: Licencias y certificaciones
Content of Section 7: Educación
Content of Section 7: Experiencia
Content of Section 7: Actividad
Content of Section 7: Servicios
Content of Section 7: Acerca de
*/



 ///html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[7]/div[2]/div/div/div/h2/span[2] //titulo
            ///html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[5]/div[3]/ul/li[1] //Contenido Puro
            ///html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[5]/div[3]/ul/li[4] //Contenido Puro
            ///html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[5]/div[3]/ul/li[1]/div/div[2]/div/div/span[1]/span[1] Empresa
            ///html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[5]/div[3]/ul/li[1]/div/div[2]/div/div/span[2]/span[1]
