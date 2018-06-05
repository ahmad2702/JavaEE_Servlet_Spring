package de.htwBerlin.ai.kbe.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthBox {

	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	public boolean authenticate(String authToken) {
		if (tokenMap.containsKey(authToken)) {
			return true;
		}
		return false;
	}

	public String getUserIdByToken(String token) {
		return tokenMap.get(token);
	}

	public String setUserIdByToken(String token, String userId) {
		return tokenMap.put(token, userId);
	}

}