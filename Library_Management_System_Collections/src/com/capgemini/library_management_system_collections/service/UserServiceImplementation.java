package com.capgemini.library_management_system_collections.service;

import java.util.LinkedList;

import com.capgemini.library_management_system_collections.dao.UserDAO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;
import com.capgemini.library_management_system_collections.factory.BookFactory;

public class UserServiceImplementation implements UserService {
	
	private UserDAO dao=BookFactory.getStudentDAO();

	public boolean register(UserDTO std) {

		return dao.register(std);
	}

	public UserDTO auth(String email, String password) {

		return dao.auth(email, password);
	}


	public LinkedList<BookDTO> searchBookTitle(String bookName) {
		return dao.searchBookTitle(bookName);
	}

	public LinkedList<BookDTO> searchBookAuthor(String bookAuthor) {
		return dao.searchBookAuthor(bookAuthor);
	}

	public LinkedList<BookDTO> searchBookType(String bookType) {
		return dao.searchBookType(bookType);
	}

	public LinkedList<Integer> getBookIds() {
		return dao.getBookIds();
	}

	public LinkedList<BookDTO> getBooksInfo() {
		return dao.getBooksInfo();
	}

	public RequestDTO requestBook(UserDTO student, BookDTO book) {
		// TODO Auto-generated method stub
		return dao.requestBook(student, book);
	}

	public RequestDTO returnBook(UserDTO student, BookDTO book) {
		// TODO Auto-generated method stub
		return dao.returnBook(student, book);
	}
	
	

}
