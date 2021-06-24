package com.nistagram.media.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "img")
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	private byte[] picByte;
	
	
	
	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Images(byte[] picByte, String name) {
		super();
		this.picByte = picByte;
		this.name = name;
	}
	
	
	
	
	
	public Images(Long id, String name, byte[] picByte) {
		super();
		this.id = id;
		this.name = name;
		this.picByte = picByte;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	
}
