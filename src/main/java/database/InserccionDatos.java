package database;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.LinkUsuario;
import modelo.Usuario;
import modelo.UsuarioPivote;
import org.bson.Document;
import org.bson.types.ObjectId;


public class InserccionDatos {
    
    private final MongoDBConnection db;
    
    public InserccionDatos(){
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
        collection.insertOne(doc);
    }
    
    public void marcarDocumentoComoVisitado(ObjectId idDocumento, String nuevaColeccion) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");
     Document filtroId = new Document("_id", idDocumento);
     Document update = new Document("$addToSet", new Document("coleccionesDondeHaSidoUsado", nuevaColeccion));
     collection.updateOne(filtroId, update);
    }
     
     public void eliminarDocumentosDuplicadosSinColecciones() {
     MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

    // Creamos un cursor para recorrer todos los documentos en la colección
    try (MongoCursor<Document> cursor = collection.find().iterator()) {
        Map<String, ObjectId> urlsUnicos = new HashMap<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String urlUsuario = doc.getString("UrlUsuario");
            ObjectId id = doc.getObjectId("_id");

            // Verificamos si ya hemos visto este URLUsuario antes
            if (urlsUnicos.containsKey(urlUsuario)) {
                // Verificamos si coleccionesDondeHaSidoUsado está vacío o no existe
                List<String> colecciones = doc.getList("coleccionesDondeHaSidoUsado", String.class);
                if (colecciones == null || colecciones.isEmpty()) {
                    // Eliminamos el documento duplicado
                    collection.deleteOne(Filters.eq("_id", id));
                }
            } else {
                // Si es único, lo agregamos al mapa para futuras comprobaciones
                urlsUnicos.put(urlUsuario, id);
            }
        }
    }
}
    
    
}
