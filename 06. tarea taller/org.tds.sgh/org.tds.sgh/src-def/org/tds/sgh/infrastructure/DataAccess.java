package org.tds.sgh.infrastructure;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DataAccess
{
	// Logger -----------------------------------------------------------------
	
	private static final Logger log = Logger.getLogger(DataAccess.class);
	
	
	// Singleton --------------------------------------------------------------

	private static DataAccess _instance = null;
	
	public static DataAccess getInstance()
	{
		if (_instance == null)
		{
			_instance = new DataAccess();
		}
		
		return _instance;
	}
	
	private DataAccess()
	{
	}
	
	
	// Attributes -------------------------------------------------------------
	
	private SessionFactory sessionFactory;
	
	
	// Operations -------------------------------------------------------------
	
	public void start()
	{
		if (sessionFactory == null)
		{
			try
			{
				sessionFactory =
					new AnnotationConfiguration()
						.configure()
						.buildSessionFactory();
			}
			catch (Throwable t)
			{
				log.error("Cannot access the database", t);
				t.printStackTrace();
				throw new ExceptionInInitializerError(t);
			}
		}
	}
	
	public DataAccessConnection createConnection()
	{
		start();
		return new DataAccessConnection(sessionFactory.openSession());
	}

	public void shutdown()
	{
		sessionFactory.close();
	}
}
