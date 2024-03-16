package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Educacion {

    @Setter private String centroEducativo;
    @Setter private String gradoAcademico;
    @Setter private Date anioIngreso;
    @Setter private Date anioEgreso;
    
    

    @Override
    public String toString() {
        return """
               Educacion{
               \t centroEducativo=""" + centroEducativo + 
            "\n \t gradoAcademico=" + gradoAcademico + 
            "\n\t anioIngreso=" + anioIngreso + 
            "\n\t anioEgreso=" + anioEgreso + 
            "\n}";
    } 
    

        // Primero, intentamos analizar la fecha con un formato que incluya el mes y el año completo
       public static Date convertirFechaAFechaLegiblePorLaBaseDeDatos(String fechaTexto) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM. yyyy");
        Date fecha = null;

        try {
            fecha = formatoFecha.parse(fechaTexto);
        } catch (ParseException e) {
            // Si falla el primer intento, intentamos con el segundo formato
            formatoFecha = new SimpleDateFormat("yyyy");
            try {
                fecha = formatoFecha.parse(fechaTexto);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        if (fecha != null) {
            // Si el formato original tiene el mes, no modificamos la fecha
            
            return fecha;
        } else {
            return null;
        }
    }
}
