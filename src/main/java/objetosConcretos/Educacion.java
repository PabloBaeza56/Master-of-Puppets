package objetosConcretos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Educacion {
    private String instituto;
    private String titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int duracion;

    public Educacion() {
        this.instituto = "";
        this.titulo = "";
        this.fechaInicio = LocalDate.parse("", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.fechaFin = LocalDate.parse("", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duracion = 0;
    }

    public Educacion(String instituto, String titulo, String fechaInicio, String fechaFin, int duracion) {
        this.instituto = instituto;
        this.titulo = titulo;
        this.fechaInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.fechaFin = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.duracion = duracion;
    }


    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
