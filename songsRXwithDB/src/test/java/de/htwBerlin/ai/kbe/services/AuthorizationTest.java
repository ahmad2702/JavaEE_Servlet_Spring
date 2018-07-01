//package de.htwBerlin.ai.kbe.services;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.Response;
//
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.Ignore;
//import org.junit.Test;
//
//public class AuthorizationTest extends JerseyTest {
//
//	@Override
//	protected Application configure() {
//		return new ResConfig();
//	}
//
//	@Test
//	public void authNegativeTest() {
//		String userID = "sdfsdfsdf";
//		String messageErwartet = "No user found with id: " + userID;
//
//		Response putResponse = target("/auth").queryParam("userId", userID).request().get();
//		int responseStatus = putResponse.getStatus();
//		String responseText = putResponse.readEntity(String.class);
//
//		assertTrue(403 == responseStatus);
//		assertEquals(responseText, messageErwartet);
//	}
//
//	// ?????????????????????
//	@Ignore
//	@Test
//	public void authPositiveTest() {
//		String userID = "mmuster";
//		String messageErwartet = "Your token is: ";
//
//		Response putResponse = target("/auth").queryParam("userId", userID).request().get();
//		int responseStatus = putResponse.getStatus();
//		String responseText = putResponse.readEntity(String.class);
//
//		System.out.println(responseStatus);
//		System.out.println(responseText);
//
//		assertTrue(200 == responseStatus);
//		assertTrue(responseText.contains(messageErwartet));
//	}
//
//	@Test
//	public void getAllSongsWithoutAuthTest() {
//		Response putResponse = target("/songs").request().get();
//		int responseStatus = putResponse.getStatus();
//
//		assertTrue(401 == responseStatus);
//	}
//
//	@Test
//	public void getOneWithoutAuthTest() {
//		Response putResponse = target("/songs/1").request().get();
//		int responseStatus = putResponse.getStatus();
//
//		assertTrue(401 == responseStatus);
//	}
//
//	@Test
//	public void putWithoutAuthTest() {
//		String xmlPayload = "";
//
//		Response putResponse = target("/songs/1").request().put(Entity.xml(xmlPayload));
//		int responseStatus = putResponse.getStatus();
//
//		assertTrue(401 == responseStatus);
//	}
//
//	@Test
//	public void postWithoutAuthTest() {
//		String xmlPayload = "";
//
//		Response putResponse = target("/songs").request().post(Entity.xml(xmlPayload));
//		int responseStatus = putResponse.getStatus();
//
//		assertTrue(401 == responseStatus);
//	}
//
//}
