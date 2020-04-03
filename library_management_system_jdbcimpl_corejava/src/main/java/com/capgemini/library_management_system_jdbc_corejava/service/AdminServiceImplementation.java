package com.capgemini.library_management_system_jdbc_corejava.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.library_management_system_jdbc_corejava.dao.AdminDAO;
import com.capgemini.library_management_system_jdbc_corejava.dto.AdminDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;
import com.capgemini.library_management_system_jdbc_corejava.factory.BookFactory;

public class AdminServiceImplementation implements AdminService {

	private AdminDAO dao=BookFactory.getAdminDao();
	
	public boolean register(AdminDTO admin) {
		return dao.register(admin);
	}

	public boolean auth(String email, String password) {

		return dao.auth(email, password);
	}

	public boolean addBook(BookDTO book) {
		return dao.addBook(book);
	}

	public LinkedList<BookDTO> searchBookTitle(String bookTitle) {
		return dao.searchBookTitle(bookTitle);
	}

	public LinkedList<BookDTO> searchBookAuthor(String bookAuthor) {
		return dao.searchBookAuthor(bookAuthor);
	}

	public LinkedList<BookDTO> searchBookType(String bookType) {
		return dao.searchBookType(bookType);
	}

	public boolean updateBook(int bookId,String bookAuthor) {
		return dao.updateBook(bookId, bookAuthor);
	}

	public boolean removeBook(int bookId) {
		return dao.removeBook(bookId);
	}

	public LinkedList<Integer> getBookIds() {
		return dao.getBookIds();
	}

	public LinkedList<BookDTO> getBooksInfo() {
		return dao.getBooksInfo();
	}

	public List<String> showStudents() {
		// TODO Auto-generated method stub
		return dao.showStudents();
	}

	public LinkedList<Integer> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		// TODO Auto-generated method stub
		return dao.issueBook(bookId,userId);
	}




	

}
