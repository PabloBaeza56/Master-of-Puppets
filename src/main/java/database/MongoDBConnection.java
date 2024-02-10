package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
    
    
    
}
