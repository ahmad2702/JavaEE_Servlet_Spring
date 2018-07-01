package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.data.Song;


public interface InterfaceSongsDAO {

	public Song findSongById(Integer id);

	public Collection<Song> findAllSongs();

	public static boolean updateSong(Song song, Integer id) {
		return false;
	};

	public Integer saveSong(Song Song);

	public boolean deleteSong(Integer id);

}
