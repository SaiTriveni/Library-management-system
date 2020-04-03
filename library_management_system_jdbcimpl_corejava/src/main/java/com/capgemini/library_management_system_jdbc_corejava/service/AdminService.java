package com.capgemini.library_management_system_jdbc_corejava.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.library_management_system_jdbc_corejava.dto.AdminDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;

public interface AdminService {
	
	boolean register(AdminDTO admin);
	boolean auth(String email,String password);
	boolean addBook(BookDTO book);
	LinkedList<BookDTO> searchBookTitle(String bookTitle);
	LinkedList<BookDTO> searchBookAuthor(String bookAuthor);
	LinkedList<BookDTO> searchBookType(String bookType);
	boolean updateBook(int bookId, String author);
	boolean removeBook(int bookId);
	LinkedList<Integer> getBookIds();
	LinkedList<BookDTO> getBooksInfo();
	
	List<String> showStudents();
	LinkedList<Integer> showRequests();
	boolean issueBook(int bookId, int userId);
	
}
