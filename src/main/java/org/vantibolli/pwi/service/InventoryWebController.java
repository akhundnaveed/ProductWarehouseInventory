/**
 * 
 */
package org.vantibolli.pwi.service;

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

/**
 * @author naveed
 *
 */
@Controller
@RequestMapping("/inventory")
public class InventoryWebController {
	
	private static Logger logger = LoggerFactory.getLogger(InventoryWebController.class);
	
	@Autowired
	private PwiService pwiService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listInventories() {
		try {
			logger.info("Getting list of inventories");
			List<Inventory> inventoryList = pwiService.listAllInventories();
			
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findInventory(@PathVariable Integer id) {
		try {
			logger.info("Finding inventory id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Inventory inventory = pwiService.findInventoryById(id);
			
			if (inventory == null) {
				return new ResponseEntity<>(new Response(false, "Inventory not found against given inventory id:" + id), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a inventory against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
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
			
			pwiService.addInventory(inventory);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventory.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Inventory. id:" + inventory.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory, UriComponentsBuilder ucBuilder) {
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
			
			pwiService.updateInventory(inventory);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Inventory. id:" + inventory.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInventory(@PathVariable Integer id) {
		try {
			logger.info("Deleting inventory id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Inventory.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Inventory inventory = pwiService.findInventoryById(id);
			
			if (inventory == null) {
				return new ResponseEntity<>(new Response(false, "Inventory could not be found to be deleted.id=" + id), HttpStatus.NOT_FOUND);
			}
			
			pwiService.deleteInventory(inventory);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Inventory.id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting inventory", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Inventory:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
