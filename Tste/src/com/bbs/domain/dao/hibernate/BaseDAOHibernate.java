package com.bbs.domain.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bbs.domain.dao.DAO;
import com.bbs.util.QueryInfo;
import com.bbs.util.HibernateUtil;


/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations.</p>
 *
 * <p><a href="BaseDAOHibernate.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseDAOHibernate  implements DAO {
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * @see org.appfuse.dao.DAO#saveObject(java.lang.Object)
     */
    public void saveObject(Object o) {
    	try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			s.saveOrUpdate( o );
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			log.fatal(e);
			HibernateUtil.rollbackTransaction();
		}finally{
			//HibernateUtil.closeSession();
		}
    }

    /**
     * @see org.appfuse.dao.DAO#getObject(java.lang.Class, java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
    	try {
			Session s = HibernateUtil.currentSession();
			return s.load(clazz , id);
		} catch (HibernateException e) {
			log.fatal(e);
			return null;
		}
    }

    /**
     * @see org.appfuse.dao.DAO#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        return null;
    }

    /**
     * @see org.appfuse.dao.DAO#removeObject(java.lang.Class, java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
    	try {
			Session s = HibernateUtil.currentSession();
			Object po = s.load(clazz ,id);
			HibernateUtil.beginTransaction();
			s.delete( po );
		} catch (HibernateException e) {
			log.fatal(e);
		}		
    }
    
    public void removeObject(Object po) {
    	try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			s.delete( po );
		} catch (HibernateException e) {
			log.fatal(e);
		}		
    }
	/**
	 * Returns unique result matching specified query using <code>org.springframework.orm.hibernate.HibernateCallback</code>
     * with arguments and their types
	 *
	 * @param hql         Query to execute
	 * @param args        Arguments to set
	 * @param types       Types of arguments
	 * @param cacheable   <code>true</code> if the query is cacheable
	 * @param cacheRegion region of cache. E.g. one that used in configuration file of EHCahce (ehcache.xml)
	 * @return Unique result matching specified query
	 */
	public Object findUniqueResult(final String hql, final Object[] args, final Type[] types, final boolean cacheable, final String cacheRegion) {
		return null ;
	}
	
	public List executeFind(Session session ,final String hql, final QueryInfo queryInfo, final Object[] args, final Type[] types, final boolean cacheable, final String cacheRegion){
		Query query = session.createQuery(hql);
		query.setCacheable(cacheable);
		if ( cacheRegion != null ) {
			query.setCacheRegion(cacheRegion);
		}
		if ( args != null ) {
			for ( int i = 0; i < args.length; i++ ) {
				Object arg = args[i];
				Type type = null;
				if ( types != null && i < types.length ) {
					type = types[i];
				}
				if ( type == null ) {
					query.setParameter(i, arg);
				} else {
					query.setParameter(i, arg, type);
				}
			}
		}
		if ( queryInfo != null ) {
			if ( queryInfo.getLimit() != null ) {
				query.setMaxResults(queryInfo.getLimit().intValue());
			}
			if ( queryInfo.getOffset() != null ) {
				query.setFirstResult(queryInfo.getOffset().intValue());
			}
		}
		System.out.println("BaseDAOHibernate   "+hql);
		
		List result = query.list();
		
		if(result != null)
			System.out.println("BaseDAOAction   result   "+result.size());
		
		
		return result;
	}
	
	public List executeFind(Session session ,final String hql, final QueryInfo queryInfo, final Object[] args){
		return executeFind ( session , hql , queryInfo , args , null , false , null);
	}
	
	
	
	public Object findUniqueResult(Session session, String hql, Object[] args) {
		return findUniqueResult(session , hql, args, null, false, null);
	}
	
	/**
	 * Returns unique result matching specified query using <code>org.springframework.orm.hibernate.HibernateCallback</code>
     * with arguments and their types
	 *
	 * @param hql         Query to execute
	 * @param args        Arguments to set
	 * @param types       Types of arguments
	 * @param cacheable   <code>true</code> if the query is cacheable
	 * @param cacheRegion region of cache. E.g. one that used in configuration file of EHCahce (ehcache.xml)
	 * @return Unique result matching specified query
	 */
	public Object findUniqueResult(Session session , final String hql, final Object[] args, final Type[] types, final boolean cacheable, final String cacheRegion) {
		
			
				Query query = session.createQuery(hql);
				query.setCacheable(cacheable);
				if ( cacheRegion != null ) {
					query.setCacheRegion(cacheRegion);
				}
				if ( args != null ) {
					for ( int i = 0; i < args.length; i++ ) {
						Object arg = args[i];
						Type type = null;
						if ( types != null && i < types.length ) {
							type = types[i];
						}
						if ( type == null ) {
							query.setParameter(i, arg);
						} else {
							query.setParameter(i, arg, type);
						}
					}
				}
				
				return query.uniqueResult();
	}
	
	

}
