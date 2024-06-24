package model;

import java.util.List;

// This is object for Yookoto app
public class User {
	
	private int privateId;
	
	private String publicId;
	
	private String fullName;
	
	private List<Role> roles;

	public int getPrivateId() {
		return privateId;
	}

	public void setPrivateId(int privateId) {
		this.privateId = privateId;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
