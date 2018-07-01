package de.htwBerlin.ai.kbe.storage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import de.htwBerlin.ai.kbe.data.User;


@Singleton
public class DBUserDAO implements InterfaceUserDAO {

	private EntityManagerFactory entManFact;

	@Inject
	public DBUserDAO(EntityManagerFactory entManFact) {
		this.entManFact = entManFact;
	}

	@Override
	public User findUserByUserId(String userId) {
		EntityManager em = entManFact.createEntityManager();
		User entMan = null;
		try {
			entMan = em.createQuery(
					  "SELECT u from User u WHERE u.userId = :userId", User.class).
					  setParameter("userId", userId).getSingleResult();
		} catch (NoResultException e) {
			//
		}finally {
			em.close();
		}
		return entMan;
	}

}
