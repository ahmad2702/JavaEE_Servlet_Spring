package de.htw.ai.kbe.songsServlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Class SongsServlet use Servlet-API. This Webservice has an access to
 * in-Memory music library (songs.json). Supported HTTP-Methods: GET and POST
 *
 */
@WebServlet(name = "SongsServlet", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "songFile", value = "songs.json") })

public class SongsServlet extends HttpServlet {

	private Map<Integer, SongStructure> songMap = null;
	private AtomicInteger idForNow = null;
	private String fileOfSongs = null;
	private static final long serialVersionUID = 1L;
	private static final String FORMAT_JSON = "application/json";
	private static final String FORMAT_TEXT = "text/plain";
	
	private static final String TEXT_CODIERUNG = "UTF-8";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public synchronized void init(ServletConfig servletConfig) throws ServletException {
		fileOfSongs = servletConfig.getInitParameter("songFile");
		try {
			initSongsServlet(fileOfSongs);
		} catch (IOException e) {
			System.out.println("Json is not readable and can not be validated for SongStructure: " + e);
		}
		idForNow = new AtomicInteger(songMap.size());
		//System.out.println("Initialisation ID: " + idForNow);
	}

	/**
	 * initializeSongStore loads a file.json with songs for each lists create ID
	 * 
	 * @throws IOException
	 */
	public synchronized void initSongsServlet(String fileOfSongs) throws IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileOfSongs);
		List<SongStructure> songList = new ObjectMapper().readValue(input, new TypeReference<List<SongStructure>>() {
		});
		songMap = new HashMap<>();
		songList.stream().forEach(var -> this.songMap.put(var.getId(), var));
	}

	/*
	 * doGet
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/**
		 * Parameters
		 */
		Enumeration<String> parameters = request.getParameterNames();
		Map<String, String> parametersMap = new HashMap<>();
		String param = "";
		while (parameters.hasMoreElements()) {
			param = parameters.nextElement();
			parametersMap.put(param, request.getParameter(param));
		}
		
		response.setContentType(FORMAT_JSON + "; charset=" + TEXT_CODIERUNG);
		response.setCharacterEncoding(TEXT_CODIERUNG);

		try (PrintWriter outputPrinter = response.getWriter()) {
			if (parametersMap.size() == 1) {
				if (parametersMap.get("all") != null && parametersMap.get("all").equals("")) {
					response.setContentType(FORMAT_JSON);
					outputPrinter.println(new ObjectMapper().writeValueAsString(songMap));
				}

				else if (parametersMap.get("songId") != null) {
					String value = parametersMap.get("songId");

					try {
						int valueInt = Integer.parseInt(value);

						if (songMap.get(valueInt) != null) {
							response.setContentType(FORMAT_JSON);
							outputPrinter.println(new ObjectMapper().writeValueAsString(songMap.get(valueInt)));
						} else {
							response.setContentType(FORMAT_TEXT);
							outputPrinter.println("Object can not be found");
						}
					} catch (NumberFormatException e) {
						response.setContentType(FORMAT_TEXT);
						outputPrinter.println("It must be Integer");
					}
				} else {
					response.setContentType(FORMAT_TEXT);
					outputPrinter.println("Parameter name is not correct");
				}

			} else if (parametersMap.size() > 1) {
				response.setContentType(FORMAT_TEXT);
				outputPrinter.println("Please, set only one parameter");
			} else {
				response.setContentType(FORMAT_TEXT);
				outputPrinter.println("Please, set at least one parameter");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public synchronized void destroy() {
		try {
			ObjectMapper objectMap = new ObjectMapper();
			objectMap.enable(SerializationFeature.INDENT_OUTPUT);
			String getPathForFile = this.getClass().getClassLoader().getResource(fileOfSongs).getPath();
			FileOutputStream output = new FileOutputStream(getPathForFile);
			objectMap.writeValue(output, songMap.values());

		} catch (JsonGenerationException e) {
			System.out.println("JsonGenerationException: " + e);
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException: " + e);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException: " + e);
		}

	}

	/*
	 * doPost
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType(FORMAT_TEXT);
		try (PrintWriter outputPrinter = response.getWriter()) {

			String req = request.getReader().lines().reduce("", (key, value) -> key + value);

			if (req.isEmpty()) {
				outputPrinter.println("You couldn't send empty value");

			} else if (req.equals("null")) {
				outputPrinter.println("You couldn't send null value");
			} else {

				try {
					SongStructure reqSong = new ObjectMapper().readValue(req, SongStructure.class);
					reqSong.setId(idForNow.incrementAndGet());
					songMap.put(idForNow.get(), reqSong);
					outputPrinter.println("ID for new song:  " + idForNow);

				} catch (JsonParseException e) {
					outputPrinter.println("You have tried to send not correctly validated song");
				}
			}
		}
	}
	
	public String getFileOfSongs() {
		return fileOfSongs;
	}

	
	
}
