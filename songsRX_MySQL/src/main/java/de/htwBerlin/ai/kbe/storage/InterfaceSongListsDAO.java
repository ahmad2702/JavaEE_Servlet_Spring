package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.ws.rs.core.GenericEntity;

import de.htwBerlin.ai.kbe.data.SongLists;

public interface InterfaceSongListsDAO {

	public SongLists findSongListsById(Integer id);

	public GenericEntity<Collection<SongLists>> findAllSongLists(String id, boolean isPublic);

	public GenericEntity<SongLists> findSongListById(String id, Integer songListId);
	
	public Integer saveSongLists(SongLists SongLists);

	public String deleteSongLists(String userId, Integer list_id);
}
