package de.htwBerlin.ai.kbe.services.authorization;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthReqFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_HEADER = "Authorization";
	// @Inject
	private AuthBox authContainer = new AuthBox();

	@Override
	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
		String authToken = containerRequest.getHeaderString(AUTHENTICATION_HEADER);

		if (authToken == null) {
			// keine Auth
			if (!containerRequest.getUriInfo().getPath().contains("auth")) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		} else {
			// token ist nicht bekannt
			if (!authContainer.authenticate(authToken)) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
	}
}