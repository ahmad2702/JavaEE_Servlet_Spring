package de.htwBerlin.ai.kbe.api;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.data.Song;
import de.htwBerlin.ai.kbe.storage.InterfaceSongsDAO;

//http://localhost:8080/contactsJPA/rest/songs 
@Path("/songs")
public class SongWebService {

	private InterfaceSongsDAO songsDAO;

	@Inject
	public SongWebService(InterfaceSongsDAO dao) {
		this.songsDAO = dao;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Collection<Song> getAllSongs() {
		System.out.println("getAllSongs: Returning all Songs!");
		return songsDAO.findAllSongs();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") Integer id) {
		Song song = songsDAO.findSongById(id);
		if (song != null) {
			System.out.println("getsong: Returning song for id " + id);
			return Response.ok(song).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No Song found with id " + id).build();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSong(Song song) {
		System.out.println(song);
		if (song != null && song.getTitle() != null && song.getArtist() != null) {
			int newId = songsDAO.saveSong(song);
			return Response.status(Response.Status.CREATED).entity("New Song Created with " + newId).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Can't create a this Song bad Payload ").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") Integer id) {
		boolean check = songsDAO.deleteSong(id);
		if (check) {
			return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully deleted Song").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Can't delete this Song. Song doesn't exists")
					.build();
		}
	}

}

