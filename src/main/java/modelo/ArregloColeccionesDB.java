package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ArregloColeccionesDB implements Serializable {

    private static final long serialVersionUID = 1L;
    public ArrayList<String> lista;
    private final String nombreArchivo = "colecciones.ser";

    public ArregloColeccionesDB() {
        lista = new ArrayList<>();

    }

    public void agregarDato(String dato) {
        lista.add(dato);
    }

    public void eliminarDato(String dato) {
        lista.remove(dato);
    }

    public void mostrarDatos() {
        for (String dato : lista) {
            System.out.println(dato);
        }
    }

    public void guardarDatos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.nombreArchivo))) {
            out.writeObject(this);
            System.out.println("Datos guardados exitosamente en " + this.nombreArchivo);
        } catch (IOException e) {
        }
    }

    public ArregloColeccionesDB cargarDatos() {
        ArregloColeccionesDB datos = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.nombreArchivo))) {
            datos = (ArregloColeccionesDB) in.readObject();
            System.out.println("Datos cargados exitosamente desde " + this.nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
        }
        return datos;
    }

    public ArrayList<String> obtenerLista() {
        return lista;
    }

}
