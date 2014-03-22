package poc.backend.dao;

import poc.backend.dto.Rating;

public class RatingDao {
	public static class NotExists extends Exception{}
	
	static private GenericDAO<Rating> delegate = new GenericDAO<Rating>("rating", Rating.class){
		public void initialize() throws Exception{
//			ensureIndex("login", SortOrder.ASCENDING);
		}
	};
	
	public static boolean create(Rating rating) throws NotExists{
		Rating exist = delegate.findOne("{id:#}", rating.id);
		if(exist == null){
			throw new NotExists();
		}
	
		return delegate.saveOrUpdate(rating);
	}
}
