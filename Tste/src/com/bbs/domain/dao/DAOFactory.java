package com.bbs.domain.dao;

import com.bbs.domain.dao.hibernate.*;

public class DAOFactory {
	
	public static UserDAO createUserDAO()
	{
		return new UserDAOHibernate();
	}
	
	public static SubjectDAOHibernate createSujectDAO()
	{
		return new SubjectDAOHibernate();
	}
	
	public static TopicDAOHibernate createTopicDAO(){
		return new TopicDAOHibernate();
	}
	
	public static ResponseDAOHibernate createResponseDAO(){
		return new ResponseDAOHibernate();
	}

}
