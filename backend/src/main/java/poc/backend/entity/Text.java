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
	public List<String> categories;
	public String type;
	public String humor;
	public String content;
	
	public String accountId;
	public long timeToRead;

}
