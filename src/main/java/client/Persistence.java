package client;

import com.mongodb.ConnectionString;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Collection;

public class Persistence {
    private String host;
    private int port;
    private MongoClient client;
    private MongoCollection<Document> collection;

    Persistence(String host, int port) {
        this.host = host;
        this.port = port;
        client = MongoClients.create();
    }

    public void setDatabaseCollection(String databaseName, String collectionName) {
        collection = client.getDatabase(databaseName).getCollection(collectionName);
    }

    boolean writeData(Document data) {
        try {
            collection.insertOne(data);
        } catch (MongoWriteException e) {
            return false;
        }
        return true;
    }
}
