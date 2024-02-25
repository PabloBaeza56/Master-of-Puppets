package objetosConcretos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experiencia {
    private String nombreEmpresa;
    private String puesto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int duracionMeses;
    private String ubicacion;
    private String descripcion;
    
    
    public Experiencia() {
        this.nombreEmpresa = "";
        this.puesto = "";
        this.fechaInicio = LocalDate.parse("", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.fechaFin = LocalDate.parse("", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duracionMeses = 0;
        this.ubicacion = "";
        this.descripcion = "";
    }

    public Experiencia(String nombreEmpresa, String puesto, String fechaInicio, String fechaFin, int duracionMeses, String ubicacion, String descripcion) {
        this.nombreEmpresa = nombreEmpresa;
        this.puesto = puesto;
        this.fechaInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.fechaFin = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duracionMeses = duracionMeses;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }
    

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
