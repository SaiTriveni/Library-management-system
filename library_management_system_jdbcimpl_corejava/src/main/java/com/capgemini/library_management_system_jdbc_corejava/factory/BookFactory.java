package com.capgemini.library_management_system_jdbc_corejava.factory;

import com.capgemini.library_management_system_jdbc_corejava.dao.AdminDAO;
import com.capgemini.library_management_system_jdbc_corejava.dao.AdminDAOImplementation;
import com.capgemini.library_management_system_jdbc_corejava.dao.UserDAO;
import com.capgemini.library_management_system_jdbc_corejava.dao.UserDAOImplementation;
import com.capgemini.library_management_system_jdbc_corejava.service.AdminService;
import com.capgemini.library_management_system_jdbc_corejava.service.AdminServiceImplementation;

import com.capgemini.library_management_system_jdbc_corejava.service.UserService;
import com.capgemini.library_management_system_jdbc_corejava.service.UserServiceImplementation;

public class BookFactory {
	
	
	public static AdminDAO getAdminDao() {
		return new AdminDAOImplementation();
		
	}
	
	public static AdminService getAdminService() {
		return new AdminServiceImplementation();
	}
	
	public static UserDAO getUserDAO() {
		
		return new UserDAOImplementation();
	}
	
	public static UserService getUserService() {
		return new UserServiceImplementation();
	}
	

}
