/**
 * 
 */
package org.vantibolli.pwi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.vantibolli.pwi.model.Inventory;

/**
 * DAO class to perform database CRUD operations for Inventory entity
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Repository
public class InventoryDao extends AbstractDao<Inventory, Integer> {
	
	/**
	 * Find the list of Inventory entities against given country id
	 * 
	 * @param countryId
	 *            to get list of Inventory entities against it
	 * @return the list of Inventory entities
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Inventory> findInventoriesByCountryId(Integer countryId) {
		Query query = getCurrentSession().createQuery("from " + Inventory.class.getName() + " where warehouse.country.id = " + countryId);
		return query.list();
	}
	
	/**
	 * Find the list of Inventory entities against given warehouse id
	 * 
	 * @param warehouseId
	 *            to get list of Inventory entities against it
	 * @return the list of Inventory entities
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Inventory> findInventoriesByWarehouseId(Integer warehouseId) {
		Query query = getCurrentSession().createQuery("from " + Inventory.class.getName() + " where warehouse.id = " + warehouseId);
		return query.list();
	}
	
}
