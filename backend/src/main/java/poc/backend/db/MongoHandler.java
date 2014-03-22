package poc.backend.db;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class MongoHandler {

    static private MongoHandler INSTANCE = null;

    private MongoClient mongoClient;
    private DB db;
    private Jongo jongo;

    synchronized static public Jongo get() {
        if (INSTANCE == null) {
            INSTANCE = new MongoHandler();
        }
        return INSTANCE.jongo;
    }

    static public MongoCollection getCollection(String collection) {
        return get().getCollection(collection);
    }

    static public MongoCollection getUserCollection() {
        return get().getCollection("user");
    }

    private MongoHandler() {
        try {
            mongoClient = new MongoClient( "localhost" , 27017 );
            db = mongoClient.getDB("mydb");
            jongo = new Jongo(db);
        } catch (Exception exc) {
            // Do something smart
        }
    }

}