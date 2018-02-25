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
import org.vantibolli.pwi.util.PwiException;

/**
 * @author naveed
 *
 */
public abstract class AbstractDao<T extends Serializable> {
	
	private Class<T> clazz;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public T findOne(Integer id) {
		return (T) getCurrentSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll() {
		Query query = getCurrentSession().createQuery("from " + clazz.getName());
		return query.list();
	}
	
	@Transactional
	public void save(T entity) {
		if (entity == null)
			throw new PwiException(clazz + " is null");
		getCurrentSession().persist(entity);
	}
	
	@Transactional
	public void saveAll(List<T> entityList) {
		for (T t : entityList) {
			save(t);
		}
	}
	
	@Transactional
	public void update(T entity) {
		if (entity == null)
			throw new PwiException(clazz + " not found in DB to update");
		getCurrentSession().merge(entity);
	}
	
	@Transactional
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}
	
	@Transactional
	public void deleteById(Integer entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
