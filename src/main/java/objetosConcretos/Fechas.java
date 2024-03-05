package objetosConcretos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Fechas {
    private String fechaInicio;
    private String fechaFin;
    private String duracion;
    
    public Fechas() {
        this.fechaInicio = "";
        this.fechaFin = "";
        this.duracion = "";
    }

    public Fechas(String fechaInicio, String fechaFin, String Duracion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracion = Duracion;
    }

    
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    

    public void setDuracion(String duracionMeses) {
        this.duracion = duracionMeses;
    }

    @Override
    public String toString() {
        return "Fechas{" + "fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", duracion=" + duracion + '}';
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
    
    

   

}
