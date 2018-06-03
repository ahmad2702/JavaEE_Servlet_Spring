package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.htwBerlin.ai.kbe.bean.Address;
import de.htwBerlin.ai.kbe.bean.Contact;

public class AddressBook {

	private static Map<Integer,Contact> storage;
	private static AddressBook instance = null;
	
	private AddressBook() {
		storage = new HashMap<Integer,Contact>();
		initSomeContacts();
	}
	
	public synchronized static AddressBook getInstance() {
		if (instance == null) {
			instance = new AddressBook();
		}
		return instance;
	}
	
	private static void initSomeContacts() {
		Address address1 = new Address.Builder()
				.street("Berliner Str.")
				.city("Berlin")
				.zip("12345")
				.country("Germany").build();
		
		Contact alfread = new Contact.Builder(1,"Alfred")
				.lastName("Schmidt")
				.mobile("+4917612345678")
				.emailAddress("as@gmx.de")
				.address(address1).build();
		
		storage.put(alfread.getId(), alfread);
		
		Address address2 = new Address.Builder()
				.street("Str.")
				.city("London")
				.zip("WC1B 6YR")
				.country("GB").build();
		
		Contact bob = new Contact.Builder(2,"Bob")
				.mobile("+4427712345678")
				.emailAddress("bob@gmx.uk")
				.address(address2).build();
		
		storage.put(bob.getId(), bob);
	}
	
	public Contact getContact(Integer id) {
		return storage.get(id);
	}
	
	public Collection<Contact> getAllContacts() {
		return storage.values();
	}
	
	public Integer addContact(Contact contact) {
		contact.setId((int)storage.keySet().stream().count() + 1);
		storage.put(contact.getId(), contact);
		return contact.getId();
	}
	
	// returns true (success), when contact exists in map and was updated
	// returns false, when contact does not exist in map
	public boolean updateContact(Contact contact) {
		throw new UnsupportedOperationException("updateContact: not yet implemented");
	}
	
	// returns deleted contact
	public Contact deleteContact(Integer id) {
		throw new UnsupportedOperationException("deleteContact: not yet implemented");
	}
}
