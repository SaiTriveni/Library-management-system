package com.capgemini.library_management_system_collections.dao;

import java.util.List;

import com.capgemini.library_management_system_collections.database.DataBase;
import com.capgemini.library_management_system_collections.dto.BookDTO;

public class BookDAOImplementation implements BookDAO {

	public boolean addBook(BookDTO dto) {

		for(BookDTO bookdto : DataBase.BOOKS) {
			if(bookdto!=null) {
				System.out.println("Book is Added");
				return true;
			} else {
				System.out.println("Book is not added");
				
			}
		}
		return false;
	}

	public BookDTO issueBook(String book_name, int book_id) {

		return null;
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
