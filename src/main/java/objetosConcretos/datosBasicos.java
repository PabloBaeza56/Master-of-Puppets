package objetosConcretos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class datosBasicos {

    @Setter private String nombre;
    @Setter private String leyenda;
    @Setter private String ubicacion;

    @Override
    public String toString() {
        return """
               datosBasicos{
               \tnombre=""" + nombre + 
                "\n\tleyenda=" + leyenda + 
                "\n\tubicacion=" + ubicacion + 
                "\n}";
    }
}
