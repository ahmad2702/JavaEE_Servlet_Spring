package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.data.Contact;

public interface ContactsDAO {

    /**
     * Retrieves a contact
     * 
     * @param id
     * @return
     */
    public Contact findContactById(Integer id);

    /**
     * Retrieves all contacts
     * 
     * @return
     */
    public Collection<Contact> findAllContacts();

    /**
     * Save a new contact
     * 
     * @param contact
     * @return the Id of the new contacts
     */
    public Integer saveContact(Contact contact);
    
    /**
     * Deletes the contact for the provided id
     * @param id
     */
    public void deleteContact(Integer id);
}
