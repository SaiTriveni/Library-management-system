package com.capgemini.library_management_system_jdbc_corejava.service;

import java.util.LinkedList;

import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;

public interface UserService {
	
	boolean register(UserDTO std);
	boolean auth(String email,String password);
	LinkedList<BookDTO> searchBookTitle(String bookName);
	LinkedList<BookDTO> searchBookAuthor(String bookAuthor);
	LinkedList<BookDTO> searchBookType(String bookType);
	LinkedList<Integer> getBookIds();
	LinkedList<BookDTO> getBooksInfo();
	boolean requestBook(int bookId,int userId);
	boolean returnBook(int userId,int bookId);

}
