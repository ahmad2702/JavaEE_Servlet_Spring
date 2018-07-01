package de.htwBerlin.ai.kbe.config;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.storage.AuthBox;
import de.htwBerlin.ai.kbe.storage.DBSongListsDAO;
import de.htwBerlin.ai.kbe.storage.DBSongsDAO;
import de.htwBerlin.ai.kbe.storage.DBUserDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceAuthContainer;
import de.htwBerlin.ai.kbe.storage.InterfaceSongListsDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceSongsDAO;
import de.htwBerlin.ai.kbe.storage.InterfaceUserDAO;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind (Persistence.createEntityManagerFactory("songsServlet-DB")).to(EntityManagerFactory.class);
        bind(AuthBox.class).to(InterfaceAuthContainer.class).in(Singleton.class);
        bind(DBUserDAO.class).to(InterfaceUserDAO.class).in(Singleton.class);
        bind(DBSongsDAO.class).to(InterfaceSongsDAO.class).in(Singleton.class);
		bind(DBSongListsDAO.class).to(InterfaceSongListsDAO.class).in(Singleton.class);
        
    }
}
