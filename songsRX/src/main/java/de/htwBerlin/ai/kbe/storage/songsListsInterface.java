package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.bean.SongLists;

/**
 * CRD -> implemented all , U not required
 * @author 
 *
 */
public interface songsListsInterface {

	/**
	 * Retrieves a SongLists
	 * 
	 * @param id
	 * @return
	 */
	public SongLists findSongListsById(Integer id);

	/**
	 * Retrieves all SongListss
	 * 
	 * @return
	 */
	public Collection<SongLists> findAllSongLists(String id, boolean isPublic);

	
	/**
	 * Retrieves single SongLists by given id
	 * 
	 * @return
	 */
	public SongLists findSongListById(String id, Integer songListId, boolean isPublic);
	
	/**
	 * Save a new SongLists
	 * 
	 * @param SongLists
	 * @return the Id of the new SongListss
	 */
	public Integer saveSongLists(SongLists SongLists);

	/**
	 * Deletes the SongLists for the provided id
	 * 
	 * @param id
	 * @return true, if successfully delete it
	 */
	public boolean deleteSongLists(Integer id);
}
