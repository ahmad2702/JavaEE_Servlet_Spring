package de.htwBerlin.ai.kbe.config;

import org.glassfish.jersey.server.ResourceConfig;

import de.htwBerlin.ai.kbe.storage.AuthReqFilter;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new DependencyBinder());
        register(AuthReqFilter.class);
        packages(true, "de.htwBerlin.ai.kbe.api");
    }
}
