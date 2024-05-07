package scrapper;

import automata.Automatron;
import automata.IteradorPorURL;
import database.InserccionDatos;
import java.util.ArrayList;
import modelo.LinkUsuario;
import modelo.CssSelector;
import modelo.UsuarioPivote;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerContactosPivote extends Mineable {

    private final IteradorPorURL iterador;
    private final InserccionDatos mongo;
    private final Automatron movilizador;

    public ObtenerContactosPivote(WebDriver driver) {
        super(driver);

        this.iterador = new IteradorPorURL(driver);
        this.mongo = new InserccionDatos();
        this.movilizador = new Automatron(driver);
    }

    public void AccederContactosPivotes(String usuarioDeseado) {
        super.driver.get(usuarioDeseado);
        super.esperaImplicita();
        super.esperaExplicita(5);
        WebElement elementoBase = super.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[1]/div[2]/ul/li"));
        String cadenaSalida = super.obtenerLink(elementoBase);
        super.driver.get(cadenaSalida);

        super.esperaExplicita(5);

        cadenaSalida = cadenaSalida.replace("&origin=MEMBER_PROFILE_CANNED_SEARCH", "&origin=MEMBER_PROFILE_CANNED_SEARCH&page=XXXXX");

        while (!this.iterador.esUltimaPagina(this.driver)) {
            this.driver.get(cadenaSalida.replace("XXXXX", String.valueOf(this.iterador.getPaginaActual())));
            ArrayList<LinkUsuario> arregloFinal = super.obtenerLinksUsuariosLinkedIn();
            this.mongo.InsertarDocumento(arregloFinal);
            this.iterador.siguientePagina();
        }

    }

    public void ActualizarPivotes() {
        super.driver.get("https://www.linkedin.com/mynetwork/invite-connect/connections/");
        super.esperaImplicita();
        super.esperaExplicita(5);
        this.movilizador.setSubcadenaParte1("/html/body/div[5]/div[3]/div/div/div/div/div[2]/div/div/main/div/section/div[2]/div[1]/ul/li[");
        this.movilizador.setSubcadenaParte2("]");
        while (this.movilizador.existeSiguienteElemento()) {

            super.esperaExplicita(1);
            UsuarioPivote user = new UsuarioPivote();

            WebElement elementoBase = super.driver.findElement(By.xpath(this.movilizador.getXpathActual()));

            super.settearMinadoOpcional(elementoBase, new CssSelector("./div/div/div[1]/a/span[2]"), cadenaNombre -> {
                if (!cadenaNombre.isEmpty()) {
                    user.setNombre(cadenaNombre);
                }
            });
            super.settearMinadoOpcional(elementoBase, new CssSelector("./div/div/div[2]/a/span[2]"), cadenaNombre -> {
                if (!cadenaNombre.isEmpty()) {
                    user.setNombre(cadenaNombre);
                }
            });

            user.setUrlUsuario(super.obtenerLink(elementoBase));
            this.mongo.InsertarDocumento(user);
            this.movilizador.siguienteElemento();

        }
        this.movilizador.reiniciarIterador();
    }

}
