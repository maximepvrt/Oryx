package poc.backend.dto;

public class SmallText {
	
	public String  id;
	public String title;
	public String summary;
	public String category;
	public String type;
	public SmallText(){}
	public SmallText(String id, String title, String summary, String category,
			String type) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.category = category;
		this.type = type;
	}
}
