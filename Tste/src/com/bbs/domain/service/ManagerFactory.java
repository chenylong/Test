package com.bbs.domain.service;

import com.bbs.domain.service.impl.*;

public class ManagerFactory {
	
	public static UserManager createUserManager(){
		return new UserManagerImpl();		
	}
	
	public static SubjectManagerImpl createSubjectManager(){
		return new SubjectManagerImpl();
	}
	
	public static TopicManagerImpl createTopicManager()
	{
		return new TopicManagerImpl();
	}
	
	public static ResponseManagerImpl createReseponseManager()
	{
		return new ResponseManagerImpl();
	}
	
	
	
}
