package com.nistagram.friends.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String dateOfBirth;
	private String phone;
	private String privacy;
	
	@Column(name = "picByte", length = 300000)
	private byte[] picByte;
	
	private String friendUname;

	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Friends(Long id, String username, String firstName, String lastName, String email, String dateOfBirth,
			String phone, String privacy, byte[] picByte, String friendUname) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.privacy = privacy;
		this.picByte = picByte;
		this.friendUname = friendUname;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getFriendUname() {
		return friendUname;
	}

	public void setFriendUname(String friendUname) {
		this.friendUname = friendUname;
	}
	
	

}
