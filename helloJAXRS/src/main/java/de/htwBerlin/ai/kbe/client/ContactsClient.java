package de.htwBerlin.ai.kbe.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.bean.Contact;

public class ContactsClient {

	private static Client client = ClientBuilder.newClient();
	private static WebTarget baseTarget = client
			.target("http://localhost:8080/helloJAXRS/rest/contacts");

	public static void main(String[] args) {
//		listContacts();
//		getContact(1);
		createContacts();
		listContacts();

		// Contact contact = getContact(1);
		// contact.setLastName("NEWLASTNAME");
		// updateContact(contact);
		// deleteContactById(3, 4);
	}

	private static void listContacts() {
		System.out.println("------- Getting all contacts:");
		
		// Antwort als JSON-String 
		String response = baseTarget.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println("JSON-Contacts received: " + response);
				
		// Antwort gleich in eine Liste von Contact-POJOs einlesen lassen
		List<Contact> contacts = baseTarget.request()
				.get(new GenericType<List<Contact>>() {});
		contacts.forEach(System.out::println);
	}

	private static Contact getContact(long id) {
		System.out.printf("------- Getting contact with id: %s\n", id);
		
		// Antwort als XML-String 
		String response = baseTarget
				.path(Long.toString(id))
				.request(MediaType.APPLICATION_XML)
				.get(String.class);
		System.out.println("XML-Contact received: " + response);
		
		// Antwort gleich in das Contact-POJO einlesen lassen
		Contact contact = baseTarget
				.path(Long.toString(id))
				.request(MediaType.APPLICATION_XML)
				.get(Contact.class);
		System.out.println("Contact retrieved: " + contact);
		return contact;
	}

	private static void createContacts() {
		System.out.println("------- Creating or posting contacts:");
		for (int i = 1; i <= 5; i++) {
			Contact contact = new Contact();
			contact.setFirstName("FirstName " + i);
			contact.setLastName("LastName " + i);
			Response response;
			Entity<Contact> entity;
			if (i%2 == 0) {
				entity = Entity.xml(contact);
			} else {
				entity = Entity.json(contact);
			}
			System.out.println("Posting new contact: " + 
					entity.toString());
			
			response = baseTarget.request().post(entity);
			System.out.println("Created contact: " 
					+ response.getStatus() + " id =" 
					+ response.readEntity(String.class));
		}
	}

//	private static void updateContact(Contact contact) {
//		System.out.printf("------- Updating (putting) a contact with id: %s\n", contact.getId());
//		Response response = baseTarget.path(Long.toString(contact.getId())).request().put(Entity.xml(contact));
//		System.out.println("Updated contact: " + response.getStatus());
//	}
//
//	private static void deleteContactById(int... ids) {
//		System.out.printf("------- Deleting contacts: %s\n", Arrays.toString(ids));
//		for (int id : ids) {
//			WebTarget target = baseTarget.path(Long.toString(id));
//			Response response = target.request().delete();
//			System.out.println("Deleted contact: " + response.getStatus());
//		}
//	}
}