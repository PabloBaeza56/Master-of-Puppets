package scrapper;

import java.util.ArrayList;
import objetosConcretos.Educacion;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;



public class ObtenerEducacion extends MinadoDatos {
    ArrayList<Educacion> listaEducacion;
    public ObtenerEducacion() {
        super();
        this.listaEducacion = new ArrayList<>();
    }

    public ArrayList<Educacion> seccionEducacion() {
        int seccionDeseada = super.buscarIndiceSeccionMain("Educación");
        int ContadorElemento = 1;
        while (true) {
            try {
                super.esperarSegundos(1);
                Educacion educacionPersona = new Educacion();
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + seccionDeseada + "]/div[3]/ul/li[" + ContadorElemento + "]/div/div[2]/div[1]/a"));

                String Universidad = super.scrapyText(elementoBase, "xpath", "./div/div/div/div/span[1]");
                educacionPersona.setCentroEducativo(Universidad);
   
                
                String Carrera = super.scrapyText(elementoBase, "xpath", "./span[1]/span[1]");
                educacionPersona.setGradoAcademico(Carrera);


                String Fecha = super.scrapyText(elementoBase, "xpath", "./span[2]/span[1]");
                String[] partesFecha = Fecha.split("-");
                educacionPersona.setAnioIngreso(partesFecha[0]);
                educacionPersona.setAnioEgreso(partesFecha[1]);
                
                listaEducacion.add(educacionPersona);
            } catch (NoSuchElementException e) {break;}
            ContadorElemento++;
        }
        return listaEducacion;
    }

    
    
    
    
}
