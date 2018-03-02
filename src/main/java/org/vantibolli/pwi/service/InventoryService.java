/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.InventoryDao;
import org.vantibolli.pwi.model.Inventory;

/**
 * The Spring Service class to perform Inventory related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	/**
	 * Get a list of Inventories from the database
	 * 
	 * @return list of Inventories
	 */
	public List<Inventory> listAllInventories() {
		return inventoryDao.findAll();
	}
	
	/**
	 * Get a list of Inventories from the database against given country id
	 * 
	 * @param countryId
	 *            the id of Country against which the list of Inventories will be returned
	 * @return list of Inventories
	 */
	public List<Inventory> listInventoriesByCountryId(Integer countryId) {
		return inventoryDao.findInventoriesByCountryId(countryId);
	}
	
	/**
	 * Get a list of Inventories from the database against given warehouse id
	 * 
	 * @param warehouseId
	 *            the id of Warehouse against which the list of Inventories will be returned
	 * @return list of Inventories
	 */
	public List<Inventory> listInventoriesByWarehouseId(Integer warehouseId) {
		return inventoryDao.findInventoriesByWarehouseId(warehouseId);
	}
	
	/**
	 * Store given Inventory object record in the database
	 * 
	 * @param inventory
	 *            the Inventory object to be stored in the database
	 */
	public void addInventory(Inventory inventory) {
		inventoryDao.save(inventory);
	}
	
	/**
	 * Update given Inventory object record in the database
	 * 
	 * @param inventory
	 *            the Inventory object to be updated in the database
	 */
	public void updateInventory(Inventory inventory) {
		inventoryDao.update(inventory);
	}
	
	/**
	 * Find Inventory object from database against given inventory id
	 * 
	 * @param id
	 *            the id of Inventory to find
	 * @return Inventory object found from the database
	 */
	public Inventory findInventoryById(Integer id) {
		return inventoryDao.findOne(id);
	}
	
	/**
	 * Delete the given Inventory object from the database
	 * 
	 * @param inventory
	 *            the Inventory object to be deleted from the database
	 */
	public void deleteInventory(Inventory inventory) {
		inventoryDao.delete(inventory);
	}
}
