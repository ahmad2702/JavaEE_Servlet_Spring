package de.htwBerlin.ai.kbe.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.GenericEntity;

import de.htwBerlin.ai.kbe.data.SongLists;


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
	public GenericEntity<Collection<SongLists>> findAllSongLists(String userId, boolean securityType) {
		GenericEntity<Collection<SongLists>> ret = null;
		EntityManager em = entManFact.createEntityManager();
		List<SongLists> allData = null;
		try {
			TypedQuery<SongLists> query = em.createQuery("select s from SongLists s", SongLists.class);
			if (query.getResultList() != null && query.getResultList().size() > 0) {
				allData = query.getResultList();
			}
		} finally {
			em.close();
		}
		if (allData != null && allData.size() > 0) {
			List<SongLists> selectedData = new ArrayList<SongLists>();
			for (int i = 0; i < allData.size(); i++) {
				if (allData.get(i).getUser().getUserId().equals(userId)) {
					if (securityType) {
						selectedData.add(allData.get(i));
					} else if (allData.get(i).securityType()) {
						selectedData.add(allData.get(i));
					}
				}
			}
			if (selectedData.size() > 0) {
				ret = new GenericEntity<Collection<SongLists>>(selectedData) {};
			}
		}
		return ret;
	}

	@Override
	public GenericEntity<SongLists> findSongListById(String userId, Integer songListId) {
		GenericEntity<SongLists> ret = null;
		EntityManager entMan = entManFact.createEntityManager();
		
		try {
			SongLists songLists = entMan.find(SongLists.class, songListId);
			
			if(songLists.getUser().getUserId().equals(userId)) {
				
				ret = new GenericEntity<SongLists>(entMan.find(SongLists.class, songListId)) {};
				
			} else {
				if(songLists.securityType()) {
					ret = new GenericEntity<SongLists>(entMan.find(SongLists.class, songListId)) {};
				}
			}
		
			return ret;
			
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
	public String deleteSongLists(String userId, Integer list_id) {
		EntityManager entMan = entManFact.createEntityManager();
		EntityTransaction entTransaction = entMan.getTransaction();
		SongLists SongLists = null;
		try {
			SongLists = entMan.find(SongLists.class, list_id);
			
			if (SongLists != null) {
				
				System.out.println(userId + " -> " + SongLists.getUser().getUserId());
				
				if(SongLists.getUser().getUserId().equals(userId)) {
					entTransaction.begin();
					entMan.remove(SongLists);
					entTransaction.commit();
				} else {
					return "forbidden";
				}

			} else {
				return "not_found";
			}
		} catch (Exception e) {
			entTransaction.rollback();
			return "not_found";
		} finally {
			entMan.close();
		}
		return "ok";
	}
}

