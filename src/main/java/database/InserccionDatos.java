
package database;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import modelo.LinkUsuario;
import modelo.Usuario;
import modelo.UsuarioPivote;
import org.bson.Document;
import org.bson.types.ObjectId;


public class InserccionDatos {
    
    private MongoDBConnection db;
    
    public InserccionDatos(){
        this.db = MongoDBConnection.getInstance();
    }
    
    
    public void InsertarURL(ArrayList<LinkUsuario> usuarios) {
        for (LinkUsuario usuario : usuarios) {
            InsertarURL(usuario);
        }
    }

    public void SubirUsuario(Usuario usuario) {

        
        MongoCollection<Document> collection = this.db.getDatabase().getCollection("2024");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
    public void InsertarURL(LinkUsuario usuario) {

        
       MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
    public void InsertarPivote(UsuarioPivote usuario) {

        
       MongoCollection<Document> collection = this.db.getDatabase().getCollection("Pivotes");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
     public void marcarDocumentoComoVisitado(ObjectId idDocumento) {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

        Document filtroId = new Document("_id", idDocumento);

        Document update = new Document("$set", new Document("visitado", true));

        collection.updateOne(filtroId, update);
    }
     
     public void eliminarDuplicadosPorUrlUsuario() {

        MongoCollection<Document> collection = this.db.getDatabase().getCollection("Links");

        // Utilizamos un conjunto para almacenar los URLUsuario únicos
        Set<String> urlsUnicos = new HashSet<>();

        // Creamos un cursor para recorrer todos los documentos en la colección
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String urlUsuario = doc.getString("UrlUsuario");
                // Verificamos si ya hemos visto este URLUsuario antes
                if (urlsUnicos.contains(urlUsuario)) {
                    // Si ya existe, eliminamos el documento duplicado
                    collection.deleteOne(new Document("UrlUsuario", urlUsuario));
                } else {
                    // Si es único, lo agregamos al conjunto para futuras comprobaciones
                    urlsUnicos.add(urlUsuario);
                }
            }
        }
    }
    
    
}
