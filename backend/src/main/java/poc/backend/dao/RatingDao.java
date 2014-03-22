package poc.backend.dao;

import poc.backend.dto.RatingDto;
import poc.backend.entity.Rating;


public class RatingDao {
	
	static private GenericDAO<Rating> delegate = new GenericDAO<Rating>("rating", Rating.class){
		public void initialize() throws Exception{
//			ensureIndex("login", SortOrder.ASCENDING);
		}
	};
	
	public static boolean create(RatingDto rating){
		Rating exist = delegate.findOne("{idBook:#}", rating.idBook);
		if(exist == null){
			exist = new Rating(rating.idBook, 0, 0, 0);
		}
		exist.nbVote+=1;
		exist.SumRating += rating.rating;
		exist.average = exist.SumRating / exist.nbVote;
		return delegate.saveOrUpdate(exist);
	}
}
