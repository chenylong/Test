package com.bbs.domain.service.impl;

import java.util.List;

import com.bbs.domain.dao.PageDAO;
import com.bbs.domain.model.Subject;
import com.bbs.domain.model.Topic;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.TopicManager;
import com.bbs.domain.dao.*;

public class TopicManagerImpl implements TopicManager, PageBaseManager {

	private static TopicDAO dao = DAOFactory.createTopicDAO();
	
	public List getAllBySubject(Subject subject) {
		return dao.getAllBySubject(subject);
	}

	public void save(Topic topic) {
		dao.save(topic);

	}

	public void delete(Topic topic) {
		dao.delete(topic);

	}

	public PageDAO getPageDAO() {
		return dao;
	}
	
	public Topic getByID(Long topicid)
	{
		return dao.getByID(topicid);
	}

	
	
}
