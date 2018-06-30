package de.htwBerlin.ai.kbe.storage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.htwBerlin.ai.kbe.bean.User;

@Singleton
public class DBUserDAO implements InterfaceUserDAO {

	private EntityManagerFactory entManFact;

	@Inject
	public DBUserDAO(EntityManagerFactory entManFact) {
		this.entManFact = entManFact;
	}

	@Override
	public User findUserById(String userid) {
		EntityManager em = entManFact.createEntityManager();
		User entMan = null;
		try {
			entMan = em.find(User.class, userid);
		} finally {
			em.close();
		}
		return entMan;
	}

}
