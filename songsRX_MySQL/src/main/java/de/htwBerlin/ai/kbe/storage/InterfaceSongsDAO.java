package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.data.Song;


/**
 * Interface for SongsDAO
 *
 */
public interface InterfaceSongsDAO {

	/**
	 * @param id
	 * @return
	 */
	public Song findSongById(Integer id);

	/**
	 * @return
	 */
	public Collection<Song> findAllSongs();

	/**
	 * @param song
	 * @param id
	 * @return
	 */
	public static boolean updateSong(Song song, Integer id) {
		return false;
	};

	/**
	 * @param Song
	 * @return
	 */
	public Integer saveSong(Song Song);

	/**
	 * @param id
	 * @return
	 */
	public boolean deleteSong(Integer id);

}
