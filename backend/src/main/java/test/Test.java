package test;

import poc.backend.db.DbConnexion;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Test {

	/**
	 * @param args
	 */
	public Test() {
		DbConnexion obj = new DbConnexion();
		DB db = obj.connexion();

		DBCollection coll = db.getCollection("testCollection");

		BasicDBObject doc = new BasicDBObject("name", "MongoDB").
				append("type", "database").
				append("count", 1).
				append("info", new BasicDBObject("x", 203).append("y", 102));

		coll.insert(doc);
		System.out.println("ok");
		
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

	}

}
