package poc.backend.dto;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement 
public class Account {
	
	private String login;
	private String password;
	private String birthyear;
	private char gender;
	public Account(){}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
}
