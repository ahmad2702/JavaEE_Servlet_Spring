package de.htwBerlin.ai.kbe.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.data.User;
import de.htwBerlin.ai.kbe.storage.InterfaceAuthContainer;
import de.htwBerlin.ai.kbe.storage.InterfaceUserDAO;

//http://localhost:8080/contactsJPA/rest/auth?userId=eschuler
@Path("/auth")
public class AuthWebService {

	private InterfaceAuthContainer authContainer;
	private InterfaceUserDAO userDAO;

	@Inject
	public AuthWebService(InterfaceAuthContainer authContainer, InterfaceUserDAO userDAO) {
		this.authContainer = authContainer;
		this.userDAO = userDAO;
	}

	@Context
	HttpServletRequest request;

	@GET
	@Path("/")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getSong(@QueryParam("userId") String userId) {
		User user = userDAO.findUserByUserId(userId);

		if (user != null) {
			String token = request.getSession().getId();
			System.out.println(authContainer);
			authContainer.setUserIdByToken(token, userId);
			return Response.status(200).entity("Your Token is " + token).build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("No User found with id " + userId).build();
		}
	}

	public String getUserIdByToken(String token) {
		return authContainer.getUserIdByToken(token);
	}

	public boolean authenticate(String authToken) {
		String userId = authContainer.getUserIdByToken(authToken);
		if (userId != null) {
			return true;
		}
		return false;
	}

}

