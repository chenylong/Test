package com.bbs.domain.service;

import java.io.Serializable;
import com.bbs.domain.dao.PageDAO;

public interface PageBaseManager extends Serializable {
	
	public PageDAO getPageDAO ();
}
