package com.bbs.util;
import com.bbs.domain.model.*;
import com.bbs.domain.service.*;
import com.bbs.domain.service.impl.*;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("11sdsfdfgs1211");
		user.setPwd("11111");
		try{
		/*UserManager manager = ManagerFactory.createUserManager();
		manager.saveUser(user);*/
			
			Session s = HibernateUtil.currentSession();
			HibernateUtil.beginTransaction();
			s.saveOrUpdate(user);
			HibernateUtil.commitTransaction();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
