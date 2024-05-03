
package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioPivote {
    
    @Setter @Getter private String nombre;
    @Setter @Getter private String UrlUsuario;
}


