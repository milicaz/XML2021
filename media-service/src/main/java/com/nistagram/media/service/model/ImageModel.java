package com.nistagram.media.service.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "post_images")
public class ImageModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	private byte[] picByte;
	private String username;
	
	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ImageModel(String name, byte[] picByte, String username) {
		super();
		this.name = name;
		this.picByte = picByte;
		this.username = username;
	}
	

	public ImageModel(Long id, String name, byte[] picByte, String username) {
		super();
		this.id = id;
		this.name = name;
		this.picByte = picByte;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	
	
	
	
	

}
