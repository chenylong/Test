package com.bbs.util;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 * {@link http://hibernate.org/42.html}.
 */
public class HibernateUtil {

	private static Log logger = LogFactory.getLog("org.hibernate.");
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	/**
	 * Location of hibernate.cfg.xml file. NOTICE: Location should be on the
	 * classpath as Hibernate uses #resourceAsStream style lookup for its
	 * configuration file. That is place the config file in a Java package - the
	 * default location is the default Java package.<br>
	 * <br>
	 * Examples: <br>
	 * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
	 * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code>
	 */
	// private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final SessionFactory sessionFactory;
	static {
		try {
			// Create the SessionFactory
			sessionFactory = new Configuration().configure(CONFIG_FILE_LOCATION )
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Initial SessionFactory creation failed.");
			logger.error(ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal tLocalsess = new ThreadLocal();

	public static final ThreadLocal tLocaltx = new ThreadLocal();

	/*
	 * getting the thread-safe session for using
	 */
	public static Session currentSession() {
		Session session = (Session) tLocalsess.get();

		// open a new one, if none can be found.
		try {
			if (session == null || !session.isOpen()) {
				session = openSession();
				tLocalsess.set(session);
			}
		} catch (HibernateException e) {
			// throw new HibernateException(e);
			logger.error(e);
		}
		return session;
	}

	/*
	 * closing the thread-safe session
	 */
	public static void closeSession() {

		Session session = (Session) tLocalsess.get();
		tLocalsess.set(null);
		try {
			if (session != null && session.isOpen()) {
				session.close();
			}

		} catch (HibernateException e) {
			// throw new InfrastructureException(e);
			logger.error(e);
		}
	}

	/*
	 * begin the transaction
	 */
	public static void beginTransaction() {
		//System.out.println("begin tx");
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			if (tx == null) {
				tx = currentSession().beginTransaction();
				tLocaltx.set(tx);
			}
		} catch (HibernateException e) {
			// throw new InfrastructureException(e);
			logger.error(e);
		}
	}

	/*
	 * close the transaction
	 */
	public static void commitTransaction() {
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
				tx.commit();
			tLocaltx.set(null);
			//System.out.println("commit tx");
		} catch (HibernateException e) {
			// throw new InfrastructureException(e);
			logger.error(e);
		}
	}

	/*
	 * for rollbacking
	 */
	public static void rollbackTransaction() {
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			tLocaltx.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
			}
			System.out.println("Roll back...");
		} catch (HibernateException e) {
			// throw new InfrastructureException(e);
			logger.error(e);
		}
	}

	private static Session openSession() throws HibernateException {
		return getSessionFactory().openSession();
	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		return sessionFactory;// SingletonSessionFactory.getInstance();
	}

}
