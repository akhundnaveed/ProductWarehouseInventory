/**
 * 
 */
package org.vantibolli.pwi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.vantibolli.pwi.model.Warehouse;

/**
 * DAO class to perform database CRUD operations for Warehouse entity
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Repository
public class WarehouseDao extends AbstractDao<Warehouse, Integer> {

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Warehouse> findWarehousesByCountryId(Integer countryId) {
		Query query = getCurrentSession().createQuery("from " + Warehouse.class.getName() + " where country.id = " + countryId);
		return query.list();
	}
}
