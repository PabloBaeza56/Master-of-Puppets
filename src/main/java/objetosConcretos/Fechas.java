package objetosConcretos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public final class Fechas {
    @Setter private Date fechaInicio;
    @Setter private Date fechaFin;
    @Setter private String duracionMeses;
    
    
    public Fechas (String cadenaFecha){
        this.ObtenerFecha(cadenaFecha);
    }
    
    public void ObtenerFecha( String Fecha){
        String[] elementos = Fecha.split(" - | · ");
        try {
            this.setFechaInicio(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(elementos[0]));
        } catch (ArrayIndexOutOfBoundsException e){}
        
        if (elementos[1].contains("actualidad")){
            try {
                this.setFechaFin(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(this.ConvertirActualidadEnFecha()));
            } catch (ArrayIndexOutOfBoundsException e){}
        } else {
            try {
                this.setFechaFin(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(elementos[1]));
            } catch (ArrayIndexOutOfBoundsException e){}
        }
        
        try {
            this.setDuracionMeses(this.ObtenerDuracionMeses(elementos[2]));
        } catch (ArrayIndexOutOfBoundsException e){}
    }
    
    public String ObtenerDuracionMeses(String cadenaFecha){
        String[] partes = cadenaFecha.split(" ");
        
        int anios = 0;
        int meses = 0;
        
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equals("años") || partes[i].equals("año")) {
                anios = Integer.parseInt(partes[i - 1]);
            } else if (partes[i].equals("meses") || partes[i].equals("mes")) {
                meses = Integer.parseInt(partes[i - 1]);
            }
        }
        
        int totalMeses = (anios * 12) + meses;
        
        return String.valueOf(totalMeses);
    }
    
    public String ConvertirActualidadEnFecha(){
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM. yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        return fechaFormateada;
    }
    
    public Date convertirFechaAFechaLegiblePorLaBaseDeDatos(String fechaTexto){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM. yyyy");
        try {

            Date fecha = formatoFecha.parse(fechaTexto);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fecha);
            calendario.set(Calendar.DAY_OF_MONTH, 1);
            Date fechaFinal = calendario.getTime();

            return fechaFinal;
        } catch (ParseException e) {}
        
        return null;
    }
    
    @Override
    public String toString() {
        return "Fechas{" + "fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", duracionMeses=" + duracionMeses + '}';
    }
    
    
    
}
