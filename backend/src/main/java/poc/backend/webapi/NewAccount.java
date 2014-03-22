package poc.backend.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import poc.backend.dto.Account;

@Path("account")
public class NewAccount {

	@PUT
	@Path("newaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String consumeJSONObject (Account  message) {
        System.out.println("Consumed json object is : " + message.getLogin()+" "+message.getPassword());
        return "OK";
    }

}
