package de.htwBerlin.ai.kbe.api;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.data.SongLists;
import de.htwBerlin.ai.kbe.storage.InterfaceAuthContainer;
import de.htwBerlin.ai.kbe.storage.InterfaceSongListsDAO;

//http://localhost:8080/songsRX/rest/userId 
@Path("/userId")
public class SongListsWebService {

	private InterfaceSongListsDAO songListsDAO;
	@Inject
	private InterfaceAuthContainer authBox;

	@Inject
	public SongListsWebService(InterfaceSongListsDAO dao) {
		this.songListsDAO = dao;
	}

	@GET
	@Path("/{id}/songLists")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllSongLists(@HeaderParam("Authorization") String token, @PathParam("id") String id) {
		GenericEntity<Collection<SongLists>> list = null;

		if (authBox.getUserIdByToken(token).equals(id)) {

			list = songListsDAO.findAllSongLists(id, true);
			if (list == null) {
				Response.status(Response.Status.NOT_FOUND).build();
			}

		} else {

			list = songListsDAO.findAllSongLists(id, false);
			if (list == null) {
				Response.status(Response.Status.NOT_FOUND).build();
			}
		}

		return Response.ok(list).build();
	}

	@GET
	@Path("/{id}/songLists/{songList_id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSongListById(@HeaderParam("Authorization") String token, @PathParam("id") String userId,
			@PathParam("songList_id") Integer songListId) {

		GenericEntity<SongLists> songs;

		songs = songListsDAO.findSongListById(authBox.getUserIdByToken(token), songListId);

		if (songs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(songs).build();

	}

	@POST
	@Path("/{id}/songLists/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSongLists(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			SongLists songLists) throws URISyntaxException {

		if (authBox.getUserIdByToken(token).equals(id)
				&& songLists.getUser().getUserId().equals(authBox.getUserIdByToken(token))
				&& songLists.getUser().getId() == authBox.getIDbyToken(token)) {
			Response response = null;
			if (songLists != null && songLists.getSongs() != null) {
				try {
					int res = songListsDAO.saveSongLists(songLists);
					response = Response.ok("/songsRXwithDB/rest/userId/" + id + "/songLists/" + res).build();
				} catch (Exception e) {
					response = Response.status(Response.Status.BAD_REQUEST).entity("Song doenstn't exists ").build();
				}
			}
			return response;
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized to save for other user ")
					.build();
		}

	}

	@DELETE
	@Path("/{id}/songLists/{list_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			@PathParam("list_id") int list_id) {
		System.out.println(authBox.getUserIdByToken(token));
		System.out.println(id);
		if (authBox.getUserIdByToken(token).equals(id)) {
			String result = songListsDAO.deleteSongLists(authBox.getUserIdByToken(token), list_id);
			if (result.equals("ok")) {
				return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully deleted SongLists").build();
			} else if (result.equals("not_found")) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("Can't delete this SongLists. SongLists is not exists").build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity("Not authorized to delete other users from playlist ").build();
			}
		} else {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("Not authorized to delete other users from playlist ").build();
		}

	}
}
