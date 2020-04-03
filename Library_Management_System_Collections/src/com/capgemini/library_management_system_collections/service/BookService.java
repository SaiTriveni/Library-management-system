package com.capgemini.library_management_system_collections.service;

import java.util.List;

import com.capgemini.library_management_system_collections.dto.BookDTO;

public interface BookService {
	
	boolean addBook(BookDTO dto);
	BookDTO issueBook(String book_name,int book_id);
	boolean returnBook(int book_id);
	List<BookDTO> search(int book_id);
	boolean delete(String book_name,int book_id);

}
