package poc.backend.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import poc.backend.dao.AccountDao;
import poc.backend.dao.AccountDao.AlreadyExists;
import poc.backend.dto.Account;
import poc.backend.dto.Result;

@Path("account")
public class NewAccount {

	@PUT
	@Path("newaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result newAccount (Account  account) {
		System.out.println("Consumed json object is : " + account.login+" "+account.password);

		//TODO verification à faire

		try {
			boolean b = AccountDao.create(account);
			if(b){
				return Result.OK;
			}
		} catch (AlreadyExists e) {
			e.printStackTrace();
			return new Result (Result.STATUS_KO, "alreadyExists");
		}
		return Result.KO;
	}

	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Result account (@QueryParam("login") String login, @QueryParam("password") String password) {
		Account account = AccountDao.get(login, password);
		if(account != null){
			return new Result (Result.STATUS_OK, account);
		}
		return Result.KO;
	}

}
