package com.capgemini.library_management_system_jdbc_corejava.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.library_management_system_jdbc_corejava.dto.AdminDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;
import com.capgemini.library_management_system_jdbc_corejava.exception.ValidationException;
import com.capgemini.library_management_system_jdbc_corejava.factory.BookFactory;
import com.capgemini.library_management_system_jdbc_corejava.service.AdminService;
import com.capgemini.library_management_system_jdbc_corejava.service.UserService;
import com.capgemini.library_management_system_jdbc_validation.ValidationAdminStudent;

public class Librarian extends Thread{


	public void performOperations() {

		boolean flag = false;
		boolean flag1 = false;

		int regId = 0; 
		String regName = null; 
		String regEmail = null;
		String regPassword = null;
		String regRole = null;
		String regDepartment = null;

		int regId1 = 0; 
		String regName1 = null; 
		String regEmail1 = null; 
		String regPassword1 = null;


		ValidationAdminStudent validation = new ValidationAdminStudent();

		Scanner scanner = new Scanner(System.in);
		int i = 0;
		String role = "";
		do {
			
			do {
				try {

					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					System.out.println("Enter the role to choose either User or Admin");
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

					role = scanner.next();
					 flag = true;
				}catch(InputMismatchException e) {
					System.out.println("Enter only Integers");
					flag=false;
				}
			}while(!flag);


			switch(role) {
			case "Admin":
				AdminService service = BookFactory.getAdminService();
				do{
					System.out.println("#########################################");
					System.out.println("Press 1 to Register as Admin");
					System.out.println("Press 2 for Admin Login ");
					System.out.println("Press 3 to exit");
					System.out.println("#########################################");

					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						
						System.out.println("Enter Role");
						regRole = scanner.next();
									
						do {
							try {
								System.out.println("Enter ID :");
								regId = scanner.nextInt();
								validation.validatedId(regId);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Id should contains only digits");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Name :");
								regName = scanner.next();
								validation.validatedName(regName);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should contains only Alphabates");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);



						do {
							try {
								System.out.println("Enter Email :");
								regEmail = scanner.next();
								validation.validatedEmail(regEmail);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Email should be proper ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);



						do {
							try {
								System.out.println("Enter Password :");
								regPassword = scanner.next();
								validation.validatedPassword(regPassword);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						
						AdminDTO adminDto = new AdminDTO();
						adminDto.setAdminRole(role);
						adminDto.setAdminUserId(regId);
						adminDto.setAdminUserName(regName);
						adminDto.setAdminEmail(regEmail);
						adminDto.setAdminPassword(regPassword);
						boolean check = service.register(adminDto);
						if(check) {
							System.out.println("Registered");
						} else {
							System.out.println("Email already exist");
						}	

						break;

					case 2:
					do {	
						System.out.println("Enter email");
						String email = scanner.next();
						System.out.println("Enter Password");
						String password = scanner.next();
						flag1 = service.auth(email, password);
						}while(!flag1);
						try {
							
							System.out.println("Logged in");

							do {
								System.out.println("*****************************************");
								System.out.println("Press 1 to Add Books");
								System.out.println("Press 2 to Update");
								System.out.println("Press 3 to Search Book by Book Name");
								System.out.println("Press 4 to Search Book by Book Author");
								System.out.println("Press 5 to Search Book by Book Type");
								System.out.println("Press 6 to Get Book Id's ");
								System.out.println("Press 7 to Remove Any Book");
								System.out.println("Press 8 to Get No Of Books Available ");
								System.out.println("Press 9 Show Students ");
								System.out.println("Press 10 Show Requests");
								System.out.println("Press 11 to Go to Main");
								System.out.println("*****************************************");


								int choice1 = scanner.nextInt();
								switch (choice1) {
								case 1:
									System.out.println("Enter ID");
									int bookId = scanner.nextInt();
									System.out.println("Enter Book Name");
									String bookName = scanner.next();
									System.out.println("Enter Author");
									String bookAuthor = scanner.next();
									System.out.println("Enter Type");
									String bookCategory = scanner.next();
									System.out.println("Enter Publisher Name");
									String bookPubName = scanner.next();

									BookDTO bean2 = new BookDTO();
									bean2.setBookId(bookId);	
									bean2.setBookTitle(bookName);
									bean2.setBookType(bookCategory);
									bean2.setBookAuthor(bookAuthor);
									bean2.setBookPublisher(bookPubName);
									flag = service.addBook(bean2);
									if(flag) {
										System.out.println("Book is added successfully");
									} else {
										System.out.println("Book already exist");
									}
									break;
								case 2:
									System.out.println("Enter the Book Id to Update");
									int bookID = scanner.nextInt();
									System.out.println("Enter the Book AUthor to be updated");
									String author = scanner.next();
									flag = service.updateBook(bookID,author);
									if(flag)
									{
									System.out.println("Entered Book is Updated ");
									}
									else
									{
										System.out.println("Entered BookId is not available");
									}

									break;
								case 3:
									System.out.println("Enter the Book Name to Search Books");
									String book_Name = scanner.next();
									LinkedList<BookDTO> dto = service.searchBookTitle(book_Name);
									for (BookDTO bookDTO : dto) {
										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given book name");
										}
									}

									break;
								case 4:
									System.out.println("Enter the Book Author for required books");
									String book_Author = scanner.next();
									BookDTO bean5 = new BookDTO();
									bean5.setBookAuthor(book_Author);
									List<BookDTO> dto1 = service.searchBookAuthor(book_Author);
									for (BookDTO bookDTO : dto1) {
										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given author name");
										}
									}
									break;
								case 5:
									System.out.println("Enter the Book Type for required books");
									String book_Type = scanner.next();
									BookDTO bean6 = new BookDTO();
									bean6.setBookType(book_Type);
									List<BookDTO> dto2 = service.searchBookType(book_Type);
									for (BookDTO bookDTO : dto2) {
										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given Book Type");
										}
									}
									break;

								case 6:
									BookDTO bean7 = new BookDTO();
									LinkedList<Integer> dto3 = service.getBookIds();
									for (Integer integer : dto3) {

										if(integer != null ) {
											System.out.println(integer);
										}else {
											System.out.println("No books are present");
										}
									}
									break;

								case 7:
									System.out.println("Enter the book Id to delete any book");
									int book_Id = scanner.nextInt();
									
										flag = service.removeBook(book_Id);
										if(flag)
										{
											System.out.println("Book has been removed successfully");
										}
										else
										{
											System.out.println("Entered book is not available");
										}
									break;

								case 8:
									LinkedList<BookDTO> dto4 = service.getBooksInfo();
									for (BookDTO bookDTO : dto4) {

										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No Books are present");
										}
									}
									break;

					

								case 9:
									try {
										System.out.println("Students of Library are :");
										System.out.println("-------------------------------");

										List<String> userInfos = service.showStudents();
										for (String info : userInfos) {

											System.out.println("Student Name -------- " + info);
											System.out.println("-------------------------------");
										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
								case 10:
									try {
										System.out.println("Requests for Books are :");
										System.out.println("-------------------------------");

										List<Integer> requestInfos = service.showRequests();
										for (int info : requestInfos) {

											System.out.println("User id has requested book---------- " + info);
											System.out.println("-------------------------------");
										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
					
								case 11:
									performOperations();
									break;

								default:
									System.out.println("Invalid Choice");
									break;
								}
							}while(true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}

						break;
					case 3:
						performOperations();
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} while(true);

			case "User":
				UserService service1 = BookFactory.getUserService();
				do {
					System.out.println("Press 1 to Register as User ");
					System.out.println("Press 2 for User Login");
					System.out.println("Press 3 to main");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID :");
								regId1 = scanner.nextInt();
								validation.validatedId(regId1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Id should contains only digits");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);




						do {
							try {
								System.out.println("Enter Name :");
								regName1 = scanner.next();
								validation.validatedName(regName1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should contains only Alphabates");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);



						do {
							try {
								System.out.println("Enter Email :");
								regEmail1 = scanner.next();
								validation.validatedEmail(regEmail1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Email should be proper ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Password :");
								regPassword1 = scanner.next();
								validation.validatedPassword(regPassword1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						System.out.println();

						UserDTO user = new UserDTO();
						user.setUserRole(role);
						user.setUserId(regId1);
						user.setUserName(regName1);
						user.setUserEmail(regEmail1);
						user.setUserPassword(regPassword1);
						boolean check = service1.register(user);
						if(check) {
							System.out.println("Registered");
						} else {
							System.out.println("Email already exist");
						}
						break;
					case 2:
						do {	
							flag1 = false;
							System.out.println("Enter email");
							String email = scanner.next();
							System.out.println("Enter Password");
							String password = scanner.next();
							flag1 = service1.auth(email, password);
							}while(!flag1);
						try {
							
							System.out.println("Logged in");

							do {
								System.out.println("*****************************************");
								System.out.println("Press 1 to Search Book by Book Name");
								System.out.println("Press 2 to Search Book by Book Author");
								System.out.println("Press 3 to Search Book by Book Id");
								System.out.println("Press 4 to Get Book Id's ");
								System.out.println("Press 5 to get all books available");
								System.out.println("Press 6 to Request Book ");
								System.out.println("Press 7 to Return Book ");
								System.out.println("Press 8 to Go to Main");
								System.out.println("*****************************************");

								int choice1 = scanner.nextInt();
								switch (choice1) {
								case 1:
									System.out.println("Enter the Book Name to Search Books");
									String book_Name = scanner.next();
									List<BookDTO> dto = service1.searchBookTitle(book_Name);
									for (BookDTO bookDTO : dto) {
										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given book name");
										}
									}
									break;

								case 2:
									System.out.println("Enter the Book Author for required books");
									String book_Author = scanner.next();
									List<BookDTO> dto1 = service1.searchBookAuthor(book_Author);
									for (BookDTO bookDTO : dto1) {
										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given author name");
										}
									}
									break;

								case 3:
									System.out.println("Enter the Book Type for required books");
									String book_Type = scanner.next();
									List<BookDTO> dto2 = service1.searchBookType(book_Type);
									for (BookDTO bookDTO : dto2) {

										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No books are present by given Book Type");
										}
									}
									break;

								case 4:
									LinkedList<Integer> dto3 = service1.getBookIds();
									for (Integer integer : dto3) {
										if(integer != null ) {
											System.out.println(integer);
										}else {
											System.out.println("No books are present");
										}
									}
									break;

								case 5:
									LinkedList<BookDTO> dto4 = service1.getBooksInfo();
									for (BookDTO bookDTO : dto4) {

										if(bookDTO != null) {
											System.out.println("Book Is is "+bookDTO.getBookId());
											System.out.println("Book Name is " + bookDTO.getBookTitle());
											System.out.println("Book Author is " + bookDTO.getBookAuthor()); 
											System.out.println("Book Category is "+bookDTO.getBookType());
											System.out.println("Book Publisher is "+bookDTO.getBookPublisher());
										}else {
											System.out.println("No Books are present");
										}
									}
									break;
								case 6:
									System.out.println("Enter book id");
									int bookId = scanner.nextInt();
									// System.out.println("Enter Book Name");
									// String bookName = scanner.next();
									BookDTO dto5 = new BookDTO();
									dto5.setBookId(bookId);
									// bookBean.setBookTitle(bookName);

									System.out.println("Enter user id");
									int userId = scanner.nextInt();
									// System.out.println("Enter User Name");
									// String userName = scanner.next();
									UserDTO bean8 = new UserDTO();
									bean8.setUserId(userId);
									// userBean.setUserName(userName);

									try {
										boolean request = service1.requestBook(bookId, userId);
										if(request)
										{
											System.out.println("Book has been successfully issued");
										}
									} catch (Exception e) {

										System.out.println("Enter valid data or Invalid Request");
									}
									break;
								case 7:
									System.out.println("Returning a book:");
									System.out.println("------------------");
									System.out.println("Enter Book Id");
									int book = scanner.nextInt();
									System.out.println("Enter User Id");
									int id  = scanner.nextInt();

									try {
										boolean requestInfo = service1.returnBook(book,id);
										if(requestInfo)
										{
											System.out.println("Book has been successfully returned");
										}
										else
										{
											System.out.println("Book has not returned successfully");
										}
										
									} catch (Exception e) {
										System.out.println("Invalid Return");
									}
									break;

								case 8:
									performOperations();

								default:
									System.out.println("Invalid Choice");
									break;
								}
							}while(true);
						}catch(Exception e) {
							e.printStackTrace();
						}
					case 3:
						performOperations();
						break;

					default:
						System.out.println("Invalid Role");
					}
				} while(true);
			}
		}while(true);
	}
	
	public void run() {
		System.out.println("-----------------WELCOME TO LIBRARY MANAGEMENT SYSTEM----------------------");
		performOperations();
		}
}





