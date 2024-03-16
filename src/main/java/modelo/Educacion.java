package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Setter private Date anioIngreso;
    @Setter private Date anioEgreso;
    

    public static Date convertirFechaAFechaLegiblePorLaBaseDeDatos(String fechaTexto) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM. yyyy");
        Date fecha = null;

        try {
            fecha = formatoFecha.parse(fechaTexto);
        } catch (ParseException e) {

            formatoFecha = new SimpleDateFormat("yyyy");
            try {
                fecha = formatoFecha.parse(fechaTexto);
            } catch (ParseException ex) {
            }
        }

        if (fecha != null) {
            return fecha;
        } else {
            return null;
        }
    }
}
