package objetosConcretos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Experiencia {
    
    @Setter private String nombreEmpresa;
    @Setter private String puestoEmpleado;
    @Setter private Fechas permanenciaEmpleado;
    @Setter private String ubicacionEmpleado;

    @Override
    public String toString() {
          return """
                 Experiencia{
                 \tnombreEmpresa=""" + nombreEmpresa + 
            "\n\tpuesto=" + puestoEmpleado + 
            "\n\tfecha=" + permanenciaEmpleado + 
            "\n\tubicacion=" + ubicacionEmpleado + 
            "\n}";
    }
   
}
