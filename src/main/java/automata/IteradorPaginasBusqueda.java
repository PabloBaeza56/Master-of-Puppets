package automata;

import org.openqa.selenium.WebDriver;


public interface IteradorPaginasBusqueda {
     
    public void siguientePagina();
    public boolean esUltimaPagina(WebDriver driver);   
}
