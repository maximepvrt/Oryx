package poc.backend.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import poc.backend.checker.Verify;
import poc.backend.dao.AccountDao;
import poc.backend.dao.AccountDao.AlreadyExists;
import poc.backend.dto.Result;
import poc.backend.entity.Account;

@Path("account")
public class AccountAPI {

	@PUT
	@Path("newaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result newAccount (Account  account) {

		account = Verify.verifyAccount(account);
		if(account == null){
			return new Result (Result.STATUS_KO, "badParameters");
		}
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
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Result account (@QueryParam("login") String login, @QueryParam("password") String password) {
		Account account = AccountDao.get(login, password);
		if(account != null){
			return new Result (Result.STATUS_OK, account);
		}
		return Result.KO;
	}

}
