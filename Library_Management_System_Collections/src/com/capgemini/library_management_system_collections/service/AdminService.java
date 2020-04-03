package com.capgemini.library_management_system_collections.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.library_management_system_collections.dto.AdminDTO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;

public interface AdminService {
	
	boolean register(AdminDTO admin);
	AdminDTO auth(String email,String password);
	boolean addBook(BookDTO book);
	LinkedList<BookDTO> searchBookTitle(String bookTitle);
	LinkedList<BookDTO> searchBookAuthor(String bookAuthor);
	LinkedList<BookDTO> searchBookType(String bookType);
	int updateBook(int bookId);
	boolean removeBook(int bookId);
	LinkedList<Integer> getBookIds();
	LinkedList<BookDTO> getBooksInfo();
	
	List<UserDTO> showStudents();
	List<RequestDTO> showRequests();
	boolean bookIssue(UserDTO student,BookDTO book);
	boolean isBookReceived(UserDTO student,BookDTO book);
	
}
