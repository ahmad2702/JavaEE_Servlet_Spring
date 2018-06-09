package de.htwBerlin.ai.kbe.services.authorization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthBox {

	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	public boolean authenticate(String authToken) {
		return tokenMap.containsKey(authToken);
	}

	public String getUserIdByToken(String token) {
		return tokenMap.get(token);
	}

	public String setUserIdByToken(String token, String userId) {
		return tokenMap.put(token, userId);
	}

}