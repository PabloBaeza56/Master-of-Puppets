package automata;

import org.openqa.selenium.WebDriver;


public interface IteradorPaginas {
     
    public void siguientePagina();
    public boolean esUltimaPagina(WebDriver driver);
    public void reiniciar();
    
}
