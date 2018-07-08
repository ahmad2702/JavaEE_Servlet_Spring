package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.ws.rs.core.GenericEntity;

import de.htwBerlin.ai.kbe.data.SongLists;

/**
 * Interface for SongListsDAO
 *
 */
public interface InterfaceSongListsDAO {

	/**
	 * @param id
	 * @return
	 */
	public SongLists findSongListsById(Integer id);

	/**
	 * @param id
	 * @param isPublic
	 * @return
	 */
	public GenericEntity<Collection<SongLists>> findAllSongLists(String id, boolean isPublic);

	/**
	 * @param id
	 * @param songListId
	 * @return
	 */
	public GenericEntity<SongLists> findSongListById(String id, Integer songListId);
	
	/**
	 * @param SongLists
	 * @return
	 */
	public Integer saveSongLists(SongLists SongLists);

	/**
	 * @param userId
	 * @param list_id
	 * @return
	 */
	public String deleteSongLists(String userId, Integer list_id);
}
