package org.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public HibernateUtil() {
	    sessionFactory = createSessionFactory();
	}

	 static {
	        try {
	        	Configuration configuration = new Configuration().configure();
	        	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
	        	builder.applySettings(configuration.getProperties());
	        	sessionFactory = configuration.buildSessionFactory(builder.build());
	        	
	        } catch (Throwable ex) {
	            // Log the exception. 
	            System.err.println("Initial SessionFactory creation failed." + ex);
	        }
	    }

	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static SessionFactory createSessionFactory() {

	    Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
}