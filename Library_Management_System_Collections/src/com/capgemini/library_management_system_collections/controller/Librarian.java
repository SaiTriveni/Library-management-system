package com.capgemini.library_management_system_collections.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.library_management_system_collections.dto.AdminDTO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;
import com.capgemini.library_management_system_collections.exception.ValidationException;
import com.capgemini.library_management_system_collections.factory.BookFactory;
import com.capgemini.library_management_system_collections.service.AdminService;
import com.capgemini.library_management_system_collections.service.UserService;
import com.capgemini.library_management_system_collections.validation.ValidationAdminStudent;

public class Librarian extends Thread{


	public void performOperations() {

		boolean flag = false;

		int regId = 0; 
		String regName = null; 
		String regEmail = null;
		String regPassword = null;

		int regId1 = 0; 
		String regName1 = null; 
		String regEmail1 = null; 
		String regPassword1 = null;


		ValidationAdminStudent validation = new ValidationAdminStudent();

		Scanner scanner = new Scanner(System.in);
		int i = 0;
		do {
			
			do {
				try {

					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					System.out.println("Press 1 for Admin Page");
					System.out.println("Press 2 for Student Page");
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

					i = scanner.nextInt();
					 flag = true;
				}catch(InputMismatchException e) {
					System.out.println("Enter only Integers");
					flag=false;
				}
			}while(!flag);


			switch(i) {
			case 1:
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
						System.out.println("Enter email");
						String email = scanner.next();
						System.out.println("Enter Password");
						String password = scanner.next();
						try {
							AdminDTO authBean = service.auth(email, password);
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
								System.out.println("Press 9 to Issue Book");
								System.out.println("Press 10 Show Students ");
								System.out.println("Press 11 Show Requests");
								System.out.println("Press 12 Receive Returned Books");
								System.out.println("Press 13 to Go to Main");
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
									System.out.println("Enter Category");
									String bookCategory = scanner.next();
									System.out.println("Enter Publisher Name");
									String bookPubName = scanner.next();

									BookDTO bean2 = new BookDTO();
									bean2.setBookId(bookId);	
									bean2.setBookTitle(bookName);
									bean2.setBookType(bookCategory);
									bean2.setBookAuthor(bookAuthor);
									bean2.setBookPublisher(bookPubName);
									boolean check2 = service.addBook(bean2);
									if(check2) {
										System.out.println("Book Added");
									} else {
										System.out.println("Book already exist");
									}
									break;
								case 2:
									System.out.println("Enter the Book Id to Update");
									int bookID = scanner.nextInt();
									BookDTO bean3 = new BookDTO();
									bean3.setBookId(bookID);
									int update = service.updateBook(bookID);
									if(update != 0) {
										System.out.println("It is Updated ");
									}else {
										System.out.println("It is not updated ");
									}

									break;
								case 3:
									System.out.println("Enter the Book Name to Search Books");
									String book_Name = scanner.next();
									BookDTO bean4 = new BookDTO();
									bean4.setBookTitle(book_Name);
									List<BookDTO> dto = service.searchBookTitle(book_Name);
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
									if(book_Id == 0) {
										System.out.println("Enter the Value");
									}else {
										BookDTO bean8 = new BookDTO();
										bean8.setBookId(book_Id);
										boolean check3 = service.removeBook(book_Id);
										if(check3) {
											System.out.println("Book is Deleted");
										}else {
											System.out.println("Book is not Deleted");
										}
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
									UserDTO userDto = new UserDTO();
									BookDTO bookDto = new BookDTO();
									System.out.println("Enter Book Id");
									int bId = scanner.nextInt();
									System.out.println("Enter User Id");
									int uId = scanner.nextInt();
									bookDto.setBookId(bId);
									userDto.setUserId(uId);
									try {
										boolean isIssued = service.bookIssue(userDto, bookDto);
										if (isIssued) {
											System.out.println("Book Issued");
										} else {
											System.out.println("Book cannot be issued");
										}

									} catch (Exception e) {
										System.out.println("Invalid data request book cannot be issued");
									}
									break;

								case 10:
									try {
										System.out.println("Students of Library are :");
										System.out.println("-------------------------------");

										List<UserDTO> userInfos = service.showStudents();
										for (UserDTO info : userInfos) {

											System.out.println("Student id ---------- " + info.getUserId());
											System.out.println("Student Name -------- " + info.getUserName());
											System.out.println("Student Email------ " + info.getUserEmail());
											System.out.println(
													"Student No Of Books Borrowed ------- " + info.getUserBooksBorrowed());
											System.out.println("-------------------------------");
										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
								case 11:
									try {
										System.out.println("Requests for Books are :");
										System.out.println("-------------------------------");

										List<RequestDTO> requestInfos = service.showRequests();
										for (RequestDTO info : requestInfos) {

											System.out.println("Book id ---------- " + info.getBookInfo().getBookId());
											System.out.println("Book Name -------- " + info.getBookInfo().getBookTitle());
											System.out.println("Student id----------- " + info.getStudentInfo().getUserId());
											System.out.println("Student Name -------- " + info.getStudentInfo().getUserName());
											System.out.println("Book Issued ------" + info.isIssued());
											System.out.println("Book Returned------" + info.isReturned());
											System.out.println("-------------------------------");
										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
								case 12:
									System.out.println("Receive Returned Book");
									System.out.println("-----------------------");
									System.out.println("Enter Student Id");
									int user1 = scanner.nextInt();
									System.out.println("Enter Book Id");
									int book1 = scanner.nextInt();

									UserDTO student = new UserDTO();
									BookDTO book = new BookDTO();
									student.setUserId(user1);
									book.setBookId(book1);
									boolean isReceive = service.isBookReceived(student, book);
									if(isReceive) {
										System.out.println(" Received Returned book");
									}else {
										System.out.println("Invalid ! Admin unable to receive");
									}

									break;
								case 13:
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

			case 2:
				UserService service1 = BookFactory.getStudentService();
				do {
					System.out.println("Press 1 to Register as Student ");
					System.out.println("Press 2 for Student Login");
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
						System.out.println("Enter email");
						String email = scanner.next();
						System.out.println("Enter Password");
						String password = scanner.next();
						try {
							UserDTO studentBean = service1.auth(email, password);
							System.out.println("Logged in");

							do {
								System.out.println("*****************************************");
								System.out.println("Press 1 to Search Book by Book Name");
								System.out.println("Press 2 to Search Book by Book Author");
								System.out.println("Press 3 to Search Book by Book Id");
								System.out.println("Press 4 to Get Book Id's ");
								System.out.println("Press 5 to Get No Of Books Available ");
								System.out.println("Press 6 to Request Book ");
								System.out.println("Press 7 to Return Book ");
								System.out.println("Press 8 to Go to Main");
								System.out.println("*****************************************");

								int choice1 = scanner.nextInt();
								switch (choice1) {
								case 1:
									System.out.println("Enter the Book Name to Search Books");
									String book_Name = scanner.next();
									BookDTO bean4 = new BookDTO();
									bean4.setBookTitle(book_Name);
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
									BookDTO bean5 = new BookDTO();
									bean5.setBookAuthor(book_Author);
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
									BookDTO bean6 = new BookDTO();
									bean6.setBookType(book_Type);
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
									BookDTO bean7 = new BookDTO();
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
										RequestDTO request = service1.requestBook(bean8, dto5);
										System.out.println("Request placed to admin");
										System.out.println("Student Id-----" + request.getStudentInfo().getUserId());
										System.out.println("Student name---" + request.getStudentInfo().getUserName());
										System.out.println("Book Id-----" + request.getBookInfo().getBookId());
										System.out.println("Book Name---" + request.getBookInfo().getBookTitle());

									} catch (Exception e) {

										System.out.println("Enter valid data or Invalid Request");
									}
									break;
								case 7:
									System.out.println("Returning a book:");
									System.out.println("------------------");
									System.out.println("Enter Student Id");
									int id  = scanner.nextInt();
									System.out.println("Enter Book Id");
									int book = scanner.nextInt();
									BookDTO dto6 = new BookDTO();
									UserDTO bean9 = new UserDTO();
									bean9.setUserId(id);
									dto6.setBookId(book);

									try {
										RequestDTO requestInfo = service1.returnBook(bean9, dto6);
										System.out.println("Book is Returning to Admin");
										System.out.println("User Id ------"+requestInfo.getStudentInfo().getUserId());
										System.out.println("Book Id ------"+requestInfo.getBookInfo().getBookId());
										System.out.println("Is Returning --"+requestInfo.isReturned());

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
						System.out.println("Invalid Option");
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





