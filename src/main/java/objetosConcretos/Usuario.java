package objetosConcretos;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Setter private ArrayList<Experiencia> experienciaLaboral;
    @Setter private ArrayList<Educacion> educacion;
    @Setter private Object datosBasicos;

    @Override
    public String toString() {
        return """
               Usuario{
               \texperiencia=""" + experienciaLaboral + 
                "\n\teducacion=" + educacion + 
                "\n\tdatosBasicos=" + datosBasicos + 
                "\n}";
    }
 
}
