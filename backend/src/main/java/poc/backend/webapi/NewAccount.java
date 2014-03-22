package poc.backend.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import poc.backend.db.DbConnexion;
import poc.backend.dto.Account;

@Path("account")
public class NewAccount {

	@PUT
	@Path("newaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String newAccount (Account  message) {
        System.out.println("Consumed json object is : " + message.getLogin()+" "+message.getPassword());
        
        //TODO verification à faire
        Jongo jongo = new DbConnexion().connexion();
        MongoCollection accounts = jongo.getCollection("accounts");
        accounts.save(message);
        return "OK";
    }
	
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String account (Account  message) {
        System.out.println("Consumed json object is : " + message.getLogin()+" "+message.getPassword());
        return "OK";
    }

}
