package de.htwBerlin.ai.kbe.storage;

import de.htwBerlin.ai.kbe.data.User;

public interface InterfaceUserDAO {

	public User findUserByUserId(String userid);

}
