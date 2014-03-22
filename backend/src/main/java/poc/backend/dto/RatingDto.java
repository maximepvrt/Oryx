package poc.backend.dto;

public class RatingDto {
	
	public String idBook;
	public int rating;
	public RatingDto(){}
	public RatingDto(String idBook, int rating) {
		super();
		this.idBook = idBook;
		this.rating = rating;
	}
	
}
