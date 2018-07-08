package de.htwBerlin.ai.kbe.storage;

import de.htwBerlin.ai.kbe.data.User;

/**
 * Interface for User DAO
 *
 */
public interface InterfaceUserDAO {

	/**
	 * @param userid
	 * @return
	 */
	public User findUserByUserId(String userid);

}
