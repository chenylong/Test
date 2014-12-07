package com.bbs.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.bbs.util.QueryInfo;
import com.bbs.util.PartialCollection;

public interface PageDAO extends DAO, Serializable {
	
	//public List getResult ( int start , int maxResult);
	
	public PartialCollection getResult ( QueryInfo queryInfo );
	
	//public int getTotalRows () ;
}
