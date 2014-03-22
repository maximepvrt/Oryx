package poc.backend.db;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DbConnexion {

	public Jongo connexion(){
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Jongo(mongoClient.getDB( "mydb" ));
	}

}
