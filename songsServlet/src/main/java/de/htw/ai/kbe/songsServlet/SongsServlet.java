package de.htw.ai.kbe.songsServlet;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class SongsServlet uses Servlet-API. This Webservice has an access to
 * in-Memory music bibliothek (songs.json)
 * HTTP-Methods: GET and POST
 *
 */
@WebServlet(name = "SongsServlet", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "songFile", value = "songs.json") })

public class SongsServlet extends HttpServlet {

	private Map<Integer, SongStructure> songMap = null;
	private AtomicInteger idForNow = null;
	private String fileOfSongs = null;
	private static final long serialVersionUID = 1L;
	private static final String APPLICATION_JSON = "application/json";
	private static final String TEXT_PLAIN = "text/plain";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		fileOfSongs = servletConfig.getInitParameter("songFile");
		try {
			initSongsServlet();
		} catch (IOException e) {
			System.out.println("Json is not readable and can not be validated for SongStructure: " + e);
		}
		idForNow = new AtomicInteger(songMap.size());
		System.out.println("Initialisation ID: " + idForNow);
	}

	/**
	 * initializeSongStore loads a file.json with songs for each lists create ID
	 * 
	 * @throws IOException
	 */
	public void initSongsServlet() throws IOException {

		if (fileOfSongs == null) {
			fileOfSongs = "songs.json";
		}
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

		try (PrintWriter outputPrinter = response.getWriter()) {
			if (parametersMap.size() == 1) {
				if (parametersMap.get("all") != null && parametersMap.get("all").equals("")) {
					response.setContentType(APPLICATION_JSON);
					outputPrinter.println(new ObjectMapper().writeValueAsString(songMap));
				}

				else if (parametersMap.get("songId") != null) {

					String value = parametersMap.get("songId");

					try {
						int valueInt = Integer.parseInt(value);

						if (songMap.get(valueInt) != null) {
							response.setContentType(APPLICATION_JSON);
							outputPrinter.println(new ObjectMapper().writeValueAsString(songMap.get(valueInt)));
						} else {
							response.setContentType(TEXT_PLAIN);
							outputPrinter.println("Object can not be found");
						}
					} catch (NumberFormatException e) {
						response.setContentType(TEXT_PLAIN);
						outputPrinter.println("It must be Integer");
					}
				} else {
					response.setContentType(TEXT_PLAIN);
					outputPrinter.println("Parameter name is not correct");
				}

			} else if (parametersMap.size() > 1) {
				response.setContentType(TEXT_PLAIN);
				outputPrinter.println("Please, set only one parameter");
			} else {
				response.setContentType(TEXT_PLAIN);
				outputPrinter.println("Please, set at least one parameter");
			}
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType(TEXT_PLAIN);
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
					// System.out.println(reqSong.getId());
					songMap.put(idForNow.get(), reqSong);
					outputPrinter.println("ID for new song:  " + idForNow);

				} catch (JsonParseException e) {
					outputPrinter.println("You have tried to send not correctly validated song");
				}
			}
		}
	}
}
