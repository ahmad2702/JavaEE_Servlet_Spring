package de.htwBerlin.ai.kbe.services.authorization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthBox {

	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	/**
	 * authenticate
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean authenticate(String authToken) {
		return tokenMap.containsKey(authToken);
	}

	/**
	 * getUserIdByToken
	 * 
	 * @param token
	 * @return
	 */
	public String getUserIdByToken(String token) {
		return tokenMap.get(token);
	}

	/**
	 * setUserIdByToken
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	public String setUserIdByToken(String token, String userId) {
		return tokenMap.put(token, userId);
	}

}