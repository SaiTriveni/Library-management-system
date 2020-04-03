package com.capgemini.library_management_system_jdbc_corejava.dto;

import java.io.Serializable;

public class AdminDTO implements Serializable{

	private int adminUserId;
	private String adminUserName;
	private String adminEmail;
	private String adminPassword;
	private String adminRole;
	private String adminDepartment;
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
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public String getAdminDepartment() {
		return adminDepartment;
	}
	public void setAdminDepartment(String adminDepartment) {
		this.adminDepartment = adminDepartment;
	}
	@Override
	public String toString() {
		return "AdminDTO [adminUserId=" + adminUserId + ", adminUserName=" + adminUserName + ", adminEmail="
				+ adminEmail + ", adminPassword=" + adminPassword + ", adminRole=" + adminRole + ", adminDepartment="
				+ adminDepartment + "]";
	}
	
	
	
	
		
	}
	

