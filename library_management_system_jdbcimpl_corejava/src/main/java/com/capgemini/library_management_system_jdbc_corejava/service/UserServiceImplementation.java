package com.capgemini.library_management_system_jdbc_corejava.service;

import java.util.LinkedList;

import com.capgemini.library_management_system_jdbc_corejava.dao.UserDAO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;
import com.capgemini.library_management_system_jdbc_corejava.factory.BookFactory;

public class UserServiceImplementation implements UserService {
	
	private UserDAO dao=BookFactory.getUserDAO();

	public boolean register(UserDTO std) {

		return dao.register(std);
	}

	public boolean auth(String email, String password) {

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

	@Override
	public boolean requestBook(int bookId,int userId) {
		// TODO Auto-generated method stub
		return dao.requestBook(bookId, userId);
	}

	@Override
	public boolean returnBook(int userId,int bookId) {
		// TODO Auto-generated method stub
		return dao.returnBook(bookId, userId);
	}

	
	

}
