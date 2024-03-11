package database;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import objetosConcretos.Usuario;
import org.bson.Document;

public class MongoDBConnection {
    
    public MongoClient mongoClient;
    
    public MongoDBConnection(){
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileReader("config.properties")); 
            this.mongoClient = MongoClients.create(propiedades.getProperty("mongoURI"));
        } catch (IOException e) {}
    }
    
    public void InsertarURL(String nombreDB , String nombreColeccion, String valorURL){
        
        MongoDatabase database = mongoClient.getDatabase(nombreDB);
        MongoCollection<org.bson.Document> collection = database.getCollection(nombreColeccion);

        org.bson.Document document = new org.bson.Document("URL", valorURL);
        collection.insertOne(document);
        
    }
    
    public void SubirUsuario(Usuario usuario){
        
        MongoDatabase database = mongoClient.getDatabase("STEAM-RADAR");
        MongoCollection<Document> collection = database.getCollection("2024");

        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }
    
    
    
}
