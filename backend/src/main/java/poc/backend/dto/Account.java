package poc.backend.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;


@XmlRootElement 
public class Account {
	
	@Id
	@ObjectId
	public String id;
	public String login;
	public String password;
	public String birthyear;
	public char gender;
	public Account(){}
	public Account(String login, String password, String birthyear,
			char gender) {
		super();
		this.login = login;
		this.password = password;
		this.birthyear = birthyear;
		this.gender = gender;
	}
	
	
}
