package com.capgemini.library_management_system_collections.database;

import java.util.LinkedList;

import com.capgemini.library_management_system_collections.dto.AdminDTO;
import com.capgemini.library_management_system_collections.dto.BookDTO;
import com.capgemini.library_management_system_collections.dto.RequestDTO;
import com.capgemini.library_management_system_collections.dto.UserDTO;

public class DataBase {
	
	public static final LinkedList<AdminDTO> ADMINS= new LinkedList<AdminDTO>();
	public static final LinkedList<UserDTO> STUDENTS= new LinkedList<UserDTO>();
	public static final LinkedList<BookDTO> BOOKS= new LinkedList<BookDTO>();
	public static final LinkedList<RequestDTO> REQUESTS= new LinkedList<RequestDTO>();
	
	}
