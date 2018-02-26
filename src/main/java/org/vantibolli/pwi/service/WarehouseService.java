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
 * @author naveed
 *
 */
@Service
public class WarehouseService {
	
	@Autowired
	private WarehouseDao warehouseDao;
	
	public List<Warehouse> listAllWarehouses() {
		return warehouseDao.findAll();
	}
	
	public List<Warehouse> listAllWarehousesByCountryId(Integer countryId) {
		return warehouseDao.findWarehousesByCountryId(countryId);
	}
	
	public void addWarehouse(Warehouse warehouse) {
		warehouseDao.save(warehouse);
	}
	
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDao.update(warehouse);
	}
	
	public Warehouse findWarehouseById(Integer id) {
		return warehouseDao.findOne(id);
	}
	
	public void deleteWarehouse(Warehouse warehouse) {
		warehouseDao.delete(warehouse);
	}
	
}
