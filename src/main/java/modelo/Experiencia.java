package modelo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Experiencia {
    
    @Setter private String nombreEmpresa;
    @Setter private String puestoEmpleado;
    @Setter private Fechas permanenciaEmpleado;
    @Setter private String ubicacionEmpleado;
   
}
