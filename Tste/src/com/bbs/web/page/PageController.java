package com.bbs.web.page;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.bbs.domain.service.PageManager;

public class PageController implements Serializable {
	
	private Pager pager ;
	
	private PageManager pageMgr;
	
	public PageController(){
		this.pager = new Pager();
		this.pageMgr = new PageManager();
	}
	
	public List toNext(){
		//List results = this.pageMgr.update( pager , true );
		this.pager.setCurPage( this.pager.getCurPage()+1);
		int offSet = this.pager.getQueryInfo().getOffset().intValue();
		this.pager.getQueryInfo().setOffset(new Integer(offSet + pager.getPerPage()));
		return pageMgr.update( pager );
	}
	
	public List toPrevious(){
		//List results = this.pageMgr.update( pager , false );
		this.pager.setCurPage( this.pager.getCurPage()-1);
		int offSet = this.pager.getQueryInfo().getOffset().intValue();
		this.pager.getQueryInfo().setOffset(new Integer(offSet - pager.getPerPage()));
		return pageMgr.update( pager );
	}
	
	public List toLast(){
		int last = pager.getTotalRows()%pager.getPerPage();
		List results = null ;
		int offSet = pager.getQueryInfo().getOffset().intValue();
		/*
		if (last == 0 ) results = pageMgr.update( pager ,pager.getTotalRows()-pager.getPerPage() );
		else
			results = pageMgr.update( pager ,pager.getTotalRows()- last);
		pager.setCurPage( pager.getTotalPages());
		return results;
		*/
		if ( last == 0 ){
			offSet = pager.getTotalRows()-pager.getPerPage() ;
		}else{
			offSet = pager.getTotalRows()- last ;
		}
		pager.getQueryInfo().setOffset(new Integer( offSet ));
		results = pageMgr.update( pager );
		pager.setCurPage( pager.getTotalPages());
		return results;
	}
	
	public List toFirst(){
		pager.getQueryInfo().setOffset( new Integer(0) );
		List results = pageMgr.update( pager  );
		this.pager.setCurPage( 1 );
		return results;
	}
	
	public List toAnyPage( int page ){
		pager.getQueryInfo().setOffset( new Integer((page-1)*pager.getPerPage()));
		List results = pageMgr.update( pager  );
		this.pager.setCurPage( page );
		return results;
	}

	public PageManager getPageMgr() {
		return pageMgr;
	}

	public void setPageMgr(PageManager pageMgr) {
		this.pageMgr = pageMgr;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	
}
