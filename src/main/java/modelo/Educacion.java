package modelo;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Educacion {

    @Setter private String centroEducativo;
    @Setter private String gradoAcademico;
    @Setter private fechasEducacion duracion;

}
