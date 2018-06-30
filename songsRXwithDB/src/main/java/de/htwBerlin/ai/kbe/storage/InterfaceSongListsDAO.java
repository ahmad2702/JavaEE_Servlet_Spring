package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.bean.SongLists;

public interface InterfaceSongListsDAO {

	public SongLists findSongListsById(Integer id);

	public Collection<SongLists> findAllSongLists(String id, boolean isPublic);

	public SongLists findSongListById(String id, Integer songListId, boolean isPublic);
	
	public Integer saveSongLists(SongLists SongLists);

	public boolean deleteSongLists(Integer id);
}
