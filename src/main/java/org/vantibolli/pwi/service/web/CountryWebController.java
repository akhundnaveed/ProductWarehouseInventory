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
import org.vantibolli.pwi.model.Country;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.model.Warehouse;
import org.vantibolli.pwi.service.CountryService;
import org.vantibolli.pwi.service.InventoryService;
import org.vantibolli.pwi.service.WarehouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Spring web service controller class to handle Country related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Api(value = "Country API", tags = { "Country Web Services" })
@Controller
@RequestMapping("/country")
public class CountryWebController {
	
	private static Logger logger = LoggerFactory.getLogger(CountryWebController.class);
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	/**
	 * Get a list of all Countries from the database
	 * 
	 * @return list of Countries
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of all Countries from the database", response = Country.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of countries fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> listAllCountries() {
		try {
			logger.info("Getting list of countries");
			List<Country> countryList = countryService.listAllCountries();
			
			if (countryList == null || countryList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Countries found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(countryList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all countries", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Countries:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find Country object from the database against given country id
	 * 
	 * @param id
	 *            the id of Country to find
	 * @return Country object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find Country object from the database against given country id", response = Country.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country record found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findCountry(
			@PathVariable(name = "id", required = true) @ApiParam(name = "country id", value = "the id of Country to find", required = true) Integer id) {
		try {
			logger.info("Finding country id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Country country = countryService.findCountryById(id);
			
			if (country == null) {
				return new ResponseEntity<>(new Response(false, "Country not found against given country id:" + id), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(country, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a country against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Country:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Store given Country object record in the database
	 * 
	 * @param country
	 *            the Country object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created Country record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Store given Country object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Country record created successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> addCountry(
			@RequestBody(required = true) @ApiParam(name = "country", value = "the Country object to be stored in the database", required = true) Country country,
			UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add country {}", country);
			
			if (StringUtils.isEmpty(country.getName())) {
				return new ResponseEntity<>(new Response(false, "Country.name is missing"), HttpStatus.BAD_REQUEST);
			}
			
			countryService.addCountry(country);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/country/{id}").buildAndExpand(country.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Country. id:" + country.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding country", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Country:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update given Country object record in the database
	 * 
	 * @param id
	 *            the id of the Country object to be updated in the database
	 * @param country
	 *            the Country object to be updated in the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update given Country object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country record updated successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> updateCountry(
			@PathVariable(name = "id", required = true) @ApiParam(name = "country id", value = "the id of the Country object to be updated in the database", required = true) Integer id,
			@RequestBody(required = true) @ApiParam(name = "country", value = "the Country object to be updated in the database", required = true) Country country) {
		try {
			logger.info("Update country {}", country);
			
			if (country.getId() == null) {
				return new ResponseEntity<>(new Response(false, "Country.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(country.getName())) {
				return new ResponseEntity<>(new Response(false, "Country.name is missing"), HttpStatus.BAD_REQUEST);
			}
			
			countryService.updateCountry(country);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Country. id:" + country.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating country", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Country:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete the given Country object from the database
	 * 
	 * @param id
	 *            the id of Country object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the given Country object from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Country record deleted successfully", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Object> deleteCountry(
			@PathVariable(name = "id", required = true) @ApiParam(name = "country id", value = "the id of Country object to be deleted from the database", required = true) Integer id) {
		try {
			logger.info("Deleting country id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Country country = countryService.findCountryById(id);
			
			if (country == null) {
				return new ResponseEntity<>(new Response(false, "Country could not be found to be deleted.id=" + id), HttpStatus.NOT_FOUND);
			}
			
			countryService.deleteCountry(country);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Country.id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting country", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Country:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get list of all Warehouses from the database against given country id
	 * 
	 * @param countryId
	 *            the id of Country against which the list of Warehouses will be returned
	 * @return list of Warehouses
	 */
	@RequestMapping(value = "{id}/warehouses", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of all Warehouses from the database against given country id", response = Warehouse.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of warehouses against given country id fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findWarehousesByCountryId(
			@PathVariable(name = "id", required = true) @ApiParam(name = "country id", value = "the id of Country against which the list of Warehouses will be returned", required = true) Integer id) {
		try {
			logger.info("Finding Warehouses against country id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Country country = countryService.findCountryById(id);
			
			if (country == null) {
				return new ResponseEntity<>(new Response(false, "Country not found against given country id:" + id), HttpStatus.NOT_FOUND);
			}
			
			List<Warehouse> warehouseList = warehouseService.listAllWarehousesByCountryId(id);
			
			if (warehouseList == null || warehouseList.isEmpty()) {
				return new ResponseEntity<>(
						new Response(false, "No Warehouses found against given country id:" + id + ", name:" + country.getName()),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(warehouseList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding Warehouses against given country id", e);
			return new ResponseEntity<>(
					new Response(false, "Error occurred while finding Warehouses against given country id." + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get a list of Inventories from the database against given country id
	 * 
	 * @param countryId
	 *            the id of Country against which the list of Inventories will be returned
	 * @return list of Inventories
	 */
	@RequestMapping(value = "{id}/inventories", method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of Inventories from the database against given country id", response = Inventory.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of inventories against given country id fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findInventoriesByCountryId(
			@PathVariable(name = "id", required = true) @ApiParam(name = "country id", value = "the id of Country against which the list of Inventories will be returned", required = true) Integer id) {
		try {
			logger.info("Finding Inventories against country id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Country.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Country country = countryService.findCountryById(id);
			
			if (country == null) {
				return new ResponseEntity<>(new Response(false, "Country not found against given country id:" + id), HttpStatus.NOT_FOUND);
			}
			
			List<Inventory> inventoryList = inventoryService.listInventoriesByCountryId(id);
			
			if (inventoryList == null || inventoryList.isEmpty()) {
				return new ResponseEntity<>(
						new Response(false, "No Inventories found against given country id:" + id + ", name:" + country.getName()),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(inventoryList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding Inventories against given country id", e);
			return new ResponseEntity<>(
					new Response(false, "Error occurred while finding Inventories against given country id." + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
