package de.htwBerlin.ai.kbe.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.htwBerlin.ai.kbe.bean.User;

public class UserDB {

	private static Map<String, User> storage;
	private static UserDB instance = null;
	private final String USERFILENAME = "user.json";
	private List<User> userList = new ArrayList<User>();

	private UserDB() {
		try {
			initializeUserStore(USERFILENAME);
		} catch (IOException e) {
			System.out.println("Can't create Instance....");
		}
	}

	public synchronized static UserDB getInstance() {
		if (instance == null) {
			instance = new UserDB();
		}
		return instance;
	}

	private void initializeUserStore(String userFilename) throws IOException {

		if (userFilename == null || userFilename.equals("")) {
			userFilename = "user.json";
		}
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(userFilename);
		userList = new ObjectMapper().readValue(input, new TypeReference<ArrayList<User>>() {
		});
		storage = new ConcurrentHashMap<String, User>();
		userList.stream().sorted((entry_1, entry_2) -> entry_1.getId().compareTo(entry_2.getId()))
				.forEach(entry -> storage.put(entry.getUserId(), entry));

	}

	public User getUser(String id) {
		return storage.get(id);
	}

	public Collection<User> getAllUsers() {
		return storage.values();
	}
}
