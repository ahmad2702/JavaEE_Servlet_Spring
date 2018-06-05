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

	private SongsDB() {
		try {
			initializeSongStore(SONGFILENAME);
		} catch (IOException e) {
			System.out.println("Can't create Instance....");
		}
	}

	public synchronized static SongsDB getInstance() {
		if (instance == null) {
			instance = new SongsDB();
		}
		return instance;
	}

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

	public Song getSong(Integer id) {
		return storage.get(id);
	}

	public Collection<Song> getAllSongs() {
		System.out.println(storage);
		System.out.println(songList);

		return storage.values();
	}

	public Integer addSong(Song song) {
		song.setId(currentID.incrementAndGet());
		storage.put(song.getId(), song);
		return song.getId();
	}
	
	/*
	 * true if song exists, false if not exists
	 */
	public boolean updateSong(Song song, Integer id) {
		if (storage.get(id) != null) {
			song.setId(id);
			storage.replace(id, song);
			return true;
		}
		return false;
	}

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