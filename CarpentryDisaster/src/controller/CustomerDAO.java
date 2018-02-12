package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Customer;


public class CustomerDAO {
	
	protected void insertCustomer(Customer c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(c);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	protected boolean customerExists(Customer c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "FROM Customer where firstName = :first "
				     + "AND surName = :second "
				     + "AND phoneNumber =:phone";
		Query<Customer> query = session.createQuery(hql);
		/* Assign the two parameters their values */
		query.setParameter("first", c.getFirstName());
		query.setParameter("second", c.getSurName());
		query.setParameter("phone", c.getPhoneNumber());
		
		List<Customer> result = query.list();
		session.close();
		
		/* If a list is returned, the person exists, so return true, 
		 * if the list is empty the person doesn't exist, return false. */
		return result.isEmpty() ? false : true;
	}
	
	protected List<Customer> getAllCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Customer> query = session.createQuery("FROM Customer");
		List<Customer> listOfCustomers = query.list();
		session.close();
		return listOfCustomers;
	}
	
	

	public List<Customer> search(String search, String searchType) {
		List<Customer> listOfCustomers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Customer> query;
		String hql;
		
		System.out.println("search "+search);
		System.out.println("searchType "+searchType);
		try {
				search = "%" + search + "%";
				hql = "FROM Customer WHERE "+searchType+ " LIKE :search";
				query = session.createQuery(hql);
				query.setParameter("search",search);
				listOfCustomers = query.list();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	
		
		return listOfCustomers;
	}
}
