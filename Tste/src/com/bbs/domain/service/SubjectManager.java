package com.bbs.domain.service;

import java.util.List;
import com.bbs.domain.model.*;

public interface SubjectManager {
	public List getAllSubject();
	public Subject getByID(Long subjectid);
	public void delete(Subject subject);
	public void save(Subject subject);

}
