package de.htwBerlin.ai.kbe.example;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.htwBerlin.ai.kbe.data.Address;
import de.htwBerlin.ai.kbe.data.Contact;

public class ContactsTesterMain {


    public static void main(String[] args) {
        
        // Datei persistence.xml wird automatisch eingelesen, beim Start der Applikation
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactsDB-PU");

        // EntityManager bietet Zugriff auf Datenbank
        EntityManager em = factory.createEntityManager();
        
        try {
            em.getTransaction().begin();
            // Create: neuen Contact anlegen
            
            Contact contact = new Contact("Bobby", "Junior", "123456789", "bobby.junior@gmail.com");
            Address address1 = new Address("Bobby Str 1", "JunoirCity", "56789", "Bobland", contact);
            Address address2 = new Address("New Str 2", "SeniorCity", "12345", "Bobland", contact);
            Set<Address> addressSet = new HashSet<>();
            addressSet.add(address1);
            addressSet.add(address2);
            contact.setAddressSet(addressSet);
            // Wir persistieren nur contact, 
            // wegen cascade=CascadeType.ALL werden auch address1, address 2 persistiert
            em.persist(contact);
            
            // commit transaction
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // EntityManager nach Datenbankaktionen wieder freigeben
            em.close();
            // Freigabe am Ende der Applikation
            factory.close();
        }
    }


}