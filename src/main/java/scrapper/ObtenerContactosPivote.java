package scrapper;

import automata.IteradorPorURL;
import database.InserccionDatos;
import database.MongoDBConnection;
import java.util.ArrayList;
import modelo.LinkUsuario;
import modelo.UsuarioPivote;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ObtenerContactosPivote extends MinadoDatos {

    private final IteradorPorURL iterador;
    private final MinadoDatos minador;
    

    public ObtenerContactosPivote(WebDriver driver) {
        super(driver);
        this.minador = new MinadoDatos(super.driver);
        this.iterador = new IteradorPorURL(driver);
    }
    
    public void AccederContactosPivotes(String usuarioDeseado){
        super.driver.get(usuarioDeseado);
        super.esperarSegundos(8);
        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/ul/li"));
        String cadenaSalida = super.obtenerLink(elementoBase);
        super.driver.get(cadenaSalida);
        
        super.esperarSegundos(5);
        
      
        cadenaSalida = cadenaSalida.replace("&origin=MEMBER_PROFILE_CANNED_SEARCH", "&origin=MEMBER_PROFILE_CANNED_SEARCH&page=XXXXX");
        InserccionDatos mongo = new InserccionDatos();
        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(cadenaSalida.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal =this.minador.obtenerLinksUsuariosLinkedIn();
            mongo.InsertarURL(arregloFinal);
            this.iterador.siguientePagina();
        }
        
    }
    
    public void ActualizarPivotes(){
        super.driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");
         super.esperarSegundos(10);
         InserccionDatos mongo = new InserccionDatos();
        int i = 1;
        while (true){
            try {
                super.esperarSegundos(1);
                UsuarioPivote user = new UsuarioPivote();

                WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div/div/div[2]/div/div/main/div/section/div[2]/div[1]/ul/li["+i+"]"));
  
                
                super.extraerDato(elementoBase, "./div/div/div[1]/a/span[2]", cadenaNombre -> {
                    if (!cadenaNombre.isEmpty()){
                        user.setNombre(cadenaNombre);
                    }
                });
                super.extraerDato(elementoBase, "./div/div/div[2]/a/span[2]", cadenaNombre -> {
                    if (!cadenaNombre.isEmpty()){
                        user.setNombre(cadenaNombre);
                    }
                });
                String url = super.obtenerLink(elementoBase);
                user.setUrlUsuario(url);
               mongo.InsertarPivote(user);

            } catch (NoSuchElementException e) {break;}
            i++; 
        }
    }
    
    
    
}
