package com.nistagram.post.service.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreatedBy
	private String username;
	@CreatedDate
	private Instant createdAt;
	private String urlMedia;
	private String caption;
	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	private Instant updatedAt;
	private int totalLikes;
	private int totalDislikes;
	private Boolean favorite;

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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public String getUrlMedia() {
		return urlMedia;
	}

	public void setUrlMedia(String urlMedia) {
		this.urlMedia = urlMedia;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
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

	@Override
	public String toString() {
		return "Post [id=" + id + ", username=" + username + ", createdAt=" + createdAt + ", urlMedia=" + urlMedia
				+ ", caption=" + caption + ", lastModifiedBy=" + lastModifiedBy + ", updatedAt=" + updatedAt
				+ ", totalLikes=" + totalLikes + ", totalDislikes=" + totalDislikes + ", favorite=" + favorite + "]";
	}

}
