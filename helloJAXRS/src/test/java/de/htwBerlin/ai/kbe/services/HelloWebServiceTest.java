package de.htwBerlin.ai.kbe.services;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class HelloWebServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(HelloWebService.class);
    }

    @Test
    public void getMsgPathParamTest() {
        String response = target("/hello/YOU").request().get(String.class);
        System.out.println(response);
        Assert.assertTrue(response.contains("YOU"));
    }
}
