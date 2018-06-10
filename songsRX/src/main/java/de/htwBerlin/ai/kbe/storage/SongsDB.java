package de.htwBerlin.ai.kbe.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.htwBerlin.ai.kbe.bean.Song;

public class SongsDB {

	private static Map<Integer, Song> storage;
	private static SongsDB instance = null;
	private final String SONGFILENAME = "songs.json";
	private AtomicInteger currentID = null;
	private List<Song> songList = new ArrayList<Song>();

	/**
	 * Init Exception
	 */
	private SongsDB() {
		try {
			initializeSongStore(SONGFILENAME);
		} catch (IOException e) {
			System.out.println("Can't create Instance....");
		}
	}

	/**
	 * getInstance
	 * 
	 * @return instance
	 */
	public synchronized static SongsDB getInstance() {
		if (instance == null) {
			instance = new SongsDB();
		}
		return instance;
	}

	/**
	 * initializeSongStore
	 * 
	 * @param songFilename
	 * @throws IOException
	 */
	private void initializeSongStore(String songFilename) throws IOException {

		if (songFilename == null || songFilename.equals("")) {
			songFilename = "songs.json";
		}
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(songFilename);

		songList = new ObjectMapper().readValue(input, new TypeReference<ArrayList<Song>>() {
		});

		storage = new HashMap<Integer, Song>();

		songList.stream().sorted((entry_1, entry_2) -> entry_1.getId().compareTo(entry_2.getId()))
				.forEach(entry -> storage.put(entry.getId(), entry));

		currentID = new AtomicInteger(Collections.max(storage.keySet()));

	}

	/**
	 * getSong
	 * 
	 * @param id
	 * @return
	 */
	public Song getSong(Integer id) {
		return storage.get(id);
	}

	/**
	 * getAllSongs
	 * 
	 * @return
	 */
	public Collection<Song> getAllSongs() {
		System.out.println(storage);
		System.out.println(songList);

		return storage.values();
	}

	/**
	 * addSong
	 * 
	 * @param song
	 * @return
	 */
	public Integer addSong(Song song) {
		song.setId(currentID.incrementAndGet());
		storage.put(song.getId(), song);
		return song.getId();
	}

	/**
	 * updateSong
	 * 
	 * @param song
	 * @param id
	 * @return true if song exists, false if not exists
	 */
	public boolean updateSong(Song song, Integer id) {
		if (storage.get(id) != null) {
			song.setId(id);
			storage.replace(id, song);
			return true;
		}
		return false;
	}

	/**
	 * deleteSong
	 * @param id
	 * @return
	 */
	public Song deleteSong(Integer id) {
		Song song = storage.get(id);
		if (song != null) {
			storage.remove(id);
			return song;
		} else {
			return song;
		}
	}
}