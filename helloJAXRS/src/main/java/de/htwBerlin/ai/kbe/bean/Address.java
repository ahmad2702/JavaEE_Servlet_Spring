package de.htwBerlin.ai.kbe.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

	private String street;
	private String city;
	private String zip;
	private String country;

	public Address() {
	}

	public static class Builder {
		private String street;
		private String city;
		private String zip;
		private String country;

		public Builder() {
		}

		public Builder street(String val) {
			street = val;
			return this;
		}

		public Builder city(String val) {
			city = val;
			return this;
		}

		public Builder zip(String val) {
			zip = val;
			return this;
		}

		public Builder country(String val) {
			country = val;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}
	
	private Address(Builder builder) {
		this.street = builder.street;
		this.city = builder.city;
		this.zip = builder.zip;
		this.country = builder.country;
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

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zip=" + zip + ", country=" + country + "]";
	}
}
