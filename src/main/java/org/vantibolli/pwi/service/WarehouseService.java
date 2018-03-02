/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.WarehouseDao;
import org.vantibolli.pwi.model.Warehouse;

/**
 * The Spring Service class to perform Warehouse related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class WarehouseService {
	
	@Autowired
	private WarehouseDao warehouseDao;
	
	/**
	 * Get list of all Warehouses from the database
	 * 
	 * @return list of Warehouses
	 */
	public List<Warehouse> listAllWarehouses() {
		return warehouseDao.findAll();
	}
	
	/**
	 * Get list of all Warehouses from the database against given country id
	 * 
	 * @param countryId
	 *            the id of Country against which the list of Warehouses will be returned
	 * @return list of Warehouses
	 */
	public List<Warehouse> listAllWarehousesByCountryId(Integer countryId) {
		return warehouseDao.findWarehousesByCountryId(countryId);
	}
	
	/**
	 * Store given Warehouse object record in the database
	 * 
	 * @param Warehouse
	 *            the Warehouse object to be stored in the database
	 */
	public void addWarehouse(Warehouse warehouse) {
		warehouseDao.save(warehouse);
	}
	
	/**
	 * Update given Warehouse object record in the database
	 * 
	 * @param warehouse
	 *            the Warehouse object to be updated in the database
	 */
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDao.update(warehouse);
	}
	
	/**
	 * Find Warehouse object from database against given warehouse id
	 * 
	 * @param id
	 *            the id of Warehouse to find
	 * @return Warehouse object found from the database
	 */
	public Warehouse findWarehouseById(Integer id) {
		return warehouseDao.findOne(id);
	}
	
	/**
	 * Delete the given Warehouse object from the database
	 * 
	 * @param warehouse
	 *            the Warehouse object to be deleted from the database
	 */
	public void deleteWarehouse(Warehouse warehouse) {
		warehouseDao.delete(warehouse);
	}
}
