package modelo;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LinkUsuario {
    
    @Setter @Getter private ObjectId _id;
    @Setter @Getter private String UrlUsuario;
    @Setter @Getter private ArrayList<String> coleccionesDondeHaSidoUsado;
    
     public LinkUsuario(ObjectId _id, String UrlUsuario) {
        this._id = _id;
        this.UrlUsuario = UrlUsuario;
        this.coleccionesDondeHaSidoUsado = new ArrayList<>();
    }
}
