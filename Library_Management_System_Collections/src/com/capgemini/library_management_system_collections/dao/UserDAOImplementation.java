package com.capgemini.library_management_system_collections.dao;

import java.util.LinkedList;

import com.capgemini.library_management_system_collections.database.DataBase;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;
import com.capgemini.library_management_system_collections.exception.UserException;

public class UserDAOImplementation implements UserDAO{

	
	public boolean register(UserDTO std) {
		
		for(UserDTO dto : DataBase.STUDENTS) {
			if(dto.getUserEmail().equals(std.getUserEmail())) {
				return false;
			}
		}
		DataBase.STUDENTS.add(std);
		return true;
	}

	public UserDTO auth(String email, String password) {

		for(UserDTO dto : DataBase.STUDENTS) {
			if(dto.getUserEmail().equals(email) && dto.getUserPassword().equals(password)) {
				System.out.println("Login Successful");
				return dto;
			}
			
		}
		throw new UserException("Invalid Email or Password");
		
	}

	public LinkedList<BookDTO> searchBookTitle(String bookName) {
		LinkedList<BookDTO> searchList=new LinkedList<BookDTO>();
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			String retrievedBookName=retrievedBook.getBookTitle();
			if(bookName.equals(retrievedBookName))
			{
				searchList.add(retrievedBook);	
				return searchList;	
			}
		}
		if(searchList.size()==0)
		{
			throw new UserException("Book is Not Found");
		}
		else
		{
			return searchList;
		}		

	}

	public LinkedList<BookDTO> searchBookAuthor(String bookAuthor) {
		LinkedList<BookDTO> searchList=new LinkedList<BookDTO>();
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			String retrievedBookAuthor=retrievedBook.getBookAuthor();
			if(bookAuthor.equals(retrievedBookAuthor))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new UserException("Book is Not Found");
		}
		else
		{
			return searchList;
		}
		
	}

	public LinkedList<BookDTO> searchBookType(String bookType) {
		LinkedList<BookDTO> searchList=new LinkedList<BookDTO>();
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			String retrievedBookType=retrievedBook.getBookType();
			if(bookType.equals(retrievedBookType))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new UserException("Book is Not Found");
		}
		else
		{
			return searchList;
		}
	}

	public LinkedList<Integer> getBookIds() {
		LinkedList<Integer> idList=new LinkedList<Integer>();
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			int retrievedBookId=retrievedBook.getBookId();
			idList.add(retrievedBookId);
		}
		return idList;
	}

	public LinkedList<BookDTO> getBooksInfo() {
		
		return DataBase.BOOKS;
	}

	public RequestDTO requestBook(UserDTO student, BookDTO book) {
		boolean flag = false, isRequestExists = false;
		RequestDTO requestInfo = new RequestDTO();
		UserDTO userInfo2 = new UserDTO();
		BookDTO bookInfo2 = new BookDTO();

		for (RequestDTO requestInfo2 : DataBase.REQUESTS) {
			if (book.getBookId() == requestInfo2.getBookInfo().getBookId()) {
				isRequestExists = true;

			}

		}

		if (!isRequestExists) {
			for (UserDTO user : DataBase.STUDENTS) {
				if (user.getUserId() == student.getUserId()) {
					for (BookDTO book1 : DataBase.BOOKS) {
						if (book1.getBookId() == book1.getBookId()) {
							userInfo2 = user;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setStudentInfo(userInfo2);
				DataBase.REQUESTS.add(requestInfo);
				return requestInfo;
			}

		}

		throw new UserException("Invalid request or you cannot request that book");
	}

	public RequestDTO returnBook(UserDTO student, BookDTO book) {
		//RequestInfo info = new RequestInfo();
				for (RequestDTO requestInfo : DataBase.REQUESTS) {
					
					  if (requestInfo.getBookInfo().getBookId() == book.getBookId() &&
					  requestInfo.getStudentInfo().getUserId() == student.getUserId() &&
					  requestInfo.isIssued() == true) {
					 
//					if (requestInfo.isIssued() == true) {
						System.out.println("Returning Issued book only");
						requestInfo.setReturned(true);
					//	info = requestInfo;

						return requestInfo;
					}

				}

				throw new UserException("Invalid return ");
			}
}
