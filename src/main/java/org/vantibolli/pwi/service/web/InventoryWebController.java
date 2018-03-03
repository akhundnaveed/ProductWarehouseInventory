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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Spring web service controller class to perform Inventory related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Api(value = "Inventory API", tags = { "Inventory Web Services" })
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
	@ApiOperation(value = "Get a list of all Inventories from the database", response = Inventory.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of inventories fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
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
	@ApiOperation(value = "Find Inventory object from the database against given inventory id", response = Inventory.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Inventory record found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findInventory(
			@PathVariable(name = "id", required = true) @ApiParam(name = "inventory id", value = "the id of Inventory to find", required = true) Integer id) {
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
	@ApiOperation(value = "Store given Inventory object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Inventory record created successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> addInventory(
			@RequestBody @ApiParam(name = "inventory", value = "the Inventory object to be stored in the database", required = true) Inventory inventory,
			UriComponentsBuilder ucBuilder) {
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
	@ApiOperation(value = "Update given Inventory object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Inventory record updated successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> updateInventory(
			@PathVariable(name = "id", required = true) @ApiParam(name = "id", value = "the id of Inventory object to be updated in the database", required = true) Integer id,
			@RequestBody(required = true) @ApiParam(name = "inventory", value = "the Inventory object to be updated in the database", required = true) Inventory inventory) {
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
	@ApiOperation(value = "Delete the given Inventory object from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Inventory record deleted successfully", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Object> deleteInventory(
			@PathVariable(name = "id", required = true) @ApiParam(name = "inventory id", value = "the id of Inventory object to be deleted from the database", required = true) Integer id) {
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
