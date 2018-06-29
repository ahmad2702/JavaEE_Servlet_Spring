package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import de.htwBerlin.ai.kbe.data.Address;
import de.htwBerlin.ai.kbe.data.Contact;

@Singleton
public class DBContactsDAO implements ContactsDAO {

    private EntityManagerFactory emf;

    @Inject
    public DBContactsDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public Contact findContactById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Contact entity = null;
        try {
            entity = em.find(Contact.class, id);
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public Collection<Contact> findAllContacts() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c", Contact.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Integer saveContact(Contact contact) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // MUST set the contact in every address
            for (Address a:contact.getAddressSet()) {
                a.setContact(contact);
            }
            em.persist(contact);
            transaction.commit();
            return contact.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding contact: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not persist entity: " + e.toString());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteContact(Integer id) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Contact contact = null;
        try {
            contact = em.find(Contact.class, id);
            if (contact != null) {
                System.out.println("Deleting: " + contact.getId() + " with firstName: " + contact.getFirstName());
                transaction.begin();
                em.remove(contact);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing contact: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not remove entity: " + e.toString());
        } finally {
            em.close();
        }
    }
}
