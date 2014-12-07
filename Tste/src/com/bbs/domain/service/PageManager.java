package com.bbs.domain.service;

import java.io.Serializable;
import java.util.List;

import com.bbs.web.page.Pager;
import com.bbs.domain.dao.PageDAO;
import com.bbs.util.QueryInfo;
import com.bbs.util.PartialCollection;

public class PageManager implements Serializable {
	
	protected PageDAO pageDAO ;
	
	public PageManager(){
		
	}
	public List update( Pager pager ,boolean isNext){
		/*
		if (isNext )
			return update ( pager , pager.getCurCountInTotal()+pager.getPerPage());
		return update (pager , pager.getCurCountInTotal() - pager.getPerPage());
		*/
		int offSet = pager.getQueryInfo().getOffset().intValue();
		if ( isNext ){
			pager.getQueryInfo().setOffset(new Integer(offSet + pager.getPerPage()) );
		}else{
			pager.getQueryInfo().setOffset(new Integer(offSet - pager.getPerPage()) );
		}
		return null;
	}
	
    public List update( Pager pager , int startRow){
    	
		return null;
	}
    
    public List update ( Pager pager ){
    	PartialCollection results = pageDAO.getResult( pager.getQueryInfo() );
    	pager.setTotalRows( results.getTotal().intValue() );
    	pager.refresh();
    	return results.asList();
    }
	public PageDAO getPageDAO() {
		return pageDAO;
	}
	public void setPageDAO(PageDAO pageDAO) {
		this.pageDAO = pageDAO;
	}
    
    
}
