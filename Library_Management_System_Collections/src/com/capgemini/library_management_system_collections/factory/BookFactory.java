package com.capgemini.library_management_system_collections.factory;

import com.capgemini.library_management_system_collections.dao.AdminDAO;
import com.capgemini.library_management_system_collections.dao.AdminDAOImplementation;
import com.capgemini.library_management_system_collections.dao.BookDAO;
import com.capgemini.library_management_system_collections.dao.BookDAOImplementation;
import com.capgemini.library_management_system_collections.dao.UserDAO;
import com.capgemini.library_management_system_collections.dao.UserDAOImplementation;
import com.capgemini.library_management_system_collections.service.AdminService;
import com.capgemini.library_management_system_collections.service.AdminServiceImplementation;
import com.capgemini.library_management_system_collections.service.BookService;
import com.capgemini.library_management_system_collections.service.BookServiceImplementation;
import com.capgemini.library_management_system_collections.service.UserService;
import com.capgemini.library_management_system_collections.service.UserServiceImplementation;

public class BookFactory {
	
	public static BookDAO getBookDAO() {
		
		return new BookDAOImplementation();
	}
	
	public static BookService getBookService() {
		
		return new BookServiceImplementation();
	}
	
	public static AdminDAO getAdminDao() {
		return new AdminDAOImplementation();
		
	}
	
	public static AdminService getAdminService() {
		return new AdminServiceImplementation();
	}
	
	public static UserDAO getStudentDAO() {
		
		return new UserDAOImplementation();
	}
	
	public static UserService getStudentService() {
		return new UserServiceImplementation();
	}
	

}
