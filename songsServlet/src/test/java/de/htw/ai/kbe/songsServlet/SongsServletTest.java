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
    public void doGetAllTest() throws IOException {
    		request.addParameter("all", "");
    		songsServlet.doGet(request, response);
    		
    		String json_test = new ObjectMapper().writeValueAsString(songMap).trim();
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals("application/json", response.getContentType());
    		assertEquals(textResponse, json_test);
    }
    
    @Test
    public void doGetIdTest() throws IOException {
    		request.addParameter("songId", "6");
    		songsServlet.doGet(request, response);
    		
    		String json_test = "{\"title\":\"I Took a Pill in Ibiza\",\"artist\":\"Mike Posner\",\"album\":\"At Night, Alone.\",\"released\":2016}";
    		String textResponse = response.getContentAsString().trim();
    		
    		assertEquals("application/json", response.getContentType());
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
