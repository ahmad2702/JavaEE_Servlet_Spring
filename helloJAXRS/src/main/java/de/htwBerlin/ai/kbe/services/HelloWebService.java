package de.htwBerlin.ai.kbe.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/* 
 * URL zu unserem Webservice ist http://localhost:8080/helloJAXRS/rest/hello/IRGENDWAS
 * 
 * /helloJAXRS ist die Webapplikation
 * /helloJAXRS/rest bildet auf das Jersey-Servlet "ServletContainer" ab (konfiguriert im web.xml). Das macht Tomcat.
 * @Path("/hello") bedeutet "/helloJAXRS/rest/hello" auf HelloWebService.java abbilden. Das macht Jersey.
 *
 * @Path("/{name}") und getMsg(@PathParam("name") bedeutet: 
 * /helloJAXRS/rest/hello/JAX auf HelloWebService.getMsg() abbilden 
 * und "JAX" an die Methode getMsg() uebergeben. Das macht auch Jersey.
 */

@Path("/hello")
public class HelloWebService {

	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_HTML)
	public Response getMsg(@PathParam("name") String name) {
		String output = "<html><title>Our first JAX-RS Web Service</title>"  + 
						"<body><h1>" +
						"Hello <span style='color: red;'>" + name + "</span>"
						+ "</h1></body></html>";
		return Response.status(Response.Status.OK).entity(output).build();
	}
}