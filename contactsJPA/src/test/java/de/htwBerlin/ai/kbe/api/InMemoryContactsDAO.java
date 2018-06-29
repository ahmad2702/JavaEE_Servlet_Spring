package de.htwBerlin.ai.kbe.api;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.htwBerlin.ai.kbe.data.Address;
import de.htwBerlin.ai.kbe.data.Contact;
import de.htwBerlin.ai.kbe.storage.ContactsDAO;

public class InMemoryContactsDAO implements ContactsDAO {

    private static Map<Integer, Contact> storage;

    public InMemoryContactsDAO() {
        storage = new ConcurrentHashMap<Integer, Contact>();
        init();
    }

    private static void init() {
        Contact testerContact = new Contact("Alfred", "Tester", "+4917612345678", "as@gmx.de");
        testerContact.setId(1);
        Address testAddress = new Address("Teststr. 1", "Test", "12345", "Germany", testerContact);
        testerContact.getAddressSet().add(testAddress);
        storage.put(testerContact.getId(), testerContact);
    }

    @Override
    public Contact findContactById(Integer id) {
        return storage.get(id);
    }

    @Override
    public Collection<Contact> findAllContacts() {
        return storage.values();
    }

    @Override
    public Integer saveContact(Contact contact) {
        contact.setId((int) storage.keySet().stream().count() + 1);
        storage.put(contact.getId(), contact);
        return contact.getId();
    }
    
    @Override
    public void deleteContact(Integer id) {
        storage.remove(id);
    }
}
