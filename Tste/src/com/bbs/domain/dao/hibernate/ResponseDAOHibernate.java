package com.bbs.domain.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.bbs.domain.dao.ResponseDAO;
import com.bbs.domain.model.Response;
import com.bbs.domain.model.Topic;
import com.bbs.util.PartialCollection;
import com.bbs.util.QueryInfo;
import com.bbs.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class ResponseDAOHibernate extends BaseDAOHibernate implements
		ResponseDAO {

	public List getAllByTopicid(Topic topic) {
		try
		{
			Session session = HibernateUtil.currentSession();
			String hqlstr = "from response  as res where res.topic=:topic";
			Query query = session.createQuery(hqlstr);
			query.setParameter("topic",topic);
			List result = query.list();
			return result;
		}catch (HibernateException e)
		{
			e.printStackTrace();			
		}
		return null;
	}

	public void save(Response response) {
		try
		{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			session.saveOrUpdate(response);
			HibernateUtil.commitTransaction();
			
		}catch(HibernateException e)
		{
			e.printStackTrace();
		}

	}

	public void delete(Response response) {
		try
		{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			if(response != null)
				session.delete(response);
			HibernateUtil.commitTransaction();
		}catch(HibernateException e)
		{
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
			whereClauseBuff.append(" c.content like '%");
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
			hqlPart = "from Response c where " + whereClause;
		}else{
			hqlPart = "from Response c" ;
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
			// If we don't have any info about the total number of results yet
			// or
			// we know that there's something that will be found, then fetch
			// data
			
			if (total == null || total.intValue() > 0) {
				String hql = "select c " + hqlPart + orderByClause;
				list = executeFind(s, hql, queryInfo, args.toArray());
				if (total == null) {
					total = new Integer(list.size());
				}
			} else {
				list = new ArrayList();
			}
			//Collection coll = new PartialCollection(list, total);
		} catch (HibernateException e) {
			log.fatal(e);
			// return 0;
		}
		return new PartialCollection(list, total);
		
	}

	public Response getById(Long id)
	{
		try
		{
			Session s = HibernateUtil.currentSession();
			Response temp = (Response)s.load(Response.class,id);
			return temp;
		}catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
