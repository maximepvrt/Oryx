package poc.backend.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import poc.backend.dao.RatingDao;
import poc.backend.dto.RatingDto;
import poc.backend.dto.Result;

@Path("shortstory")
public class ShortstoryAPI {

	@PUT
	@Path("rating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result rating (RatingDto  ratingDto) {
		boolean b = RatingDao.create(ratingDto);
		if(b){
			return Result.OK;
		}
		return Result.KO;
	}

}
