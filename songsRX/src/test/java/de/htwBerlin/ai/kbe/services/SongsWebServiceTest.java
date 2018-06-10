package de.htwBerlin.ai.kbe.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import de.htwBerlin.ai.kbe.services.songs.SongWebService;

public class SongsWebServiceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(SongWebService.class);
	}

	@Test
	public void putBadJsonTest() {
		String jsonPayload = "";
		String responseGewartet = "Can't update this song bad payload";

		Response putResponse = target("/songs/1").request().put(Entity.json(jsonPayload));
		int responseStatus = putResponse.getStatus();
		String responseText = putResponse.readEntity(String.class).trim();

		assertTrue(404 == responseStatus);
		assertEquals(responseGewartet, responseText);
	}

	@Test
	public void putBadXmlTest() {
		String xmlPayload = "";
		// String responseGewartet = "Can't update this song bad payload";

		Response putResponse = target("/songs/1").request().put(Entity.xml(xmlPayload));
		int responseStatus = putResponse.getStatus();
		// String responseText = putResponse.readEntity(String.class).trim();

		assertTrue(400 == responseStatus);
		// assertEquals(responseGewartet, responseText);
	}

	@Test
	public void putJsonTest() {
		String jsonPayload = "{\"title\":\"Test\",\"artist\":\"Test\",\"album\":\"Test\",\"released\":1990}";
		String jsonGewartet = "{\"id\":1,\"title\":\"Test\",\"artist\":\"Test\",\"album\":\"Test\",\"released\":1990}";

		Response getByIdResponseBefore = target("/songs/1").request().accept(MediaType.APPLICATION_JSON).get();
		String messageBefore = getByIdResponseBefore.readEntity(String.class);

		Response putResponse = target("/songs/1").request().put(Entity.json(jsonPayload));
		int responseStatus = putResponse.getStatus();

		Response getByIdResponseAfter = target("/songs/1").request().accept(MediaType.APPLICATION_JSON).get();
		String messageAfter = getByIdResponseAfter.readEntity(String.class);

		assertTrue(204 == responseStatus);
		assertEquals(messageAfter, jsonGewartet);
		assertNotEquals(messageBefore, messageAfter);
	}

	@Test
	public void putXmlTest() {
		String xmlPayload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<song>\n" + "	<album>Test 111</album>\n"
				+ "    <artist>Test</artist>\n" + "    <released>1990</released>\n" + "    <title>Test</title>\n"
				+ "</song>";

		String jsonGewartet = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<song>"
				+ "<album>Test 111</album>" + "<artist>Test</artist>" + "<id>1</id>" + "<released>1990</released>"
				+ "<title>Test</title>" + "</song>";

		Response getByIdResponseBefore = target("/songs/1").request().accept(MediaType.APPLICATION_XML).get();
		String messageBefore = getByIdResponseBefore.readEntity(String.class);

		Response putResponse = target("/songs/1").request().put(Entity.xml(xmlPayload));
		int responseStatus = putResponse.getStatus();

		Response getByIdResponseAfter = target("/songs/1").request().accept(MediaType.APPLICATION_XML).get();
		String messageAfter = getByIdResponseAfter.readEntity(String.class);

		assertTrue(204 == responseStatus);
		assertEquals(messageAfter, jsonGewartet);
		assertNotEquals(messageBefore, messageAfter);
	}

}
