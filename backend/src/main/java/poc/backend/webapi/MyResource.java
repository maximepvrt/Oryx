package poc.backend.webapi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import test.Test;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		System.out.println("coucou");
		new Test();
		return "Got it!";
	}

//	@PUT
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getIt(@QueryParam("login") String login, @QueryParam("password") String password) {
//		System.out.println("coucou "+login+" "+password);
//		//	        return "Got it!";
//		return "coucou "+login+" "+password;
//	}

	//    @GET
	//    @Path("{star}")
	//    @Produces(MediaType.TEXT_PLAIN)
	//    public String getIt(@PathParam("star") String star) {
	//    	System.out.println("star "+star);
	//        return "star "+star;
	//    }
}
