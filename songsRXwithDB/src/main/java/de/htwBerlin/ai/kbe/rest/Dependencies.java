package de.htwBerlin.ai.kbe.rest;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.storage.DBSongListsDAO;
import de.htwBerlin.ai.kbe.storage.DBSongsDAO;
import de.htwBerlin.ai.kbe.storage.DBUserDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceSongListsDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceSongsDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceUserDAO;

public class Dependencies extends AbstractBinder {
	@Override
	protected void configure() {
		bind(Persistence.createEntityManagerFactory("SongListDB")).to(EntityManagerFactory.class);
		bind(DBSongsDAO.class).to(InterfaceSongsDAO.class).in(Singleton.class);
		bind(DBSongListsDAO.class).to(InterfaceSongListsDAO.class).in(Singleton.class);
		bind(AuthBox.class).to(InterfaceAuthContainer.class).in(Singleton.class);
		bind(DBUserDAO.class).to(InterfaceUserDAO.class).in(Singleton.class);
	
	}
}
