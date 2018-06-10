package de.htwBerlin.ai.kbe.services;

import org.glassfish.jersey.server.ResourceConfig;

import de.htwBerlin.ai.kbe.services.authorization.AuthReqFilter;

/**
 * class ResConfig
 *
 */
public class ResConfig extends ResourceConfig {
	public ResConfig() {
		packages("de.htwBerlin.ai.kbe.services");
		register(AuthReqFilter.class);
	}

}