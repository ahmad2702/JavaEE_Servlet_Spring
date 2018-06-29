package de.htwBerlin.ai.kbe.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String city;
    private String zip;
    private String country;
    
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Address() {}
    
    public Address(String street, String city, String zip, String country) {
        this(street, city, zip, country, null);
    }
    
    public Address(String street, String city, String zip, String country, Contact contact) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.contact = contact;
    }
    
    public int getId() {
        return id;
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    // Wichtig beim Persistieren von Contact mit Address:
    // contact.getAddress().setContact(contact);
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", city=" + city + ", zip=" + zip + ", country=" + country + "]";
    }
    
//    Kein getContact: getter loest Probleme mit Jackson aus 
//    public Contact getContact() {
//        return this.contact;
//    }
    
}
