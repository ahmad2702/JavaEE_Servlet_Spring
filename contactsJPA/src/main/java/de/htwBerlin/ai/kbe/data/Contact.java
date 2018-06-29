package de.htwBerlin.ai.kbe.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String emailAddress;
    
    @OneToMany(mappedBy="contact", 
            cascade=CascadeType.ALL, 
            orphanRemoval=true, 
            fetch = FetchType.EAGER)
    private Set<Address> addressSet;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String mobile, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.emailAddress = emailAddress;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public Set<Address> getAddressSet() {
        if(addressSet == null) {
            addressSet = new HashSet<>();
        }
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
        // Works for JSON, but not for XML
        if(addressSet != null) {
            this.addressSet.forEach(a-> a.setContact(this));
        }
    }
    
    public void addAddress(Address address) {
        if(addressSet == null) {
            addressSet = new HashSet<>();
        }
        address.setContact(this);
        this.addressSet.add(address);
    }
    
    @Override
    public String toString() {
        String addresses = "Addresses:\n";
        for(Address a:addressSet) {
            addresses = addresses.concat(a.toString()) + "\n";
        }
        return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
                + ", emailAddress=" + emailAddress + ", addressSet=" + addresses + "]";
    }
}
