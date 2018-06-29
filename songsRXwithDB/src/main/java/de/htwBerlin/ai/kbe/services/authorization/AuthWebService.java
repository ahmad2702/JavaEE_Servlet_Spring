package de.htwBerlin.ai.kbe.services.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.bean.User;
import de.htwBerlin.ai.kbe.storage.UserDB;

/**
 * class AuthWebService
 *
 */
@Path("/auth")
public class AuthWebService {

	// @Inject
	private AuthBox authContainer = new AuthBox();

	@Context
	HttpServletRequest request;

	/**
	 * Response getSong
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getSong(@QueryParam("userId") String userId) {

		User user = UserDB.getInstance().getUser(userId);

		if (user != null) {
			String token = request.getSession().getId();
			authContainer.setUserIdByToken(token, userId);
			return Response.status(200).entity("Your token is: " + token).build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("No user found with id: " + userId).build();
		}
	}

	/**
	 * getUserIdByToken
	 * 
	 * @param token
	 * @return
	 */
	public String getUserIdByToken(String token) {
		return authContainer.getUserIdByToken(token);
	}

	/**
	 * authenticate
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean authenticate(String authToken) {
		String userId = authContainer.getUserIdByToken(authToken);
		if (userId != null) {
			return true;
		}
		return false;
	}

}