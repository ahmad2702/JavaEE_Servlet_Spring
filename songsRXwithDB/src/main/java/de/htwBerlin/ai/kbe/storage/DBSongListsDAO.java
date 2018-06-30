package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import de.htwBerlin.ai.kbe.bean.SongLists;

@Singleton
public class DBSongListsDAO implements InterfaceSongListsDAO {

	private EntityManagerFactory entManFact;

	@Inject
	public DBSongListsDAO(EntityManagerFactory entManFact) {
		this.entManFact = entManFact;
	}

	@Override
	public SongLists findSongListsById(Integer id) {
		EntityManager entMan = entManFact.createEntityManager();
		SongLists entity = null;
		try {
			entity = entMan.find(SongLists.class, id);
		} finally {
			entMan.close();
		}
		return entity;
	}

	@Override
	public Collection<SongLists> findAllSongLists(String id, boolean isPublic) {
		EntityManager entMan = entManFact.createEntityManager();
		try {

			TypedQuery<SongLists> query = entMan.createQuery("SELECT c FROM SongLists c where user_id = " + "'" + id + "'",
					SongLists.class);
			if (isPublic) {
				query = entMan.createQuery("SELECT c FROM SongLists c where user_id = " + "'" + id + "' and isPublic=1 ",
						SongLists.class);
			}
			return query.getResultList();
		} finally {
			entMan.close();
		}
	}

	@Override
	public SongLists findSongListById(String id, Integer songListId, boolean isPublic) {
		EntityManager entMan = entManFact.createEntityManager();
		try {

			TypedQuery<SongLists> query = entMan.createQuery(
					"SELECT c FROM SongLists c where user_id = " + "'" + id + "' and id=" + songListId,
					SongLists.class);
			if (isPublic) {
				query = entMan.createQuery("SELECT  c FROM SongLists c where user_id = " + "'" + id
						+ "' and isPublic=1 and id=" + songListId, SongLists.class);
			}
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			entMan.close();
		}
	}

	@Override
	public Integer saveSongLists(SongLists SongLists) throws PersistenceException {
		EntityManager entMan = entManFact.createEntityManager();
		EntityTransaction entTransaction = entMan.getTransaction();
		try {
			entTransaction.begin();
			entMan.persist(SongLists);
			entTransaction.commit();
			return SongLists.getId();
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			System.out.println("Error adding SongLists: " + e.getMessage());
			entTransaction.rollback();
			throw new PersistenceException("Could not persist entity: " + e.toString());

		} finally {
			entMan.close();
		}
	}

	@Override
	public boolean deleteSongLists(Integer id) {
		EntityManager entMan = entManFact.createEntityManager();
		EntityTransaction entTransaction = entMan.getTransaction();
		SongLists SongLists = null;
		try {
			SongLists = entMan.find(SongLists.class, id);
			if (SongLists != null) {
				System.out.println("Deleting: " + id);
				entTransaction.begin();
				entMan.remove(SongLists);
				entTransaction.commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error removing SongLists: " + e.getMessage());
			entTransaction.rollback();
			return false;
			// throw new PersistenceException("Could not remove entity: " + e.toString());
		} finally {
			entMan.close();
		}
		return true;
	}
}
