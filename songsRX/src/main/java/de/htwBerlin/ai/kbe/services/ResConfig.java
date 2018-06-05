package de.htwBerlin.ai.kbe.services;

import org.glassfish.jersey.server.ResourceConfig;

public class ResConfig extends ResourceConfig {
	public ResConfig() {
		packages("de.htwBerlin.ai.kbe.services");
		register(AuthReqFilter.class);
	}
}