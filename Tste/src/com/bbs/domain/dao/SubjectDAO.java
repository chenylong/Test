package com.bbs.domain.dao;

import java.util.List;
import com.bbs.domain.model.*;

public interface SubjectDAO extends DAO,PageDAO {
	
	public List getAllSubject();
	
	public Subject getByID(Long subjectid);
	
	public void delete(Subject subject);
	
	public void save(Subject subject);
	


}
