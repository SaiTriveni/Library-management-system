package com.capgemini.library_management_system_collections.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.library_management_system_collections.database.DataBase;
import com.capgemini.library_management_system_collections.dto.AdminDTO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;
import com.capgemini.library_management_system_collections.exception.AdminException;

public class AdminDAOImplementation implements AdminDAO {

	public boolean register(AdminDTO admin) {

		for(AdminDTO adm : DataBase.ADMINS) {
			if(adm.getAdminEmail().equals(admin.getAdminEmail())) {
				return false;
			}
		}
		DataBase.ADMINS.add(admin);
		return true;
	}

	public AdminDTO auth(String email, String password) {

		for(AdminDTO adm : DataBase.ADMINS) {
			if(adm.getAdminEmail().equals(email) && adm.getAdminPassword().equals(password)) {
				System.out.println("Login Successful");
				return adm;
			}
		}
		throw new AdminException("Invalid Credentials");
		}


	public boolean addBook(BookDTO book) {
		for(BookDTO bookDto : DataBase.BOOKS) {
			if(bookDto.getBookId() == book.getBookId()  ) {
				return false;
			}
		}
		DataBase.BOOKS.add(book);
		return true;
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
			throw new AdminException("Book is Not Found");
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
			throw new AdminException("Book is Not Found");
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
			throw new AdminException("Book is Not Found");
		}
		else
		{
			return searchList;
		}	
		
	}

	public int updateBook(int bookId) {

		int status=0;
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			int retrievedId=retrievedBook.getBookId();
			if(bookId==retrievedId)
			{
				status++;
				break;
			}
		}
		 throw new AdminException("Book is Not Found");
		
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

	public boolean removeBook(int bookId) {
		boolean status=false;
		for(int i=0;i<=DataBase.BOOKS.size()-1;i++)
		{
			BookDTO retrievedBook=DataBase.BOOKS.get(i);
			int retrievedId=retrievedBook.getBookId();
			if(bookId==retrievedId)
			{
				status=true;
				DataBase.BOOKS.remove(i);
				
			}
		}
		return status;
	}

	public List<UserDTO> showStudents() {
		List<UserDTO> show = new LinkedList<UserDTO>();

		for (UserDTO info : DataBase.STUDENTS) {
			info.getUserId();
			info.getUserName();
			info.getUserEmail();
			info.getUserBooksBorrowed();
			show.add(info);
		}
		return show;
	}
		
	public List<RequestDTO> showRequests() {
		List<RequestDTO> show = new LinkedList<RequestDTO>();
		for (RequestDTO requestInfo : DataBase.REQUESTS) {
			requestInfo.getBookInfo();
			requestInfo.getStudentInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			show.add(requestInfo);
		}
		return show;
	}
		

	public boolean bookIssue(UserDTO student, BookDTO book) {
		boolean isValid = false;

		RequestDTO requestInfo = new RequestDTO();

		int noOfBooksBorrowed = student.getUserBooksBorrowed();
		for (RequestDTO info : DataBase.REQUESTS) {
			if (info.getStudentInfo().getUserId() == student.getUserId()) {
				if (info.getBookInfo().getBookId() == book.getBookId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid)

		{
			

			for (BookDTO info2 : DataBase.BOOKS) {
				if (info2.getBookId() == book.getBookId()) {
					book = info2;
				}

			}

			for (UserDTO studentInfo : DataBase.STUDENTS) {
				if (studentInfo.getUserId() == student.getUserId()) {
					student = studentInfo;
					noOfBooksBorrowed = student.getUserBooksBorrowed();

				}

			}

			if (noOfBooksBorrowed < 3) {
				
				boolean isRemoved = DataBase.BOOKS.remove(book);
				if (isRemoved) {
					
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					student.setUserBooksBorrowed(noOfBooksBorrowed);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new AdminException("Book can't be borrowed");
				}

			} else {
				throw new AdminException("Student Exceeds maximum limit");
			}

		} else {
			throw new AdminException("Book data or Student data is incorrect");

		}
	}
		
	public boolean isBookReceived(UserDTO student, BookDTO book) {
		boolean isValid = false;
		RequestDTO requestInfo1 = new RequestDTO();
		for (RequestDTO requestInfo : DataBase.REQUESTS) {

			if (requestInfo.getBookInfo().getBookId() == book.getBookId()
					&& requestInfo.getStudentInfo().getUserId() == student.getUserId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {
			
			book.setBookAuthor(requestInfo1.getBookInfo().getBookAuthor());
			book.setBookTitle(requestInfo1.getBookInfo().getBookTitle());
			//book.setPrice(requestInfo1.getBookInfo().getPrice());
			DataBase.BOOKS.add(book);
			DataBase.REQUESTS.remove(requestInfo1);
			

			for (UserDTO userInfo2 : DataBase.STUDENTS) {
				if (userInfo2.getUserId() == student.getUserId()) {
					student = userInfo2;
				}

			}

			
			int noOfBooksBorrowed = student.getUserBooksBorrowed();
			noOfBooksBorrowed--;
			student.setUserBooksBorrowed(noOfBooksBorrowed);
			return true;

		}

		return false;
	}
		
	

}
