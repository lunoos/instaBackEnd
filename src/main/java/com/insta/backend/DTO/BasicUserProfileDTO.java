package com.insta.backend.DTO;

public class BasicUserProfileDTO {

	private Long userId;
	private String userName;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public BasicUserProfileDTO(Long userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "BasicUserProfileDTO [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
}
