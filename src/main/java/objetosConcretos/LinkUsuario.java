package objetosConcretos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class LinkUsuario {
    
    @Setter private String UrlUsuario;
    @Setter private Boolean visitado;

    @Override
    public String toString() {
        return "LinkUsuario{" + "UrlUsuario=" + UrlUsuario + ", visitado=" + visitado + '}';
    }

    
}
