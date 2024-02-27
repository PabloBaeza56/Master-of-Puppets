package objetosConcretos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fechas {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private long duracionMeses;

    public Fechas(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionMeses = calcularDuracionMeses();
    }

    private long calcularDuracionMeses() {
        return ChronoUnit.MONTHS.between(fechaInicio, fechaFin);
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

    public long getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(long duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        LocalDate inicio = LocalDate.of(2022, 1, 1);
        LocalDate fin = LocalDate.of(2022, 12, 31);
        Fechas tiempo = new Fechas(inicio, fin);
        System.out.println("Duración en meses: " + tiempo.getDuracionMeses());
    }
}
