package com.bbs.domain.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import com.bbs.util.*;

import com.bbs.domain.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bbs.domain.dao.DAO;
import com.bbs.util.QueryInfo;
import com.bbs.domain.dao.SubjectDAO;

public class SubjectDAOHibernate extends BaseDAOHibernate implements SubjectDAO {

	public List getAllSubject() {
		
		try{
			Session session = HibernateUtil.currentSession();
			String hqlstr = "from Subject";
			Query query = session.createQuery(hqlstr);
			List result = query.list();
			return result;
		}catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}			
	}
	
	
	public  Subject getByID(Long subjectid)
	{
		try{
			Session session = HibernateUtil.currentSession();
			Subject subject = (Subject)session.load(Subject.class,subjectid);
			if(subject != null)
				return subject;
		}catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public void save(Subject subject)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			session.saveOrUpdate(subject);	
			HibernateUtil.commitTransaction();
		}catch(HibernateException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void delete(Subject subject)
	{
		try{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			session.delete(subject);
			HibernateUtil.commitTransaction();
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	
public PartialCollection getResult(QueryInfo queryInfo) {
		
		
		String whereClause = new String();
		String orderByClause = new String();
		if (queryInfo != null) {
			whereClause = queryInfo.getWhereClause();
			orderByClause = queryInfo.getOrderByClause();
			if (whereClause != null && whereClause.length() != 0) {
			//	whereClause =  whereClause;
			} else {
				whereClause = new String();
			}
			if (orderByClause != null && orderByClause.length() != 0) {
				orderByClause = " order by " + orderByClause;
			} else {
				orderByClause = new String();
			}
		}
		
		String keyword = (String)queryInfo.getQueryParameters().get("keyword");
		if ( keyword != null ){
			StringBuffer whereClauseBuff = new StringBuffer("");
			whereClauseBuff.append(" c.name like '%");
			whereClauseBuff.append( keyword );
			whereClauseBuff.append("%' ");
			if (whereClause.length()==0){
				whereClause = whereClauseBuff.toString();
			}else{
				whereClauseBuff.append(")");
				whereClause += " and (" + whereClauseBuff.toString();
			}
		}

		List list = null;
		Integer total = null;
		ArrayList args = new ArrayList();
		String hqlPart = new String();
		if (whereClause != null && whereClause.length() != 0){
			hqlPart = "from Subject c where " + whereClause;
		}else{
			hqlPart = "from Subject c" ;
		}
		
		try {
			Session s = HibernateUtil.currentSession();
			if (queryInfo != null
					&& (queryInfo.getLimit() != null || queryInfo.getOffset() != null)) {
				String hqlForTotal = "select count(c.id) " + hqlPart;
				
				System.out.println(hqlForTotal);
				
				total = (Integer) findUniqueResult(s, hqlForTotal, args
						.toArray());
				if (total == null) {
					total = new Integer(0);
				}
			}
			
			
			if (total == null || total.intValue() > 0) {
				String hql = "select c " + hqlPart + orderByClause;
				list = executeFind(s, hql, queryInfo, args.toArray());
				if (total == null) {
					total = new Integer(list.size());
				}
			} else {
				list = new ArrayList();
			}
		} catch (HibernateException e) {
			log.fatal(e);
		}
		return new PartialCollection(list, total);
	}
	
	
}
