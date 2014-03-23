package poc.backend.entity;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;


@XmlRootElement 
public class Text {

	@Id
	@ObjectId
	public String id;

	public Date creationDate;
	public Date modificationDate;
	
	public String title;
	public String summary;
	public String category;
	public String type;
	public String mood;
	public String content;
	
	public String login;
	public Integer timeToRead;

}
