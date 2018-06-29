package de.htwBerlin.ai.kbe.config;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.storage.ContactsDAO;
import de.htwBerlin.ai.kbe.storage.DBContactsDAO;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind (Persistence
                .createEntityManagerFactory("contactsDB-PU"))
                .to(EntityManagerFactory.class);
        bind(DBContactsDAO.class)
        .to(ContactsDAO.class)
        .in(Singleton.class);
    }
}
