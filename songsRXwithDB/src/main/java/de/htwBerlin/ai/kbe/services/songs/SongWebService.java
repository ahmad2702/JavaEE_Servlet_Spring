package de.htwBerlin.ai.kbe.services.songs;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.services.authorization.AuthWebService;
import de.htwBerlin.ai.kbe.storage.SongsDB;

/**
 * class SongWebService
 * http://localhost:8080/songsRX/rest/songs
 */
@Path("/songs")
public class SongWebService {

	// @Inject
	@SuppressWarnings("unused")
	private AuthWebService auth = new AuthWebService();

	/**
	 * Response getAllSongs
	 * 
	 * @return getAllSongs
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllSongs() {

		System.out.println("getAllSongs: Returning all Songs!");
		@SuppressWarnings("rawtypes")
		GenericEntity entity = new GenericEntity<Collection<Song>>(SongsDB.getInstance().getAllSongs()) {
		};
		return Response.ok(entity).build();
	}

	/**
	 * Response getSong
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSong(@PathParam("id") Integer id) {

		Song song = SongsDB.getInstance().getSong(id);
		if (song != null) {
			System.out.println("getsong: Returning song for id " + id);
			return Response.ok(song).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No Song found with id " + id).build();
		}
	}

	/**
	 * Response createSong; 
	 * id and released can be null
	 * 
	 * @param song
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSong(Song song) {
		if (song != null && song.getTitle() != null && song.getArtist() != null && song.getAlbum() != null) {
			System.out.println("createsong: Received Song: " + song.toString());
			int idForNewSong = SongsDB.getInstance().addSong(song);
			String urlForNewSong = "http://localhost:8080/songsRX/rest/songs/" + idForNewSong;
			return Response.status(Response.Status.CREATED).entity(urlForNewSong).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Can't create a this Song bad Payload ").build();
		}
	}

	/**
	 * Response updateSong
	 * 
	 * @param id
	 * @param song
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateSong(@PathParam("id") Integer id, Song song) {

		// if client want to update a file ,album name ,title,Artist should be given
		if (song != null && song.getTitle() != null && song.getArtist() != null && song.getAlbum() != null) {
			if (id == song.getId()) {
				boolean check = SongsDB.getInstance().updateSong(song, id);
				if (check) {
					return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully updated Song ").build();
				} else {
					return Response.status(Response.Status.NOT_FOUND)
							.entity("Can't update this song. Song doesn't exists ").build();
				}
			} else {
				Song tmp = SongsDB.getInstance().getSong(id);
				if (tmp != null) {
					SongsDB.getInstance().updateSong(song, id);
					return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully updated Song ").build();
				} else {
					return Response.status(Response.Status.NOT_FOUND)
							.entity("Can't update this song. Song doesn't exists ").build();
				}
			}

		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Can't update this song bad payload ").build();
		}

	}

	/**
	 * Response delete
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") Integer id) {
		Song song = SongsDB.getInstance().deleteSong(id);
		if (song != null) {
			return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully deleted Song").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Can't delete this Song. Song doesn't exists")
					.build();
		}
	}
}