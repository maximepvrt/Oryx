package poc.backend.dto;


public class TextDto {

	public String title;
	public String summary;
	public String category;
	public String type;
	public String mood;
	public String content;
	public TextDto(){}
	public TextDto(String title, String summary, String category, String type,
			String mood, String content) {
		super();
		this.title = title;
		this.summary = summary;
		this.category = category;
		this.type = type;
		this.mood = mood;
		this.content = content;
	}
	
	
}
