package com.bbs.domain.service;

import com.bbs.domain.model.*;
import java.util.*;

public interface ResponseManager {
	
	public  List getAllByTopic(Topic topic);
	
	public void save(Response response);
	
	public void delete(Response response);
	
	public Response getById(Long id);

}
