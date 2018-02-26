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

/**
 * @author naveed
 *
 */
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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listCountries() {
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findCountry(@PathVariable Integer id) {
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addCountry(@RequestBody Country country, UriComponentsBuilder ucBuilder) {
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateCountry(@PathVariable Integer id, @RequestBody Country country, UriComponentsBuilder ucBuilder) {
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCountry(@PathVariable Integer id) {
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
	
	@RequestMapping(value = "{id}/warehouses", method = RequestMethod.GET)
	public ResponseEntity<Object> findWarehousesByCountryId(@PathVariable Integer id) {
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
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Warehouses against given country id." + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}/inventories", method = RequestMethod.GET)
	public ResponseEntity<Object> findInventoriesByCountryId(@PathVariable Integer id) {
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
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Inventories against given country id." + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
