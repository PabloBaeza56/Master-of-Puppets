package modelo;

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
    @Setter @Getter private Boolean visitado;
}
