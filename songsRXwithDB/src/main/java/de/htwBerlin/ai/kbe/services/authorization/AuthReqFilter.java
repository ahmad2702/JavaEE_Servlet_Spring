package de.htwBerlin.ai.kbe.services.authorization;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthReqFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_HEADER = "Authorization";
	private InterfaceAuthContainer authContainer;

	@Inject
	public AuthReqFilter(InterfaceAuthContainer authContainer) {
		this.authContainer = authContainer;
	}

	@Override
	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {

		String authToken = containerRequest.getHeaderString(AUTHENTICATION_HEADER);

		if (authToken == null) {
			if (!containerRequest.getUriInfo().getPath().contains("auth")) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		} else {
			if (!authContainer.authenticate(authToken)) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
	}
}
