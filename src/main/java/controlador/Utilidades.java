package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Utilidades {

    protected Calendar obtenerFechaActual() {
        return Calendar.getInstance();
    }

    protected void escribirEnArchivoFechaActualMas10Dias() {

        Date fechaActual = new Date();

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);

        calendario.add(Calendar.DAY_OF_YEAR, 10);

        Date fechaMas10Dias = calendario.getTime();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = formato.format(fechaMas10Dias);

        String nombreArchivo = "fecha_actual.txt";

        String rutaArchivo = System.getProperty("user.dir") + File.separator + nombreArchivo;

        try {
            try (FileWriter escritor = new FileWriter(rutaArchivo)) {
                escritor.write(fechaFormateada);
            }
            System.out.println("Se ha creado el archivo '" + nombreArchivo + "' correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    protected void escribirEnArchivoFechaActual() {

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = formato.format(fechaActual);

        String nombreArchivo = "fecha_actual.txt";

        String rutaArchivo = System.getProperty("user.dir") + File.separator + nombreArchivo;

        try {
            try (FileWriter escritor = new FileWriter(rutaArchivo)) {
                escritor.write(fechaFormateada);
            }
            System.out.println("Se ha creado el archivo '" + nombreArchivo + "' correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

    }

    protected Date leerFechaArchivo(String rutaArchivo) throws IOException, ParseException {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String fechaArchivoTexto = lector.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formato.parse(fechaArchivoTexto);
        }
    }

    protected boolean haPasadoUnaSemana(Date fechaArchivo, Date fechaActual) {
        long diferenciaEnMilisegundos = fechaActual.getTime() - fechaArchivo.getTime();
        long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
        return diferenciaEnDias >= 7;
    }
    
    protected void modificarArchivoProperties(String clave, String nuevoValor) {
        Properties properties = new Properties();
        OutputStream output = null;
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("config.properties");
            properties.load(inputStream);

            properties.setProperty(clave, nuevoValor);

            output = new FileOutputStream("config.properties");
            properties.store(output, null);

            System.out.println("Archivo properties modificado correctamente.");
        } catch (IOException io) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected String leerValorProperties(String rutaArchivo, String clave) {
        Properties properties = new Properties();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(rutaArchivo);
            properties.load(inputStream);

            String valor = properties.getProperty(clave);

            return valor;
        } catch (IOException io) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
