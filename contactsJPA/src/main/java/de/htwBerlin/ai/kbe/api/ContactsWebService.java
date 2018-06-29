package de.htwBerlin.ai.kbe.api;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import de.htwBerlin.ai.kbe.data.Contact;
import de.htwBerlin.ai.kbe.storage.ContactsDAO;


// URL fuer diesen Service ist: http://localhost:8080/contactsJPA/rest/contacts 
@Path("/contacts")
public class ContactsWebService {

    private ContactsDAO contactsDao;

    @Inject
    public ContactsWebService(ContactsDAO dao) {
        this.contactsDao = dao;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Collection<Contact> getAllContacts() {
        return contactsDao.findAllContacts();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getContact(@PathParam("id") Integer id) {
        Contact contact = contactsDao.findContactById(id);
        if (contact != null) {
            return Response.ok(contact).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No contact found with id " + id).build();
        }
    }

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces(MediaType.TEXT_PLAIN)
    public Response createContact(Contact contact) {
        int newId = contactsDao.saveContact(contact);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(newId));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Integer id, Contact contact) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("PUT not implemented").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        contactsDao.deleteContact(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}