package database;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.LinkUsuario;
import modelo.Usuario;
import modelo.UsuarioPivote;
import org.bson.Document;
import org.bson.types.ObjectId;

public class InserccionDatos {

    private final MongoDBConnection db;

    public InserccionDatos() {
        this.db = MongoDBConnection.getInstance();
    }

    public void InsertarDocumento(ArrayList<LinkUsuario> usuarios) {
        for (LinkUsuario usuario : usuarios) {
            InserccionDatos.this.InsertarDocumento(usuario);
        }
    }

    public void InsertarDocumento(LinkUsuario usuario) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }

    public void InsertarDocumento(Usuario usuario, String nombreColeccion) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection(nombreColeccion);
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }

    public void InsertarDocumento(UsuarioPivote usuario) {
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Pivotes");
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);

        // Verificar si ya existe un documento con el mismo nombre y URL de usuario
        Document existingDoc = collection.find(
                Filters.and(
                        Filters.eq("nombre", usuario.getNombre()),
                        Filters.eq("UrlUsuario", usuario.getUrlUsuario())
                )
        ).first();

        if (existingDoc == null) {
            collection.insertOne(doc);
            System.out.println("Documento insertado correctamente.");
        } else {
            System.out.println("El documento ya existe en la base de datos.");
        }
    }

    public void marcarDocumentoComoVisitado(ObjectId idDocumento, String nuevaColeccion) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");
        Document filtroId = new Document("_id", idDocumento);
        Document update = new Document("$addToSet", new Document("coleccionesDondeHaSidoUsado", nuevaColeccion));
        collection.updateOne(filtroId, update);
    }

    public void eliminarDocumentosDuplicadosSinColecciones() {
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            Map<String, ObjectId> urlsUnicos = new HashMap<>();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String urlUsuario = doc.getString("UrlUsuario");
                ObjectId id = doc.getObjectId("_id");

                if (urlsUnicos.containsKey(urlUsuario)) {

                    List<String> colecciones = doc.getList("coleccionesDondeHaSidoUsado", String.class);
                    if (colecciones == null || colecciones.isEmpty()) {

                        collection.deleteOne(Filters.eq("_id", id));
                    }
                } else {

                    urlsUnicos.put(urlUsuario, id);
                }
            }
        }
    }

}
