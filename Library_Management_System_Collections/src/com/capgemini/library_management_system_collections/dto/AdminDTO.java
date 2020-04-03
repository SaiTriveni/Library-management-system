package com.capgemini.library_management_system_collections.dto;

import java.io.Serializable;

public class AdminDTO implements Serializable{

	private int adminUserId;
	private String adminUserName;
	private String adminEmail;
	private String adminPassword;
	
	
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public int getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	@Override
	public String toString() {
		return "Admin [adminUserId=" + adminUserId + ", adminUserName=" + adminUserName + ", adminEmail=" + adminEmail
				+ ", adminPassword=" + adminPassword + "]";
	}

	
		
	}
	

