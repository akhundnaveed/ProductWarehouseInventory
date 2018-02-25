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
 * @author naveed
 *
 */
@Repository
public class InventoryDao extends AbstractDao<Inventory> {

	@Transactional
	public List<Inventory> findInventoriesByCountryId(Integer countryId) {
		Query query = getCurrentSession().createQuery("from " + Inventory.class.getName() + " where warehouse.country.id = " + countryId);
		return query.list();
	}

	@Transactional
	public List<Inventory> findInventoriesByWarehouseId(Integer warehouseId) {
		Query query = getCurrentSession().createQuery("from " + Inventory.class.getName() + " where warehouse.id = " + warehouseId);
		return query.list();
	}
	
}
