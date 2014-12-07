package com.bbs.domain.dao;

import com.bbs.domain.model.*;
import java.util.*;

public interface UserDAO extends DAO, PageDAO  {
	
	public User getUserbyUsername(String username);
	public void saveUser(User user);
	public void delete(User user);
	public List getAll();
	public User  getUserByID(Long userid);
	
	

}
