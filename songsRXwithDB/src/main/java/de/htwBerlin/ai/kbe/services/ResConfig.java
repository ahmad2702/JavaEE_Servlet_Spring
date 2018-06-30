package de.htwBerlin.ai.kbe.services;

import org.glassfish.jersey.server.ResourceConfig;

import de.htwBerlin.ai.kbe.services.authorization.AuthReqFilter;

public class ResConfig extends ResourceConfig {
	public ResConfig() {
		register(new Dependencies());
		packages("de.htwBerlin.ai.kbe.services");
		register(AuthReqFilter.class);

	}
}
