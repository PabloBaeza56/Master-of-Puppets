package modelo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class datosBasicos {

    @Setter private String nombre;
    @Setter private String leyenda;
    @Setter private String ubicacion;  
}

