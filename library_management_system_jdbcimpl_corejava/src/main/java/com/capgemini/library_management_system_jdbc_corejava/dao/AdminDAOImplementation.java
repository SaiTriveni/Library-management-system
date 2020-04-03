package com.capgemini.library_management_system_jdbc_corejava.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.library_management_system_jdbc_corejava.database.DataBase;
import com.capgemini.library_management_system_jdbc_corejava.dto.AdminDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;
import com.capgemini.library_management_system_jdbc_corejava.exception.AdminException;
import com.capgemini.library_management_system_jdbc_corejava.exception.UserException;
import com.capgemini.library_management_system_jdbc_corejava.exception.ValidationException;

public class AdminDAOImplementation implements AdminDAO {

	Connection connection=null;
	Statement statement=null;
	ResultSet resultset=null;
	PreparedStatement pStatement=null;
	boolean flag = false;

	public boolean register(AdminDTO admin) {
		flag = false;
		try {
			
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection

			pStatement=connection.prepareStatement(properties.getProperty("query"));
		
			pStatement.setInt(1,admin.getAdminUserId());
			pStatement.setString(2,admin.getAdminUserName());
			pStatement.setString(3,admin.getAdminPassword());
			pStatement.setString(4,admin.getAdminEmail());
			pStatement.setString(5,admin.getAdminRole());
			System.out.println("before Insert");
			int count = pStatement.executeUpdate();
			System.out.println("After insert");
			if(count!=0) {
				flag = true;
			}else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}



		return flag;
	}

	public boolean auth(String email, String password) {

		flag = false;
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection

			pStatement=connection.prepareStatement(properties.getProperty("select"));

			pStatement.setString(1,email);
			pStatement.setString(2,password);
			resultset=pStatement.executeQuery();
			if(resultset.next())
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;

		}


	public boolean addBook(BookDTO book) {
		flag = false;
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection

			pStatement=connection.prepareStatement(properties.getProperty("insertBook"));

			pStatement.setInt(1,book.getBookId());
			pStatement.setString(2,book.getBookTitle());
			pStatement.setString(3,book.getBookAuthor());
			pStatement.setString(4,book.getBookType());
			pStatement.setString(5,book.getBookPublisher());
 			int res = pStatement.executeUpdate();
			if(res!= 0)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;
	}

	public LinkedList<BookDTO> searchBookTitle(String bookName) {
		
		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);
			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			pStatement=connection.prepareStatement(properties.getProperty("searchTitle"));
			pStatement.setString(1,bookName);
			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;


	}

	public LinkedList<BookDTO> searchBookAuthor(String bookAuthor) {
		LinkedList<BookDTO> list= new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("searchAuthor"));

			pStatement.setString(1,bookAuthor);
			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public LinkedList<BookDTO> searchBookType(String bookType) {
		
		LinkedList<BookDTO> list= new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("searchType"));

			pStatement.setString(1,bookType);
			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

		
	}

	public boolean updateBook(int bookId,String bookAuthor) {
		flag = false;
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("update"));

			pStatement.setString(1,bookAuthor);
			pStatement.setInt(2,bookId);
			int res = pStatement.executeUpdate();
			if(res!= 0)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;
		
	}

	public LinkedList<Integer> getBookIds() {
		LinkedList<Integer> list= new LinkedList<Integer>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("searchId"));

			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				list.add(resultset.getInt("bookId"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;


	}

	public LinkedList<BookDTO> getBooksInfo() {
		
		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("displayAll"));

			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;	}

	public boolean removeBook(int bookId) {
		flag = false;
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);
			
			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("removeBook"));

			pStatement.setInt(1,bookId);
			int res = pStatement.executeUpdate();
			if(res!= 0)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;
	}

	public List<String> showStudents() {
		LinkedList<String> list = new LinkedList<String>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("displayAllUsers"));

			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				list.add(resultset.getString("userName"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;	
	}
		
	public LinkedList<Integer> showRequests() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("displayAllRequests"));

			resultset=pStatement.executeQuery();
			while(resultset.next())
			{
				list.add(resultset.getInt("requestUserId"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;	
	}
		


	public boolean issueBook(int bookId, int userId) {
		String title = "";
		try {
			FileInputStream inputStream=new FileInputStream("db.properties");
			Properties properties=new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the  "DB connection" via Driver
			String dburl=properties.getProperty("dburl");
			connection=DriverManager.getConnection(dburl,properties); 

			//3.Issue the SQL query via connection
			
			pStatement=connection.prepareStatement(properties.getProperty("issueBook"));
			pStatement.setInt(1,bookId);
			resultset=pStatement.executeQuery();
			if(resultset.next())
			{
				title = resultset.getString("bookTitle");
				PreparedStatement ps = connection.prepareStatement(properties.getProperty("insertIssueBook"));
				ps.setInt(1,resultset.getInt("bookId"));
				System.out.println(resultset.getInt("bookId"));
				ps.setString(2,resultset.getString("bookTitle"));
				ps.setString(3,resultset.getString("bookAuthor"));
				ps.setString(4,resultset.getString("bookType"));
				ps.setString(5,resultset.getString("bookPublisher"));
				int res = ps.executeUpdate();
				if(res != 0)
				{
					flag = true;
				}
				else
				{
					flag = false;
				}
			}
			else
			{
				throw new ValidationException("Book Not Found In Library");
			}
			PreparedStatement ps1 = connection.prepareStatement(properties.getProperty("insertRequestBook"));
			ps1.setInt(1,bookId);
			ps1.setInt(2,userId);
			ps1.setString(3,title);
			int res = ps1.executeUpdate();
			if(res == 0)
			{
				throw new ValidationException("insert into RequestBook is Failed");
			}
			/*PreparedStatement ps2 = connection.prepareStatement(properties.getProperty("deleteBookIssued"));
			ps2.setInt(1,bookId);
			res = ps2.executeUpdate();
			if(res!=0)
			{
				throw new ValidationException("Deletion is failed");
			}/*/
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			//5.close all the jdbc objects
			try {
				if(connection!=null) {

					connection.close();
				}if(pStatement!=null) {
					pStatement.close();
				}			if(resultset!=null) {
					resultset.close();
				}
			}
			catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;
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
