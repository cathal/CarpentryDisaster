package ie.carpentrydisaster.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ie.carpentrydisaster.model.Cart;
import ie.carpentrydisaster.model.Customer;
import ie.carpentrydisaster.model.EmailAddress;
import ie.carpentrydisaster.model.Material;
import ie.carpentrydisaster.model.PhoneNumber;


public class HibernateUtil {
	/* A private constructor prevents a class from being instantiated.
	 * This class exists to provide a static method to retrieve the one
	 * instance of the SessionFactory.  */
	private HibernateUtil() {
		System.out.println("Hibernate Util");
	}
	
	/* You need one SessionFactory per web app. It will be used to 
	 * create Session objects which will be used for every 
	 * CRUD (create, read, update, delete) operation. */
	private static SessionFactory sessionFactory;
	
	/* A static global variable should be instantiated in a static 
	 * init block. */
	static {
		/* This Configuration object is only created once during 
		 * application initialization. It represents the config/
		 * properties file required by Hibernate. 
		 * It hooks up to the hibernate.cfg.xml file to read the 
		 * properties. The configure() method uses the mapping and 
		 * properties in that resource/ config file. */
		try {
			Properties applicationConfig = new Properties();
			applicationConfig.load(new FileInputStream(new File("WEB-INF/config/application.properties")));

			Configuration hibernateConfig = new Configuration().configure();
			hibernateConfig.setProperty("connection.url", applicationConfig.getProperty("database_host"));
			hibernateConfig.setProperty("connection.username", applicationConfig.getProperty("database_user"));
			hibernateConfig.setProperty("connection.password", applicationConfig.getProperty("database_password"));

			/* Lets the Configuration know about the Book class, so that
			* the annotations can be used to map between Java and SQL 
			* Server 
			* If you omit one of these lines, you will get the following exception:
			* org.hibernate.MappingException: Unknown entity: model.User
			**/
			hibernateConfig.addAnnotatedClass(Customer.class);
			hibernateConfig.addAnnotatedClass(EmailAddress.class);
			hibernateConfig.addAnnotatedClass(PhoneNumber.class);
			hibernateConfig.addAnnotatedClass(Material.class);
			hibernateConfig.addAnnotatedClass(Cart.class);
			
			//github.com/TrioOrg/Trio.git<!-->branch 'master' of https
			/* config.getProperties() gets all the mappings/ properties 
			* from the hibernate config file. */
			StandardServiceRegistryBuilder builder = 
					new StandardServiceRegistryBuilder().
					applySettings(hibernateConfig.getProperties());
			
			/* A SessionFactory is used to create each Session instance, 
			* the Configuration object is used to create the SessionFactory */
			sessionFactory = hibernateConfig.buildSessionFactory(builder.build());
			
			System.out.println("End of static block");
		}
		catch(Exception e) {
			System.out.print("ERRROROROROROROR: Something bad happened");
			System.out.println(e);
		}
	}
	
	/* A static method to return the one instance of the sessionFactory */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
