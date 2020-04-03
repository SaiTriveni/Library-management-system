package com.capgemini.library_management_system_collections.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.library_management_system_collections.dao.AdminDAO;
import com.capgemini.library_management_system_collections.dto.AdminDTO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;
import com.capgemini.library_management_system_collections.factory.BookFactory;

public class AdminServiceImplementation implements AdminService {

	private AdminDAO dao=BookFactory.getAdminDao();
	
	public boolean register(AdminDTO admin) {
		return dao.register(admin);
	}

	public AdminDTO auth(String email, String password) {

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

	public int updateBook(int bookId) {
		return dao.updateBook(bookId);
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

	public List<UserDTO> showStudents() {
		// TODO Auto-generated method stub
		return dao.showStudents();
	}

	public List<RequestDTO> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	public boolean bookIssue(UserDTO student, BookDTO book) {
		// TODO Auto-generated method stub
		return dao.bookIssue(student, book);
	}

	public boolean isBookReceived(UserDTO student, BookDTO book) {
		// TODO Auto-generated method stub
		return dao.isBookReceived(student, book);
	}

	

}
