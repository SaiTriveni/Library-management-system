package com.capgemini.library_management_system_collections.dao;

import java.util.LinkedList;

import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;

public interface UserDAO {
	boolean register(UserDTO std);
	UserDTO auth(String email,String password);
	LinkedList<BookDTO> searchBookTitle(String bookName);
	LinkedList<BookDTO> searchBookAuthor(String bookAuthor);
	LinkedList<BookDTO> searchBookType(String bookType);
	LinkedList<Integer> getBookIds();
	LinkedList<BookDTO> getBooksInfo();
	RequestDTO requestBook(UserDTO student, BookDTO book);
	RequestDTO returnBook(UserDTO student, BookDTO book);
	
	

}
