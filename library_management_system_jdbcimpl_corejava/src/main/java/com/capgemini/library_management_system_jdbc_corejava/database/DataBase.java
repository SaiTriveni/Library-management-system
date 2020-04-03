package com.capgemini.library_management_system_jdbc_corejava.database;

import java.util.LinkedList;

import com.capgemini.library_management_system_jdbc_corejava.dto.AdminDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.BookDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.RequestDTO;
import com.capgemini.library_management_system_jdbc_corejava.dto.UserDTO;

public class DataBase {
	
	public static final LinkedList<AdminDTO> ADMINS= new LinkedList<AdminDTO>();
	public static final LinkedList<UserDTO> STUDENTS= new LinkedList<UserDTO>();
	public static final LinkedList<BookDTO> BOOKS= new LinkedList<BookDTO>();
	public static final LinkedList<RequestDTO> REQUESTS= new LinkedList<RequestDTO>();
	
	}
