/**
 * 
 */
package org.vantibolli.pwi.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h1>AbstractDao</h1> This class implements common database CRUD operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public abstract class AbstractDao<T extends Serializable, Id extends Serializable> {
	
	/**
	 * Entity class type
	 */
	private Class<T> clazz;
	
	/**
	 * SessionFactory to get Hibernate session
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Also initializes the entity type added to extending DAO class
	 */
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * Find a single entity object from database against given id
	 * 
	 * @param id
	 *            the {@code id} to find entity in the database against it
	 * @return the entity object after finding in the database
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public T findOne(Id id) {
		return (T) getCurrentSession().get(clazz, id);
	}
	
	/**
	 * Find all available entity objects from database
	 * 
	 * @return list of all entities
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll() {
		Query query = getCurrentSession().createQuery("from " + clazz.getName());
		return query.list();
	}
	
	/**
	 * Save given entity into database
	 * 
	 * @param entity
	 *            object to be saved in the database
	 */
	@Transactional
	public void save(T entity) {
		getCurrentSession().persist(entity);
	}
	
	/**
	 * Save the list of entities in the database
	 * 
	 * @param the
	 *            entity list to be saved in database
	 */
	@Transactional
	public void saveAll(List<T> entityList) {
		for (T t : entityList) {
			save(t);
		}
	}
	
	/**
	 * Update the given entity in the database
	 * 
	 * @param entity
	 *            to be updated in the database
	 */
	@Transactional
	public void update(T entity) {
		getCurrentSession().merge(entity);
	}
	
	/**
	 * Delete the given entity from the database
	 * 
	 * @param entity
	 *            to be deleted from the database
	 */
	@Transactional
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}
	
	/**
	 * Delete the entity from the database against given entity id
	 * 
	 * @param id
	 *            the {@code id} of the entity to be deleted from the database
	 */
	@Transactional
	public void deleteById(Id entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	/**
	 * Get current hibernate session object from {@code SessionFactory}
	 * 
	 * @return the hibernate session
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
