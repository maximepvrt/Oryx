package poc.backend.entity;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;


public class Rating {
	@Id
	@ObjectId
	public String id;
	public String idBook;
	public int nbVote;
	public int SumRating;
	public float average;
	public Rating(){}
	public Rating(String idBook, int nbVote, int sumRating,
			float average) {
		super();
		this.idBook = idBook;
		this.nbVote = nbVote;
		SumRating = sumRating;
		this.average = average;
	}
	
	
	
}
