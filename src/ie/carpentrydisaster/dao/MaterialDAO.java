package ie.carpentrydisaster.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ie.carpentrydisaster.model.Material;
import ie.carpentrydisaster.utils.HibernateUtil;

public class MaterialDAO {
	
	public void insertMaterials(Material m) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(m);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteMaterial(int materialId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Material material = session.get(Material.class, materialId);
			System.out.println("------"+material);
			/*Deletes the material from the database*/
			session.delete(material);
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateMaterial(Material updatedMaterial)	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(updatedMaterial);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public Material getMaterialbyId(int materialId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Material material =  session.get(Material.class, materialId);
		session.close();
		return material;
	}
	
	
	public List<Material> getAllMaterials() {
		List<Material> listOfMaterials = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query<Material> query = session.createQuery("FROM Material");
			/* query.list() executes the Hibernate Query Language
			 * (FROM Customer), gets all the Customer rows from the database,
			 * turns each row into a Customer object, using the Customer
			 * constructor, adds each Customer to a List and returns that list
			 */
			listOfMaterials = query.list();
			System.out.println(listOfMaterials);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return listOfMaterials;
	}
	
	

	public List<Material> search(String search, String searchType) {
		List<Material> listOfMaterials = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Material> query;
		String hql;
		
		System.out.println("search "+search);
		System.out.println("searchType "+searchType);
		try {
				search = "%" + search + "%";
				
				//System.out.println("search2 "+search);
				
				hql = "FROM Material WHERE "+searchType+ " LIKE :search";
				
				query = session.createQuery(hql);
				query.setParameter("search",search);
				
				listOfMaterials = query.list();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	
		
		return listOfMaterials;
	}
	
}
