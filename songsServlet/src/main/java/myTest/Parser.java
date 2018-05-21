package myTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.htw.ai.kbe.songsServlet.Song;
import de.htw.ai.kbe.songsServlet.SongStructure;
import de.htw.ai.kbe.songsServlet.Songs;

public class Parser {
	
	private static final String MY_JSON_FILE = "";
	private Map<Integer, SongStructure> songMap = null;
	private AtomicInteger idForNow = null;

	
	public String getJsonFormat(String fileOfSongs) throws JsonGenerationException, JsonMappingException, IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileOfSongs);
		List<SongStructure> songList = new ObjectMapper().readValue(input, new TypeReference<List<SongStructure>>() {
		
		});
		
		
		idForNow = new AtomicInteger(0);
		
		
		songMap = new HashMap<>();
		songList.stream().forEach(var -> this.songMap.put(idForNow.incrementAndGet(), var));
		
		String out = new ObjectMapper().writeValueAsString(songMap);
		return out;
	}
	
	public Songs getSongsFromXmlString(String xmlText) throws JAXBException, IOException {
		
		InputStream in = IOUtils.toInputStream(xmlText, "UTF-8");

		JAXBContext jaxbContext = JAXBContext.newInstance(Songs.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		Songs songs = (Songs) jaxbUnmarshaller.unmarshal(in);
		
		return songs;
		
	}
	
	public String getXmlFromSongs(Songs songs) throws JAXBException {
		Song song = new Song();
		song.setArtist("sdfsdf");
		song.setTitle("sdfsdf");
		song.setAlbum("sdfsdfsdf");
		song.setReleased("2093");
		
		JAXBContext context = JAXBContext.newInstance(Songs.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        StringWriter sw = new StringWriter();
        marshaller.marshal(songs, sw);
        String xmlString = sw.toString();
		
        return xmlString;
		
		
	}

	
	
	
}
