package com.capgemini.library_management_system_jdbc_corejava.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;
import com.capgemini.library_management_system_jdbc_corejava.exception.UserException;
import com.capgemini.library_management_system_jdbc_corejava.exception.ValidationException;
import com.capgemini.library_management_system_jdbc_corejava.factory.BookFactory;
import com.capgemini.library_management_system_jdbc_corejava.service.AdminService;

public class UserDAOImplementation implements UserDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement pStatement = null;
	boolean flag = false;
	AdminService adminService = BookFactory.getAdminService();

	public boolean register(UserDTO user) {
		try {

			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection

			pStatement = connection.prepareStatement(properties.getProperty("query"));
			pStatement.setInt(1, user.getUserId());
			pStatement.setString(2, user.getUserName());
			pStatement.setString(3, user.getUserPassword());
			pStatement.setString(4, user.getUserEmail());
			pStatement.setString(5, user.getUserRole());
			

			int count = pStatement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return false;
	}

	public boolean auth(String email, String password) {

		flag = false;
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection

			pStatement = connection.prepareStatement(properties.getProperty("select"));

			pStatement.setString(1, email);
			pStatement.setString(2, password);
			resultset = pStatement.executeQuery();
			if (resultset.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;

	}

	public LinkedList<BookDTO> searchBookTitle(String bookName) {
		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("searchTitle"));

			pStatement.setString(1, bookName);
			resultset = pStatement.executeQuery();
			while (resultset.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public LinkedList<BookDTO> searchBookAuthor(String bookAuthor) {
		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("searchAuthor"));

			pStatement.setString(1, bookAuthor);
			resultset = pStatement.executeQuery();
			while (resultset.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public LinkedList<BookDTO> searchBookType(String bookType) {
		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("searchType"));

			pStatement.setString(1, bookType);
			resultset = pStatement.executeQuery();
			while (resultset.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public LinkedList<Integer> getBookIds() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("searchId"));
			resultset = pStatement.executeQuery();
			while (resultset.next()) {
				list.add(resultset.getInt("bookId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public LinkedList<BookDTO> getBooksInfo() {

		LinkedList<BookDTO> list = new LinkedList<BookDTO>();
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("displayAll"));

			resultset = pStatement.executeQuery();
			while (resultset.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookId(resultset.getInt("bookId"));
				bookDTO.setBookTitle(resultset.getString("bookTitle"));
				bookDTO.setBookAuthor(resultset.getString("bookAuthor"));
				bookDTO.setBookType(resultset.getString("bookType"));
				bookDTO.setBookPublisher(resultset.getString("bookPublisher"));
				list.add(bookDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public boolean requestBook(int bookId, int userId) {
		flag = false;
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("checkBookBorrowForNull"));

			pStatement.setInt(1, userId);
			ResultSet rs1 = pStatement.executeQuery();
			Object PreparedStatement;
			/*if(rs1.next())
			{
				PreparedStatement ps4 = connection.prepareStatement(properties.getProperty("insertValuesInNo"));
				ps4.setInt(1, userId);
				System.out.println("hey");
				int rs= ps4.executeUpdate();
				if(rs!=0)
				{
					throw new ValidationException("Values Cannot be inserted into booksBorrowed table");
				}
			}*/
				
			PreparedStatement ps5 = connection.prepareStatement(properties.getProperty("checkBookBorrow"));
			ps5.setInt(1, userId);
			ResultSet rs2 = ps5.executeQuery();
			int noOfBooksBorrowed = 0;
			while(rs2.next())
			{
			noOfBooksBorrowed = rs2.getInt("noOfBooksBorrowed");
			}
			if (noOfBooksBorrowed < 3) {
				noOfBooksBorrowed++;
				flag = adminService.issueBook(bookId, userId);
			} else {
				throw new ValidationException("user has exceeded limit to take books");
			}
			PreparedStatement ps2 = connection.prepareStatement(properties.getProperty("insertBookBorrow"));
			
			ps2.setInt(2, bookId);
			ps2.setInt(1,noOfBooksBorrowed);
			ps2.setInt(3,userId);
			int res  = ps2.executeUpdate();
			if(res==0)
			{
				throw new ValidationException("Inserting into BookBorrow has failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;

	}

	private static double differenceDate(Date issuedDate, Date returnDate) {
		double days = (returnDate.getTime() - issuedDate.getTime()) / 86400000;

		return days;
	}

	@Override
	public boolean returnBook(int userId,int bookId) {
		double f = 10;
		flag = false;
		try {
			FileInputStream inputStream = new FileInputStream("userdb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			Class.forName(properties.getProperty("path")).newInstance();
			// 2.Get the "DB connection" via Driver
			String dburl = properties.getProperty("dburl");
			connection = DriverManager.getConnection(dburl, properties);

			// 3.Issue the SQL query via connection
			pStatement = connection.prepareStatement(properties.getProperty("checkBookRequest"));

			pStatement.setInt(1, bookId);
			resultset = pStatement.executeQuery();
			System.out.println("Return");
			System.out.println(bookId);
			System.out.println(userId);
			if (resultset.next()) {
				System.out.println("in 1 if");
				Date issuedDate;
				PreparedStatement ps3 = connection.prepareStatement(properties.getProperty("getIssueDate"));
				ps3.setInt(1,bookId);
				ResultSet searchBookResultSet = ps3.executeQuery();
				if(searchBookResultSet.next()) {
				issuedDate = searchBookResultSet.getDate("issueDate");
				Date returnDate = new Date();
				double noOfDays = differenceDate(issuedDate, returnDate);
				int noOfDaysint = (int) noOfDays;
				PreparedStatement ps4 = connection.prepareStatement(properties.getProperty("checkFines"));
				ps4.setInt(1, userId);
				ResultSet rs = ps4.executeQuery();
				if(rs.next()) {
				double fine = rs.getInt("fineAmount");
				System.out.println(noOfDays);
				
				if(noOfDays < 15.0)
				{
					PreparedStatement ps1 = connection.prepareStatement(properties.getProperty("selectIssueBook"));
					ps1.setInt(1,bookId);
					resultset = ps1.executeQuery();
					
					PreparedStatement ps2 = connection.prepareStatement(properties.getProperty("insertBook"));
					ps2.setInt(1,resultset.getInt("issueBookId"));
					ps2.setString(2,resultset.getString("bookTitle"));
					ps2.setString(3,resultset.getString("bookAuthor"));
					ps2.setString(4,resultset.getString("bookType"));
					ps2.setString(5,resultset.getString("bookPublisher"));
					int res = ps2.executeUpdate();
					if(res==0)
					{
						flag = true;
					}	
					else
					{
						flag = false;
						PreparedStatement ps5 = connection.prepareStatement(properties.getProperty("insertFine"));
						ps5.setInt(1,userId);
						ps5.setDouble(2,f);
						int rs1 = ps4.executeUpdate();
						if(rs1!=0)
						{
							throw new ValidationException("fine table insertion failed");
						}
						throw new ValidationException("Book has not been inserted into Book table");
						
						
					}
				}
				}
				}

			} else {
				throw new ValidationException("Book cannot be returned,because fine has been applied ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// 5.close all the jdbc objects
			try {
				if (connection != null) {

					connection.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return flag;

	}

}