package com.bbs.domain.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.bbs.domain.dao.TopicDAO;
import com.bbs.util.PartialCollection;
import com.bbs.util.QueryInfo;
import com.bbs.domain.dao.TopicDAO;
import com.bbs.domain.model.Subject;
import com.bbs.domain.model.Topic;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;
import com.bbs.util.*;
import com.bbs.domain.model.*;
import com.bbs.util.*;

public class TopicDAOHibernate extends BaseDAOHibernate implements TopicDAO {

	public List getAllBySubject(Subject subject) {
		try{
			Session session = HibernateUtil.currentSession();
			String hqlstr = "from Topic topic where topic.subject=:subject";
			Query query = session.createQuery(hqlstr);
			query.setParameter("subject",subject);
			List result = query.list();
			return result;
		}catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public void delete(Topic topic)
	{
		try{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			session.delete(topic);
			HibernateUtil.commitTransaction();
			}catch(HibernateException e)
			{
				e.printStackTrace();
			}
	}
	
	public void save(Topic topic){
		try{
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			s.saveOrUpdate(topic);
			HibernateUtil.commitTransaction();
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	
	public Topic getByID(Long topicid)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			Topic temp = (Topic)session.load(Topic.class,topicid);
			HibernateUtil.commitTransaction();
			return temp;
		}catch(HibernateException e)
		{
			e.printStackTrace();
		}
		return null;
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
			whereClauseBuff.append(" c.title like '%");
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
			hqlPart = "from Topic c where " + whereClause;
		}else{
			hqlPart = "from Topic c" ;
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


	
	
	

}
