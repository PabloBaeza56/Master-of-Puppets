package objetosConcretos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Educacion {

    @Setter private String centroEducativo;
    @Setter private String gradoAcademico;
    @Setter private String anioIngreso;
    @Setter private String anioEgreso;

    @Override
    public String toString() {
        return """
               Educacion{
               \t centroEducativo=""" + centroEducativo + 
            "\n \t gradoAcademico=" + gradoAcademico + 
            "\n\t anioIngreso=" + anioIngreso + 
            "\n\t anioEgreso=" + anioEgreso + 
            "\n}";
    } 
}
