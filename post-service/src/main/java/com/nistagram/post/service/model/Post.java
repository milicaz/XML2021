package com.nistagram.post.service.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreatedBy
	private String username;
	@CreatedDate
	private Date createdAt;
	private String caption;
	private int totalLikes;
	private int totalDislikes;
	private Boolean favorite;
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;

	public Post() {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public int getTotalDislikes() {
		return totalDislikes;
	}

	public void setTotalDislikes(int totalDislikes) {
		this.totalDislikes = totalDislikes;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", username=" + username + ", createdAt=" + createdAt + ", caption=" + caption
				+ ", totalLikes=" + totalLikes + ", totalDislikes=" + totalDislikes + ", favorite=" + favorite
				+ ", picByte=" + Arrays.toString(picByte) + "]";
	}

}
