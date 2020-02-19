package com.xiehongyuan.pojo;

public class Like {
	private Integer id;
	private String text;
	private String url;
	private Integer user_id;
	private String created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Like() {
		super();
	}
	@Override
	public String toString() {
		return "Like [id=" + id + ", text=" + text + ", url=" + url + ", user_id=" + user_id + ", created=" + created
				+ "]";
	}
	
}
