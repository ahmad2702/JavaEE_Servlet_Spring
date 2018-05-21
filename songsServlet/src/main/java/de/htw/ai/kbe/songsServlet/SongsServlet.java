package de.htw.ai.kbe.songsServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class SongsServlet use Servlet-API. This Webservice has an access to
 * in-Memory music library (songs.json). Supported HTTP-Methods: GET and POST
 *
 */
@WebServlet(name = "SongsServlet", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "songFile", value = "songs.json") })

public class SongsServlet extends HttpServlet {

	private Map<Integer, Song> songMap = null;
	private AtomicInteger idForNow = null;
	private String fileOfSongs = null;
	private static final long serialVersionUID = 1L;
	private static final String FORMAT_JSON = "application/json";
	private static final String FORMAT_XML = "application/xml";
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
			System.out.println("Json is not readable and can not be validated for Song: " + e);
		}
		idForNow = new AtomicInteger(songMap.size());
		// System.out.println("Initialisation ID: " + idForNow);
	}

	/**
	 * initializeSongStore loads a file.json with songs for each lists create ID
	 * 
	 * @throws IOException
	 */
	public synchronized void initSongsServlet(String fileOfSongs) throws IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileOfSongs);
		List<Song> songList = new ObjectMapper().readValue(input, new TypeReference<List<Song>>() {
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

		String formatOfRequest = request.getHeader("accept");

		if (formatOfRequest == null || (!formatOfRequest.equals(FORMAT_JSON) && !formatOfRequest.equals(FORMAT_XML))) {
			response.setContentType(FORMAT_TEXT);
			response.getWriter().println("Header error: 'Accept' kann nicht initialisiert werden!");
			return; // here like a breakpoint
		}

		response.setContentType(formatOfRequest + "; charset=" + TEXT_CODIERUNG);
		response.setCharacterEncoding(TEXT_CODIERUNG);

		try (PrintWriter outputPrinter = response.getWriter()) {
			if (parametersMap.size() == 1) {
				if (parametersMap.get("all") != null && parametersMap.get("all").equals("")) {
					String out = "";

					if (formatOfRequest.equals(FORMAT_JSON)) {
						out = new ObjectMapper().writeValueAsString(songMap.values());

					} else if (formatOfRequest.equals(FORMAT_XML)) {
						Songs songs = new Songs();
						List<Song> list = new ArrayList<Song>(songMap.values());
						songs.setSongs(list);
						out = Converter.getXmlFromSongs(songs);
					} else {
						out = "Format wird nicht akzeptiert!";
					}
					response.setContentType(formatOfRequest);
					outputPrinter.println(out);
				}

				else if (parametersMap.get("songId") != null) {
					String value = parametersMap.get("songId");

					try {
						int valueInt = Integer.parseInt(value);

						if (songMap.get(valueInt) != null) {
							String out = "";

							if (formatOfRequest.equals(FORMAT_JSON)) {
								out = new ObjectMapper().writeValueAsString(songMap.get(valueInt));
							} else {
								Songs songs = new Songs();
								Song song = songMap.get(valueInt);
								songs.setSong(song);
								out = Converter.getXmlFromSongs(songs);
							}

							response.setContentType(formatOfRequest);
							outputPrinter.println(out);
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
		} catch (JAXBException e) {
			e.printStackTrace();
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

					String formatOfAccept = request.getHeader("accept");
					String formatOfContentType = request.getContentType();
					String out = "";

					if (formatOfContentType.equals(FORMAT_JSON)) {
						Song reqSong = new ObjectMapper().readValue(req, Song.class);
						reqSong.setId(idForNow.incrementAndGet());
						songMap.put(idForNow.get(), reqSong);
						out = "ID for new song:  " + idForNow;

					} else if (formatOfContentType.equals(FORMAT_XML)) {
						Songs songs = Converter.getSongsFromXmlString(req);
						Song reqSong = songs.getSongs().get(0);
						reqSong.setId(idForNow.incrementAndGet());
						songMap.put(idForNow.get(), reqSong);
						out = "ID for new song:  " + idForNow;

					} else {
						out = "Format wird nicht akzeptiert!!!";
					}
					response.setContentType(formatOfAccept);
					outputPrinter.println(out);

				} catch (JsonParseException e) {
					outputPrinter.println("You have tried to send not correctly (json) validated song");
				} catch (JAXBException e) {
					outputPrinter.println("You have tried to send not correctly (xml) validated song");
				}
			}
		}
	}

	public String getFileOfSongs() {
		return fileOfSongs;
	}

}
