package com.bbs.domain.service;

import java.util.*;
import com.bbs.util.*;
import com.bbs.domain.model.*;

public interface TopicManager {
	
	public  List getAllBySubject(Subject subject);
	
	public void save(Topic topic);
	public void delete(Topic topic);
	
	public Topic getByID(Long topicid);
	

}
