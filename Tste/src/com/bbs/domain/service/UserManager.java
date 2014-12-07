package com.bbs.domain.service;

import com.bbs.domain.model.*;
import com.bbs.domain.service.UserExsitsException;

import java.util.*;

public interface UserManager {
	public User getUserByName ( String username );
	
	public void saveUser ( User user)throws UserExsitsException;;
	
	public User getUserByID ( Long userid );
	
	public void delete ( User user);
	
	public User getByID(Long id);
	
	public List getAll ( );

}
