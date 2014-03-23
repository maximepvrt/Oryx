package poc.backend.webapi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import poc.backend.dao.AccountDao;
import poc.backend.dao.RatingDao;
import poc.backend.dao.TextDao;
import poc.backend.dto.RatingDto;
import poc.backend.dto.Result;
import poc.backend.dto.SmallText;
import poc.backend.dto.TextDto;
import poc.backend.entity.Account;
import poc.backend.entity.Text;

@Path("shortstory")
public class ShortstoryAPI {

	@PUT
	@Path("rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result rating (@CookieParam("id") String id, RatingDto  ratingDto, @Context HttpServletResponse response) {
		Account account = AccountDao.get(id);
		if(account == null){
			return Result.KO;
		}
		response.addCookie(new Cookie("id", account.id));
		response.addCookie(new Cookie("login", account.login));
		boolean b = RatingDao.create(ratingDto);
		if(b){
			return Result.OK;
		}
		return Result.KO;
	}
	
	@PUT
	@Path("putstory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result putstory (@CookieParam("id") String id, TextDto  textDto, @Context HttpServletResponse response) {
		Account account = AccountDao.get(id);
		if(account == null){
			return new Result (Result.STATUS_OK, "accountNull");
		}
		response.addCookie(new Cookie("id", account.id));
		response.addCookie(new Cookie("login", account.login));
		boolean b = TextDao.saveOrUpdate(textDto, account.id);
		if(b){
			return Result.OK;
		}
		return Result.KO;
	}
	
	@POST
	@Path("findlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Result account (@QueryParam("timeToRead") Long timeToRead, @QueryParam("type") String type, @QueryParam(value = "category") String category) {
		long min = 0L;
		if(timeToRead != null){min = timeToRead/2;}
		long max = 9999L;
		if(timeToRead != null){max = timeToRead+timeToRead/2;}
		List<Text> textList = TextDao.search(min,max, type, category,0,5);
		if(textList != null){
			List<SmallText> smallTextList = new ArrayList<SmallText>();
			for(Text text : textList){
				smallTextList.add(new SmallText(text.title, text.summary, text.category, text.type));
			}
			return new Result (Result.STATUS_OK, smallTextList);
		}
		return Result.KO;
	}
	
	@GET
	@Path("findtext")
	@Produces(MediaType.APPLICATION_JSON)
	public Result account (@QueryParam("id") String id) {
		Text text = TextDao.get(id);
		if(text != null){
			return new Result (Result.STATUS_OK, text);
		}
		return Result.KO;
	}

}
