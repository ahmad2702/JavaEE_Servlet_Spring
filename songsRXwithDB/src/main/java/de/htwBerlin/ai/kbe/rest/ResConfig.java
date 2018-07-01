package de.htwBerlin.ai.kbe.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class ResConfig extends ResourceConfig {
	public ResConfig() {
		register(new Dependencies());
		packages("de.htwBerlin.ai.kbe.services");
		register(AuthReqFilter.class);

	}
}
