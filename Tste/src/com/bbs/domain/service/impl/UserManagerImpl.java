package com.bbs.domain.service.impl;

import java.util.List;

import com.bbs.domain.model.User;
import com.bbs.domain.service.UserExsitsException;
import com.bbs.domain.service.UserManager;
import com.bbs.domain.dao.PageDAO;
import com.bbs.domain.dao.UserDAO;
import com.bbs.domain.dao.DAOFactory;
import com.bbs.domain.service.PageBaseManager;

public class UserManagerImpl implements UserManager,PageBaseManager {
     private static UserDAO dao = DAOFactory.createUserDAO();

     public User getUserByName(String username) {
		return dao.getUserbyUsername(username);
	}

	public void saveUser(User user) throws UserExsitsException {
		if(user.getId() != null){
			dao.saveUser(user);
		     return ;
			}
		User temp = dao.getUserbyUsername(user.getUsername());
		if(temp == null)
		{
			dao.saveUser(user);
			return;
		}
		throw new UserExsitsException();

	}

	public User getUserByID(Long userid) {
		return dao.getUserByID(userid);
	}

	public void delete(User user) {
		dao.delete(user);

	}

	public List getAll() {
		return dao.getAll();
	}
	
	public User getByID(Long id)
	{
		return dao.getUserByID(id);
	}
	
	public PageDAO getPageDAO() {
		return dao;
	}

}
