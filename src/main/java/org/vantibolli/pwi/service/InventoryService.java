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
 * @author naveed
 *
 */
@Service
public class InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	public List<Inventory> listAllInventories() {
		return inventoryDao.findAll();
	}
	
	public List<Inventory> listInventoriesByCountryId(Integer countryId) {
		return inventoryDao.findInventoriesByCountryId(countryId);
	}
	
	public List<Inventory> listInventoriesByWarehouseId(Integer warehouseId) {
		return inventoryDao.findInventoriesByWarehouseId(warehouseId);
	}
	
	public void addInventory(Inventory inventory) {
		inventoryDao.save(inventory);
	}
	
	public void updateInventory(Inventory inventory) {
		inventoryDao.update(inventory);
	}
	
	public Inventory findInventoryById(Integer id) {
		return inventoryDao.findOne(id);
	}
	
	public void deleteInventory(Inventory inventory) {
		inventoryDao.delete(inventory);
	}
}
