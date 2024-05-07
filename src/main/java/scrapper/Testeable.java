package scrapper;

import controlador.ControladorMaestro;
import java.io.IOException;
import java.text.ParseException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testeable {

    public void fastTest(String url, Consumer<WebDriver> functionToRun) {

        try {
            ControladorMaestro controller = new ControladorMaestro();
            WebDriver newDriver = new ChromeDriver();
            controller.inyectarCookies(newDriver);

            newDriver.get(url);

            functionToRun.accept(newDriver);

            newDriver.close();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Testeable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
