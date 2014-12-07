package com.bbs.domain.service.impl;

import java.util.List;
import com.bbs.domain.dao.*;

import com.bbs.domain.model.*;

import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.SubjectManager;

public class SubjectManagerImpl implements SubjectManager,PageBaseManager {

     SubjectDAO dao = DAOFactory.createSujectDAO();	
	
	public List getAllSubject() {
		return dao.getAllSubject();		
	}
	
	public Subject getByID(Long subjectid)
	{
		return dao.getByID(subjectid);
	}
	
	public void delete(Subject subject)
	{
		dao.delete(subject);
	}

	public PageDAO getPageDAO() {
		return dao;
	}
	
	public void save(Subject subject)
	{
		dao.save(subject);
	}
	
}
