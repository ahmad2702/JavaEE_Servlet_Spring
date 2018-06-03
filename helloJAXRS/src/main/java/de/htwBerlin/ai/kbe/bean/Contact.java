package de.htwBerlin.ai.kbe.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact {
	private Integer id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String emailAddress;
	private Address address;

	// needed for JAXB
	public Contact() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter
		private Integer id;
		private String firstName;
		// optional parameters
		private String lastName;
		private String mobile;
		private String emailAddress;
		private Address address;

		public Builder(Integer id, String firstName) {
			this.id = id;
			this.firstName = firstName;
		}

		public Builder lastName(String val) {
			lastName = val;
			return this;
		}

		public Builder mobile(String val) {
			mobile = val;
			return this;
		}

		public Builder emailAddress(String val) {
			emailAddress = val;
			return this;
		}

		public Builder address(Address val) {
			address = val;
			return this;
		}

		public Contact build() {
			return new Contact(this);
		}
	}

	private Contact(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.mobile = builder.mobile;
		this.emailAddress = builder.emailAddress;
		this.address = builder.address;
	}
	
	// getters and evil setters :-), also needed for JAXB
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
				+ ", emailAddress=" + emailAddress + ", address=" + address + "]";
	}
}
