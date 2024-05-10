package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.Setter;

public final class fechasEducacion {

    @Setter
    private Date anioIngreso;
    @Setter
    private Date anioEgreso;

    public fechasEducacion(String cadenaFecha) {
        if (!cadenaFecha.isEmpty()) {
            this.ObtenerFecha(cadenaFecha);
        }

    }

    public void ObtenerFecha(String Fecha) {

        String[] elementos = Fecha.split(" - | · ");

        try {
            this.setAnioIngreso(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(elementos[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        if (elementos[1].contains("actualidad")) {
            try {
                this.setAnioEgreso(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(this.ConvertirActualidadEnFecha()));
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        } else {
            try {
                this.setAnioEgreso(this.convertirFechaAFechaLegiblePorLaBaseDeDatos(elementos[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

    }

    public String ConvertirActualidadEnFecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM. yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        return fechaFormateada;
    }

    public Date convertirFechaAFechaLegiblePorLaBaseDeDatos(String fechaTexto) {
        SimpleDateFormat formatoFecha1 = new SimpleDateFormat("MMM. yyyy");
        SimpleDateFormat formatoFecha2 = new SimpleDateFormat("yyyy");
        try {
            formatoFecha1.setLenient(false);
            Date fecha = formatoFecha1.parse(fechaTexto);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fecha);
            calendario.set(Calendar.DAY_OF_MONTH, 1);
            Date fechaFinal = calendario.getTime();

            return fechaFinal;
        } catch (ParseException e1) {
            try {
                formatoFecha2.setLenient(false);
                Date fecha = formatoFecha2.parse(fechaTexto);
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(fecha);
                calendario.set(Calendar.DAY_OF_MONTH, 1);
                Date fechaFinal = calendario.getTime();

                return fechaFinal;
            } catch (ParseException e2) {}
        }

        return null;
    }

}
