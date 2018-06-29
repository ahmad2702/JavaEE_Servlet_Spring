package de.htwBerlin.ai.kbe.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class User
 *
 */

@XmlRootElement(name = "user")
public class User {

	private Integer id;
	private String userId;
	private String lastName;
	private String firstName;

	public User() {
	}

	/**
	 * getId
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setId
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * getUserId
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * setUserId
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * getLastName
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * setLastName
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * getFirstName
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * setFirstName
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}

}
