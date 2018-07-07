package de.htwBerlin.ai.kbe.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

@Singleton
public class AuthBox implements InterfaceAuthContainer {

	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();
	private static Map<String, Integer> tokenMapForId = new ConcurrentHashMap<String, Integer>();


	@Override
	public boolean authenticate(String authToken) {
		if (tokenMap.containsKey(authToken)) {
			return true;
		}
		return false;
	}

	@Override
	public String getUserIdByToken(String token) {
		return tokenMap.get(token);
	}

	@Override
	public String setUserIdByToken(String token, String userId) {
		return tokenMap.put(token, userId);
	}

	@Override
	public void setIDByToken(String token, Integer id) {
		tokenMapForId.put(token, id);
	}

	@Override
	public Integer getIDbyToken(String token) {
		return tokenMapForId.get(token);
	}
	
	

}
