package com.bbs.domain.dao;

import java.util.*;
import com.bbs.domain.model.*;

public interface TopicDAO extends DAO, PageDAO {
		public List getAllBySubject(Subject subjecit);
		
		public void delete(Topic topic);
		
		public void save(Topic topic);
		
		public Topic getByID(Long topicid);
		
		
		
}
