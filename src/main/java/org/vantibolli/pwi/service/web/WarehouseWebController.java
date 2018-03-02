/**
 * 
 */
package org.vantibolli.pwi.service.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.vantibolli.pwi.ext.Response;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.model.Warehouse;
import org.vantibolli.pwi.service.InventoryService;
import org.vantibolli.pwi.service.WarehouseService;

/**
 * The Spring web service controller class to perform Warehouse related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(WarehouseWebController.class);
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * Get list of all Warehouses from the database
	 * 
	 * @return list of Warehouses
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listWarehouses() {
		try {
			logger.info("Getting list of warehouses");
			List<Warehouse> warehouseList = warehouseService.listAllWarehouses();
			
			if (warehouseList == null || warehouseList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Warehouses found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(warehouseList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all warehouses", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Warehouses:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find Warehouse object from database against given warehouse id
	 * 
	 * @param id
	 *            the id of Warehouse to find
	 * @return Warehouse object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findWarehouse(@PathVariable Integer id) {
		try {
			logger.info("Finding warehouse id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Warehouse warehouse = warehouseService.findWarehouseById(id);
			
			if (warehouse == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse not found against given warehouse id:" + id),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(warehouse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a warehouse against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Warehouse:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Store given Warehouse object record in the database
	 * 
	 * @param Warehouse
	 *            the Warehouse object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created Warehouse record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addWarehouse(@RequestBody Warehouse warehouse, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add warehouse {}", warehouse);
			
			if (StringUtils.isEmpty(warehouse.getName())) {
				return new ResponseEntity<>(new Response(false, "Warehouse.name is missing"), HttpStatus.BAD_REQUEST);
			} else if (warehouse.getCountry().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			warehouseService.addWarehouse(warehouse);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/warehouse/{id}").buildAndExpand(warehouse.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Warehouse. id:" + warehouse.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding warehouse", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Warehouse:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update given Warehouse object record in the database
	 * 
	 * @param id
	 *            the id of Warehouse object to be updated in the database
	 * @param warehouse
	 *            the Warehouse object to be updated in the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
		try {
			logger.info("Update warehouse {}", warehouse);
			
			if (warehouse.getId() == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(warehouse.getName())) {
				return new ResponseEntity<>(new Response(false, "Warehouse.name is missing"), HttpStatus.BAD_REQUEST);
			} else if (warehouse.getCountry().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			warehouseService.updateWarehouse(warehouse);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Warehouse. id:" + warehouse.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating warehouse", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Warehouse:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete the given Warehouse object from the database
	 * 
	 * @param id
	 *            the id of Warehouse object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteWarehouse(@PathVariable Integer id) {
		try {
			logger.info("Deleting warehouse id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Warehouse warehouse = warehouseService.findWarehouseById(id);
			
			if (warehouse == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse could not be found to be deleted.id=" + id),
						HttpStatus.NOT_FOUND);
			}
			
			warehouseService.deleteWarehouse(warehouse);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Warehouse.id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting warehouse", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Warehouse:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get a list of Inventories from the database against given warehouse id
	 * 
	 * @param warehouseId
	 *            the id of Warehouse against which the list of Inventories will be returned
	 * @return list of Inventories
	 */
	@RequestMapping(value = "{id}/inventories", method = RequestMethod.GET)
	public ResponseEntity<Object> findInvetoriesByWarehouseId(@PathVariable Integer id) {
		try {
			logger.info("Finding Inventories against warehouse id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Warehouse warehouse = warehouseService.findWarehouseById(id);
			
			if (warehouse == null) {
				return new ResponseEntity<>(new Response(false, "Warehouse not found against given warehouse id:" + id),
						HttpStatus.NOT_FOUND);
			}
			
			List<Inventory> inventoryList = inventoryService.listInventoriesByWarehouseId(id);
			
			if (inventoryList == null || inventoryList.isEmpty()) {
				return new ResponseEntity<>(
						new Response(false, "No Inventories found against given warehouse id:" + id + ", name:" + warehouse.getName()),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(inventoryList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding list of Inventories against given warehouse id", e);
			return new ResponseEntity<>(
					new Response(false, "Error occurred while finding Inventories against given warehouse id." + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
