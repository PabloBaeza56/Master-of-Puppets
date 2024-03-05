package scrapper;

import automata.IteradorPorURL;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class ObtenerContactosPivote extends MinadoDatos {
    

    public ObtenerContactosPivote() {
        super();

    }
    
    public void AccederContactosPivotes(String usuarioDeseado){
        super.driver.get(usuarioDeseado);
    
        WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/ul/li"));
        String cadenaSalida = super.obtenerLink(elementoBase);
        
        super.esperarSegundos(5);
        
        IteradorPorURL iterador = new IteradorPorURL();
        cadenaSalida = cadenaSalida.replace("&origin=MEMBER_PROFILE_CANNED_SEARCH", "&origin=MEMBER_PROFILE_CANNED_SEARCH&page=XXXXX");
        iterador.iniciarIteracion(cadenaSalida);
        
    }
    
    public void ActualizarPivotes(){
        super.driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");
        int i = 1;
        while (true){
            try {
                super.esperarSegundos(1);

                WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div/div/div[2]/div/div/main/div/section/div[2]/div[1]/ul/li["+i+"]"));

                String url = super.obtenerLink(elementoBase);
                System.out.println(url);

            } catch (NoSuchElementException e) {break;}
            i++; 
        }
    }
    
    
    
}
