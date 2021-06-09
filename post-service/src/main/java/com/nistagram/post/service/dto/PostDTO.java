package com.nistagram.post.service.dto;

public class PostDTO {

	private String username;
	private String urlMedia;
	private String caption;
	private int totalLikes;
	private int totalDislikes;
	private Boolean favorite;

	public PostDTO() {
	}

	public PostDTO(String username, String urlMedia, String caption, int totalLikes, int totalDislikes,
			Boolean favorite) {
		super();
		this.username = username;
		this.urlMedia = urlMedia;
		this.caption = caption;
		this.totalLikes = totalLikes;
		this.totalDislikes = totalDislikes;
		this.favorite = favorite;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
