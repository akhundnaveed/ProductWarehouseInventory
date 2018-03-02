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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.vantibolli.pwi.ext.Response;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.service.InventoryService;

/**
 * The Spring web service controller class to perform Inventory related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Controller
@RequestMapping("/inventory")
public class InventoryWebController {
	
	private static Logger logger = LoggerFactory.getLogger(InventoryWebController.class);
	
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * Get a list of Inventories from the database
	 * 
	 * @return list of Inventories
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listInventories() {
		try {
			logger.info("Getting list of inventories");
			List<Inventory> inventoryList = inventoryService.listAllInventories();
			
			if (inventoryList == null || inventoryList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Inventories found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(inventoryList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all inventories", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Inventories:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find Inventory object from database against given inventory id
	 * 
	 * @param id
	 *            the id of Inventory to find
	 * @return Inventory object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findInventory(@PathVariable Integer id) {
		try {
			logger.info("Finding inventory id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Inventory inventory = inventoryService.findInventoryById(id);
			
			if (inventory == null) {
				return new ResponseEntity<>(new Response(false, "Inventory not found against given inventory id:" + id),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a inventory against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Store given Inventory object record in the database
	 * 
	 * @param inventory
	 *            the Inventory object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created Inventory record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addInventory(@RequestBody Inventory inventory, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add inventory {}", inventory);
			
			if (inventory.getWarehouse().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProduct().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProductSize().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.size.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProductType().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.type.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			inventoryService.addInventory(inventory);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventory.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Inventory. id:" + inventory.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update given Inventory object record in the database
	 * 
	 * @param id
	 *            the id of Inventory object to be updated in the database
	 * @param inventory
	 *            the Inventory object to be updated in the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
		try {
			logger.info("Update inventory {}", inventory);
			
			if (inventory.getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getWarehouse().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.warehouse.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProduct().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProductSize().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.size.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (inventory.getProductType().getId() == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.product.type.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			inventoryService.updateInventory(inventory);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Inventory. id:" + inventory.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete the given Inventory object from the database
	 * 
	 * @param id
	 *            the id of Inventory object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInventory(@PathVariable Integer id) {
		try {
			logger.info("Deleting inventory id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Inventory inventory = inventoryService.findInventoryById(id);
			
			if (inventory == null) {
				return new ResponseEntity<>(new Response(false, "Inventory could not be found to be deleted.id=" + id),
						HttpStatus.NOT_FOUND);
			}
			
			inventoryService.deleteInventory(inventory);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Inventory.id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
