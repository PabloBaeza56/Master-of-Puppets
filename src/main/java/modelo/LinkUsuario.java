package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
public class LinkUsuario {
    @Setter @Getter private ObjectId _id;
    @Setter @Getter private String UrlUsuario;
    @Setter @Getter private Boolean visitado;

    @Override
    public String toString() {
        return "LinkUsuario{" + "UrlUsuario=" + UrlUsuario + ", visitado=" + visitado + '}';
    }

    
}
