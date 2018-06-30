package de.htwBerlin.ai.kbe.storage;

import de.htwBerlin.ai.kbe.bean.User;

public interface InterfaceUserDAO {

	public User findUserById(String userid);

}
