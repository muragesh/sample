package assignment.user.models;

import javax.persistence.Entity;

@Entity
public class UserLoginToken extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private Long userId;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
