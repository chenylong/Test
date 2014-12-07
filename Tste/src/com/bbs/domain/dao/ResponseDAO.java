package com.bbs.domain.dao;

import com.bbs.domain.model.*;
import java.util.List;

public interface ResponseDAO extends PageDAO, DAO {

	
	public  List getAllByTopicid(Topic topic);
	
	public void save(Response response);
	
	public void delete(Response response);
	
	public Response getById(Long id);
}
