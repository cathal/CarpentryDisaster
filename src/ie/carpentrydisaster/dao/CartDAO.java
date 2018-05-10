package ie.carpentrydisaster.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ie.carpentrydisaster.model.*;
import ie.carpentrydisaster.utils.HibernateUtil;

public class CartDAO {
	public void insertCartMaterial(Cart c) {
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
	public List<Cart> cartMaterialExists(int customerId,int materialId) {
		List<Cart> listOfCartMaterials = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Cart> query;
		String hql;
		
		hql = "FROM Cart WHERE customerId = :customerId AND materialId = :materialId";
		query = session.createQuery(hql);
		query.setParameter("customerId",customerId);
		query.setParameter("materialId",materialId);

		listOfCartMaterials = query.list();
				
		return listOfCartMaterials;
}
/*public void deleteCartMaterial(int cartId)
{
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;
		
	try {
			tx = session.beginTransaction();
			Cart cart = session.get(Cart.class, cartId);
			session.delete(cart);
			tx.commit();
	} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
		e.printStackTrace();
		} finally {
			session.close();
		}	
	}*/
	
public void deleteCartMaterial(Cart c)
{
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = null;
		
	try {
			tx = session.beginTransaction();
			session.delete(c);
			tx.commit();
	} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
		e.printStackTrace();
		} finally {
			session.close();
		}	
	}
	public Cart getCartObjectById(int customerId, int materialId)
	{
		List<Cart> listOfCartMaterials = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Cart> query;
		String hql;
		
		hql = "FROM Cart WHERE customerId = :customerId AND materialId = :materialId";
		query = session.createQuery(hql);
		query.setParameter("customerId",customerId);
		query.setParameter("materialId",materialId);

		listOfCartMaterials = query.list();
		Cart c = listOfCartMaterials.get(0);
		return c;
	}
	
	public List<Cart> getAllCartMaterialsForCust(int customerId) {
		
		List<Cart> listOfCartMaterials = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Cart> query;
		String hql;
		
		try {
			hql = "FROM Cart WHERE customerId = :customerId";
			query = session.createQuery(hql);
			query.setParameter("customerId",customerId);
			listOfCartMaterials = query.list();
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return listOfCartMaterials;
	}
	
	public List<Cart> getAllCartMaterials() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		/*Query<Cart> query = session.createQuery("FROM Cart");
		List<Cart> listOfCartMaterials = query.list();
		session.close();*/
		
		List<Cart> listOfCartMaterials = new ArrayList<>();
		try {
			Query<Cart> query = session.createQuery("FROM Cart");
			/* query.list() executes the Hibernate Query Language
			 * (FROM Customer), gets all the Customer rows from the database,
			 * turns each row into a Customer object, using the Customer
			 * constructor, adds each Customer to a List and returns that list
			 */
			listOfCartMaterials = query.list();
			//System.out.println(listOfCartMaterials);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return listOfCartMaterials;
	}
	
	public void updateCart(Cart updatedCart) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(updatedCart);
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
