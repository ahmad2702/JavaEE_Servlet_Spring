package de.htwBerlin.ai.kbe.storage;

/**
 * Interface for AuthContainer
 *
 */
public interface InterfaceAuthContainer {

	/**
	 * @param authToken
	 * @return
	 */
	public boolean authenticate(String authToken);

	/**
	 * @param token
	 * @return
	 */
	public String getUserIdByToken(String token);

	/**
	 * @param token
	 * @param userId
	 * @return
	 */
	public String setUserIdByToken(String token, String userId);

	/**
	 * @param token
	 * @param integer
	 */
	public void setIDByToken(String token, Integer integer);

	/**
	 * @param token
	 * @return
	 */
	public Integer getIDbyToken(String token);

}
