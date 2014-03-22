package poc.backend.dto;

import java.util.List;

public class TextDto {

	public String title;
	public String summary;
	public List<String> categories;
	public String type;
	public String mood;
	public String content;
	public long timeToRead;
	public TextDto(){}
	public TextDto(String title, String summary, List<String> categories,
			String type, String mood, String content, long timeToRead) {
		super();
		this.title = title;
		this.summary = summary;
		this.categories = categories;
		this.type = type;
		this.mood = mood;
		this.content = content;
		this.timeToRead = timeToRead;
	}
	
	
}
