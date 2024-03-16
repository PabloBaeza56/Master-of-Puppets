package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import lombok.Getter;



public class MongoDBConnection {

    @Getter private MongoDatabase database;
   
    private MongoDBConnection() {
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileReader("config.properties"));
            MongoClient mongoClient = MongoClients.create(propiedades.getProperty("mongoURI"));
            this.database = mongoClient.getDatabase("STEAM-RADAR");
        } catch (IOException e) {}
    }
    
    public static MongoDBConnection getInstance() {
        return MongoDBConnectionHolder.INSTANCE;
    }

    private static class MongoDBConnectionHolder {
        private static final MongoDBConnection INSTANCE = new MongoDBConnection();
    }
}
