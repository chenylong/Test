package com.bbs.domain.service.impl;

import java.util.List;

import com.bbs.domain.dao.PageDAO;
import com.bbs.domain.model.Response;
import com.bbs.domain.model.Topic;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.ResponseManager;
import com.bbs.domain.dao.*;

public class ResponseManagerImpl implements PageBaseManager, ResponseManager {
	ResponseDAO dao = DAOFactory.createResponseDAO();
	
	public PageDAO getPageDAO() {
		return dao;
	}

	public List getAllByTopic(Topic topic) {
		return dao.getAllByTopicid(topic);
	}

	public void save(Response response) {
		dao.save(response);
	}

	public void delete(Response response) {
		dao.delete(response);
	}
	
	public Response getById(Long id)
	{
		return dao.getById(id);
	}

}
