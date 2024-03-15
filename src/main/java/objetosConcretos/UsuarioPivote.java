
package objetosConcretos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPivote {
    @Setter @Getter private String nombre;
    @Setter @Getter private String UrlUsuario;

    @Override
    public String toString() {
        return "UsuarioPivote{" + "nombre=" + nombre + ", UrlUsuario=" + UrlUsuario + '}';
    } 
}


