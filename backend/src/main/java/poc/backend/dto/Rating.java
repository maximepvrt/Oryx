package poc.backend.dto;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Rating {

	@Id
	@ObjectId
	public String id;
	public String idBook;
	public int stars;
	public Rating(){}
	public Rating(String id, String idBook, int stars) {
		super();
		this.id = id;
		this.idBook = idBook;
		this.stars = stars;
	}
	
}
