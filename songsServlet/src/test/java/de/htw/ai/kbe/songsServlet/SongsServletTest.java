package de.htw.ai.kbe.songsServlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SongsServletTest {
	
	private SongsServlet songsServlet;
	private Map<Integer, SongStructure> songMap = null;
	private String fileOfSongs = "songs.json";
	
	private static final String FORMAT_JSON = "application/json";
	private static final String FORMAT_XML = "application/xml";
	private static final String FORMAT_TEXT = "text/plain";
	
	private MockServletConfig config;
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    @Before
    public void setUp() throws ServletException {
        songsServlet = new SongsServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        config = new MockServletConfig();
        config.addInitParameter("songFile", "songs.json");
        songsServlet.init(config);
        
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileOfSongs);
		List<SongStructure> songList = null;
		try {
			songList = new ObjectMapper().readValue(input, new TypeReference<List<SongStructure>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		songMap = new HashMap<>();
		songList.stream().forEach(var -> this.songMap.put(var.getId(), var));
    }
    
    @Test
    public void initTest() {
    		assertEquals(songsServlet.getFileOfSongs(), fileOfSongs);
    }
    
    @Test
    public void doGetAllInJsonTest() throws IOException {
    		request.addParameter("all", "");
    		request.addHeader("accept", FORMAT_JSON);
    		songsServlet.doGet(request, response);
    		
    		String json_test = new ObjectMapper().writeValueAsString(songMap.values()).trim();
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals(FORMAT_JSON, response.getContentType());
    		assertEquals(textResponse, json_test);
    }
    
    @Test
    public void doGetAllInXmlTest() throws IOException {
    		request.addParameter("all", "");
    		request.addHeader("accept", FORMAT_XML);
    		songsServlet.doGet(request, response);
    		
    		String erwartet = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><songs><song><id>1</id><title>Can’t Stop the Feeling</title><artist>Justin Timberlake</artist><album>Trolls</album><released>2016</released></song><song><id>2</id><title>Mom</title><artist>Meghan Trainor, Kelli Trainor</artist><album>Thank You</album><released>2016</released></song><song><id>3</id><title>Team</title><artist>Iggy Azalea</artist><released>2016</released></song><song><id>4</id><title>Ghostbusters (I'm not a fraid)</title><artist>Fall Out Boy, Missy Elliott</artist><album>Ghostbusters</album><released>2016</released></song><song><id>5</id><title>Bad Things</title><artist>Camila Cabello, Machine Gun Kelly</artist><album>Bloom</album><released>2017</released></song><song><id>6</id><title>I Took a Pill in Ibiza</title><artist>Mike Posner</artist><album>At Night, Alone.</album><released>2016</released></song><song><id>7</id><title>i hate u, i love u</title><artist>Gnash</artist><album>Top Hits 2017</album><released>2017</released></song><song><id>8</id><title>No</title><artist>Meghan Trainor</artist><album>Thank You</album><released>2016</released></song><song><id>9</id><title>Private Show</title><artist>Britney Spears</artist><album>Glory</album><released>2016</released></song><song><id>10</id><title>7 Years</title><artist>Lukas Graham</artist><album>Lukas Graham (Blue Album)</album><released>2015</released></song></songs>";
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals(FORMAT_XML, response.getContentType());
    		assertEquals(textResponse, erwartet);
    }
    
    @Test
    public void doGetIdForJsonTest() throws IOException {
    		request.addParameter("songId", "6");
    		request.addHeader("accept", FORMAT_JSON);
    		songsServlet.doGet(request, response);
    		
    		String json_test = "{\"id\":6,\"title\":\"I Took a Pill in Ibiza\",\"artist\":\"Mike Posner\",\"album\":\"At Night, Alone.\",\"released\":2016}";
    		
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals(FORMAT_JSON, response.getContentType());
    		assertEquals(textResponse, json_test);
    }
    
    @Test
    public void doGetIdForXmlTest() throws IOException {
    		request.addParameter("songId", "6");
    		request.addHeader("accept", FORMAT_XML);
    		songsServlet.doGet(request, response);
    		
    		String json_test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><songs><song><id>6</id><title>I Took a Pill in Ibiza</title><artist>Mike Posner</artist><album>At Night, Alone.</album><released>2016</released></song></songs>";
    		
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals(FORMAT_XML, response.getContentType());
    		assertEquals(textResponse, json_test);
    }
    
    @Test
    public void doGetIdErrorTest() throws IOException {
    		request.addParameter("songId", "9999");
    		songsServlet.doGet(request, response);
    		
    		String message_test = "Object can not be found";
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals("text/plain", response.getContentType());
    		assertEquals(textResponse, message_test);
    }
    
    @Test
    public void doGetParameterErrorTest() throws IOException {
    		request.addParameter("blablabla", "9999");
    		songsServlet.doGet(request, response);
    		
    		String message_test = "Parameter name is not correct";
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals("text/plain", response.getContentType());
    		assertEquals(textResponse, message_test);
    }
    
    @Test
    public void doPostJsonTest() throws IOException {
		String payloadData = "{\"title\":\"Cansdfsdf?t Stop the Feeling\",\"artist\":\"Justin Timberlake\",\"album\":\"Trolls\",\"released\":2016}";

    		request.setContent(payloadData.getBytes());
		songsServlet.doPost(request, response);
		
		String textResponse = response.getContentAsString().trim();
		
		assertTrue(textResponse.contains("ID for new song:  "));
    }
    
    
    
}
