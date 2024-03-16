package database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import modelo.LinkUsuario;
import org.bson.Document;

public class BusquedaDatos {

    private final MongoDBConnection db;

    public BusquedaDatos() {
        this.db = MongoDBConnection.getInstance();
    }

    public List<String> obtenerNombresUsuarios() {
        List<String> nombresUsuarios = new ArrayList<>();

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Pivotes");

        // Utiliza el método distinct() para obtener los nombres de usuario distintos
        List<Object> resultados = collection.distinct("nombre", String.class).into(new ArrayList<>());

        // Convierte los resultados a una lista de String
        for (Object resultado : resultados) {
            nombresUsuarios.add((String) resultado);
        }

        return nombresUsuarios;
    }

    public long obtenerNumeroDocumentosEnColeccion() {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        long count = collection.countDocuments();
        return count;
    }

    public String buscarUrlAsociadoConNombrePivote(String nombreABuscar) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Pivotes");

        Document filtro = new Document("nombre", nombreABuscar);

        Document resultado = collection.find(filtro).first();

        String url = resultado.getString("UrlUsuario");
        System.out.println("URL encontrada para " + nombreABuscar + ": " + url);
        return url;
    }

    public List<LinkUsuario> obtenerDocumentos(int cantidad) {
        List<LinkUsuario> listaLinks = new ArrayList<>();

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

        long totalDocumentos = collection.countDocuments();

        if (totalDocumentos == 0) {
            System.out.println("La colección está vacía.");
            return listaLinks;
        }

        int cantidadReal = (int) Math.min(totalDocumentos, cantidad);

        Document filtro = new Document("visitado", false);

        try (MongoCursor<Document> cursor = collection.find(filtro).limit(cantidadReal).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                LinkUsuario linkUsuario = documentoALinkUsuario(doc);
                listaLinks.add(linkUsuario);
            }
        }

        return listaLinks;
    }

    private LinkUsuario documentoALinkUsuario(Document doc) {

        String urlUsuario = doc.getString("UrlUsuario");
        Boolean visitado = doc.getBoolean("visitado");
        return new LinkUsuario(doc.getObjectId("_id"), urlUsuario, visitado);
    }

}
