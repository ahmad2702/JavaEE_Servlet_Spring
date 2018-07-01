package de.htwBerlin.ai.kbe.services;

import java.net.URI;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import de.htwBerlin.ai.kbe.bean.SongLists;
import de.htwBerlin.ai.kbe.rest.InterfaceAuthContainer;
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
	public Collection<SongLists> getAllSongLists(@HeaderParam("Authorization") String token,
			@PathParam("id") String id) {

		System.out.println("getAllSongLists: Returning all SongLists!");
		System.out.println(songListsDAO.findAllSongLists(id, false));
		if (authBox.getUserIdByToken(token).equals(id)) {
			return songListsDAO.findAllSongLists(id, false);
		}
		return songListsDAO.findAllSongLists(id, true);
	}

	@GET
	@Path("/{id}/songLists/{songList_id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response getSongListById(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			@PathParam("songList_id") Integer songListId) {

		System.out.println("getAllSongLists: Returning SongLists by given id !");
		SongLists s;
		if (authBox.getUserIdByToken(token).equals(id)) {
			s = songListsDAO.findSongListById(id, songListId, false);
		} else {
			s = songListsDAO.findSongListById(id, songListId, true);
		}
		if (s == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No SongList found with id " + songListId).build();
		} else {
			return Response.ok(s).build();
		}
	}

	@POST
	@Path("/{id}/songLists/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSongLists(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			SongLists SongLists) throws URISyntaxException {
		System.out.println(SongLists);
		if (authBox.getUserIdByToken(token).equals(id)
				&& SongLists.getUser().getUserId().equals(authBox.getUserIdByToken(token))) {
			if (SongLists != null && SongLists.getSongs() != null) {
				try {
					int res = songListsDAO.saveSongLists(SongLists);
					return Response.created(new URI("/songsRX/rest/userId/" + id + "/songLists/" + res)).build();
				} catch (Exception e) {
					return Response.status(Response.Status.BAD_REQUEST).entity("Song doenstn't exists ").build();
				}
			}
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized to save for other user ").build();
	}

	@DELETE
	@Path("/{id}/songLists/{list_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			@PathParam("list_id") int list_id) {
		System.out.println(authBox.getUserIdByToken(token));
		System.out.println(id);
		if (authBox.getUserIdByToken(token).equals(id)) {
			if (songListsDAO.deleteSongLists(list_id)) {
				return Response.status(Response.Status.NO_CONTENT).entity("Sucessfully deleted SongLists").build();
			} else {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("Can't delete this SongLists. SongLists doesn't exists").build();
			}
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized to delete  other users playlist ")
				.build();
	}
}
