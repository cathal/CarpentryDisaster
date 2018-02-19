package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;

import org.hibernate.query.Query;

import model.Customer;
import model.EmailAddress;


public class CustomerDAO {
	/*gjhhg*/
	public void insertCustomer(Customer c) {
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
	
	public void deleteCustomer(int customerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			/*session.get() goes to the database with the customerId
			 * argument, loops through all the rows in the Book table,
			 finds the matching row for that id (primary key)
			 retrieves that row, turns it into a Customer object, using the constructor
			 and setters from the Customer class (listed as the first argument)*/ 
			Customer customer = session.get(Customer.class, customerId);
			/*Deletes the book from the database*/
			session.delete(customer);
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	// 4 lines of code: open session, get the customer, close session, return the customer
	public Customer getCustomerbyId(int customerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Customer customer =  session.get(Customer.class, customerId);
		session.close();
		return customer;
	}
	
	public EmailAddress getEmailbyId(int customerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		EmailAddress email = session.get(EmailAddress.class, customerId);
		session.close();
		return email;
	}
	
	public boolean customerExists(Customer c) {
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
	
	public List<Customer> getAllCustomers() {
		List<Customer> listOfCustomers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query<Customer> query = session.createQuery("FROM Customer");
			/* query.list() executes the Hibernate Query Language
			 * (FROM Customer), gets all the Customer rows from the database,
			 * turns each row into a Customer object, using the Customer
			 * constructor, adds each Customer to a List and returns that list
			 */
			listOfCustomers = query.list();
			System.out.println(listOfCustomers);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
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
				
				//System.out.println("search2 "+search);
				
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
	
	public void updateCustomer(Customer updatedCustomer) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(updatedCustomer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
