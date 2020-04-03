package com.capgemini.library_management_system_collections.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable{
	
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userDepartment;
	private Date userIssueDate;
	private Date userReturnDate;
	private int userBooksBorrowed;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public Date getUserIssueDate() {
		return userIssueDate;
	}
	public void setUserIssueDate(Date userIssueDate) {
		this.userIssueDate = userIssueDate;
	}
	public Date getUserReturnDate() {
		return userReturnDate;
	}
	public void setUserReturnDate(Date userReturnDate) {
		this.userReturnDate = userReturnDate;
	}
	public int getUserBooksBorrowed() {
		return userBooksBorrowed;
	}
	public void setUserBooksBorrowed(int userBooksBorrowed) {
		this.userBooksBorrowed = userBooksBorrowed;
	}
	
	
	
	
	

}
