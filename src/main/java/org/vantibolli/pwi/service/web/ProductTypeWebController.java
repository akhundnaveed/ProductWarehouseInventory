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
import org.vantibolli.pwi.model.ProductType;
import org.vantibolli.pwi.service.ProductTypeService;

/**
 * @author naveed
 *
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductTypeWebController.class);
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listProductTypes() {
		try {
			logger.info("Getting list of product types");
			
			List<ProductType> ptList = productTypeService.listAllProductTypes();
			
			if (ptList == null || ptList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Product types found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(ptList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all product types", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Product Types:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findProductType(@PathVariable Integer id) {
		try {
			logger.info("Finding by product type id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product Type id is missing"), HttpStatus.BAD_REQUEST);
			}

			ProductType productType = productTypeService.findProductTypeById(id);
			
			if (productType == null) {
				return new ResponseEntity<>(new Response(false, "Product type not found against given product type id:" + id), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(productType, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a product type against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addProductType(@RequestBody ProductType productType, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add product type {}", productType);
			
			if (StringUtils.isEmpty(productType.getType())) {
				return new ResponseEntity<>(new Response(false, "Product.type is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productTypeService.addProductType(productType);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/productType/{id}").buildAndExpand(productType.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Product Type. id:" + productType.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateProductType(@PathVariable Integer id, @RequestBody ProductType productType, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Update product type {}", productType);
			
			if (productType.getId() == null) {
				return new ResponseEntity<>(new Response(false, "ProductType.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(productType.getType())) {
				return new ResponseEntity<>(new Response(false, "Product type is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productTypeService.updateProductType(productType);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Product Type. id:" + productType.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating product type", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductType(@PathVariable Integer id) {
		try {
			logger.info("Deleting product type by id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product type id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			ProductType productType = productTypeService.findProductTypeById(id);
			
			if (productType == null) {
				return new ResponseEntity<>(new Response(false, "Product type could not be found to be deleted.id=" + id), HttpStatus.NOT_FOUND);
			}
			
			productTypeService.deleteProductType(productType);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Product type id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting product type ", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
