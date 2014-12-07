package com.bbs.web.page;

import java.io.Serializable;

import com.bbs.util.QueryInfo;

public class Pager implements Serializable {
	
	//total records
	private int totalRows;
	
	//total pages = totalRows%perPage==0?totalRows/perPage:...
	private int totalPages;
	
	private int perPage = 10;
	
	private int curPage;
	
	private int curCountInTotal;
	
	private QueryInfo queryInfo = new QueryInfo() ;
	
	public Pager(){
		queryInfo.setLimit( new Integer(perPage));
		queryInfo.setOffset( new Integer(0));
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	

	public boolean isFirstPage() {
		return this.curPage == 1 ? true : false ;
	}

	

	public boolean isLastPage() {
		return this.curPage == this.totalPages ? true:false ;
	}

	

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		this.queryInfo.setLimit(new Integer(perPage));
	}

	

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	
	
	public void refresh( ){
		this.totalPages = this.totalRows%this.perPage == 0?
				this.totalRows/this.perPage:this.totalRows/this.perPage+1;
		//this.curPage = 1;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurCountInTotal() {
		return curCountInTotal;
	}

	public void setCurCountInTotal(int curCountInTotal) {
		this.curCountInTotal = curCountInTotal;
	}

	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
		queryInfo.setLimit( new Integer(perPage));
		queryInfo.setOffset( new Integer(0));
	}
	
	
	
}
