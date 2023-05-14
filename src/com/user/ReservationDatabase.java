package com.user;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.model.Ticket;

public class ReservationDatabase {
	public static StandardServiceRegistry registry;
	public static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null) {
			
		Map<String, Object> m = new HashMap<>();
		
		m.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		m.put(Environment.URL, "jdbc:mysql://localhost:3306/reservation");
		m.put(Environment.USER, "root");
		m.put(Environment.PASS, "root");
		m.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
		m.put(Environment.HBM2DDL_AUTO, "update");
		m.put(Environment.SHOW_SQL, "true");
		registry = new StandardServiceRegistryBuilder().applySettings(m).build();
		MetadataSources mds = new MetadataSources(registry);
		mds.addAnnotatedClass(Ticket.class);
		
		
		Metadata md = mds.getMetadataBuilder().build();
		sessionFactory = md.getSessionFactoryBuilder().build();
		}
		return sessionFactory;

	}

}
