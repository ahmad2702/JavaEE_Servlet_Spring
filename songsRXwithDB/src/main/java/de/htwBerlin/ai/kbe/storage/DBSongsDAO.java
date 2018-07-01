package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import de.htwBerlin.ai.kbe.bean.Song;

@Singleton
public class DBSongsDAO implements InterfaceSongsDAO {

	private EntityManagerFactory entManFact;

	@Inject
	public DBSongsDAO(EntityManagerFactory entManFact) {
		this.entManFact = entManFact;
	}

	@Override
	public Song findSongById(Integer id) {
		EntityManager entMan = entManFact.createEntityManager();
		Song entity = null;
		try {
			entity = entMan.find(Song.class, id);
		} finally {
			entMan.close();
		}
		return entity;
	}

	@Override
	public Collection<Song> findAllSongs() {
		EntityManager entMan = entManFact.createEntityManager();
		try {
			TypedQuery<Song> query = entMan.createQuery("SELECT c FROM Song c", Song.class);
			return query.getResultList();
		} finally {
			entMan.close();
		}
	}

	@Override
	public Integer saveSong(Song Song) throws PersistenceException {
		EntityManager entMan = entManFact.createEntityManager();
		EntityTransaction entTransaction = entMan.getTransaction();
		try {
			entTransaction.begin();
			entMan.persist(Song);
			entTransaction.commit();
			return Song.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error adding Song: " + e.getMessage());
			entTransaction.rollback();
			throw new PersistenceException("No persist entity: " + e.toString());
		} finally {
			entMan.close();
		}
	}

	@Override
	public boolean deleteSong(Integer id) throws PersistenceException {
		EntityManager entMan = entManFact.createEntityManager();
		EntityTransaction entTransaction = entMan.getTransaction();
		Song Song = null;
		try {
			Song = entMan.find(Song.class, id);
			if (Song != null) {
				System.out.println("Deleting: " + Song.getId() + " ");
				entTransaction.begin();
				entMan.remove(Song);
				entTransaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error to remove Song: " + e.getMessage());
			entTransaction.rollback();
			throw new PersistenceException("Could not remove entity: " + e.toString());
		} finally {
			entMan.close();
		}
	}
}
