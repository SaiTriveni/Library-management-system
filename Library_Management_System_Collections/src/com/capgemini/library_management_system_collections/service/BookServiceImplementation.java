package com.capgemini.library_management_system_collections.service;
import java.util.List;

import com.capgemini.library_management_system_collections.dao.BookDAO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.factory.BookFactory;
public class BookServiceImplementation implements BookService {
	private BookDAO dao = BookFactory.getBookDAO();
	public boolean addBook(BookDTO dto) {
		return dao.addBook(dto) ;
	}
	public BookDTO issueBook(String book_name, int book_id) {
		return dao.issueBook(book_name, book_id);
	}
	public boolean returnBook(int book_id) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<BookDTO> search(int book_id) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean delete(String book_name, int book_id) {
		// TODO Auto-generated method stub
		return false;
	}
}
