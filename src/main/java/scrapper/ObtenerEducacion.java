package scrapper;

import automata.Automatron;
import java.util.ArrayList;
import java.util.function.Consumer;
import modelo.Educacion;
import modelo.RelativeXpath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObtenerEducacion extends Mineable implements ScrapeableProduct {

    private final Automatron movilizador;
    private final ArrayList<Educacion> listaEducacion;

    public ObtenerEducacion(WebDriver driver, Automatron movilizador) {
        super(driver);
        this.movilizador = movilizador;
        this.listaEducacion = new ArrayList<>();
    }
    
    @Override
    public Boolean existeSeccion() {
        if (movilizador.getIndicesSeccionesMain().get("Educacion") != null){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public ArrayList<Educacion> reclamarDatos() throws MandatoryElementException {
        if (this.existeSeccion()){
            return this.minarDatosEducacion();
        } else {
            return null;
        }
    }


    private ArrayList<Educacion> minarDatosEducacion() {

        Integer secciondeseada = movilizador.getIndicesSeccionesMain().get("Educacion");
        

        String subcadenaParte1 = "/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + secciondeseada + "]/div[3]/ul/li[";
        String subcadenaParte2 = "]/div/div[2]/div[1]/a";
        Consumer<WebElement> iterable = elementoBase -> {

            try {
                Educacion educacionPersona = new Educacion();

                super.settearMinadoObligatorio(elementoBase, new RelativeXpath("./div/div/div/div/span[1]"), "setCentroEducativo ", educacionPersona::setCentroEducativo);

                super.settearMinadoOpcional(elementoBase, new RelativeXpath("./span[1]/span[1]"), educacionPersona::setGradoAcademico);

                super.settearMinadoOpcional(elementoBase, new RelativeXpath("./span[2]/span[1]"), (String fecha) -> {
                    
                        String[] partesFecha = fecha.split("-");
                        educacionPersona.setAnioIngreso(Educacion.convertirFechaAFechaLegiblePorLaBaseDeDatos(partesFecha[0].trim()));
                        educacionPersona.setAnioEgreso(Educacion.convertirFechaAFechaLegiblePorLaBaseDeDatos(partesFecha[1].trim()));
                    
                });

                listaEducacion.add(educacionPersona);
            } catch (MandatoryElementException ex) {}

        };

        movilizador.procesarElementos(secciondeseada, subcadenaParte1, subcadenaParte2, iterable);

        return this.listaEducacion;
    }

    

    

}
