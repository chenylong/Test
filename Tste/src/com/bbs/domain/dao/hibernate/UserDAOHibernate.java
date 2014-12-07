package com.bbs.domain.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bbs.domain.dao.UserDAO;
import com.bbs.domain.model.User;
import com.bbs.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bbs.domain.dao.DAO;
import com.bbs.util.QueryInfo;

public class UserDAOHibernate extends BaseDAOHibernate implements UserDAO {

	public User getUserbyUsername(String username) {
		try{
			Session s = HibernateUtil.currentSession();
			String hqlstr = "from User as user where user.username=:name";
			Query query = s.createQuery(hqlstr);
			query.setString("name",username);
			List result = query.list();
			if(result != null && result.size() > 0)
			{
				return (User)result.get(0);
			}
		}catch(HibernateException e){			
			
			e.printStackTrace();
		}
		return null;
	}

	public void saveUser(User user) {
		try{
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			s.saveOrUpdate(user);
			HibernateUtil.commitTransaction();
		}catch(HibernateException e)
		{
			log.debug(e);
		}

	}

	public User getUserByID(Long userid)
	{
		try{
			Session s = HibernateUtil.currentSession();
			return (User)s.load(User.class,userid);
		}catch(HibernateException e)
		{
			log.debug(e);
		}
		return null;
	}
	
	public void delete(User user) {
		try{
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			User temp = getUserByID(user.getId());
			if(temp != null)
				s.delete(user);
			HibernateUtil.commitTransaction();
		}catch(HibernateException e)
		{
			log.debug(e);
		}

	}

	public List getAll() {
		try{
			Session s = HibernateUtil.currentSession();
			String hqlstr = "from User";
			Query query = s.createQuery(hqlstr);
			List result = query.list();
			return result;
		}catch(HibernateException e)
		{
			log.debug(e);
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
			whereClauseBuff.append(" c.username like '%");
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
			hqlPart = "from User c where " + whereClause;
		}else{
			hqlPart = "from User c" ;
		}
		
		try {
			Session s = HibernateUtil.currentSession();
			if (queryInfo != null
					&& (queryInfo.getLimit() != null || queryInfo.getOffset() != null)) {
				String hqlForTotal = "select count(c.id) " + hqlPart;				
				
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
			//Collection coll = new PartialCollection(list, total);
		} catch (HibernateException e) {
			log.fatal(e);
			// return 0;
		}
		return new PartialCollection(list, total);
	}
	
	

	public List getObjects(Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

	public void removeObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub

	}

	public void removeObject(Object po) {
		// TODO Auto-generated method stub

	}

	public void removeShc(Long objectid, int type) {
		// TODO Auto-generated method stub

	}

	public void removePl(Long objectid, int type) {
		// TODO Auto-generated method stub

	}

	public void removeTj(Long objectid, int type) {
		// TODO Auto-generated method stub

	}

}
