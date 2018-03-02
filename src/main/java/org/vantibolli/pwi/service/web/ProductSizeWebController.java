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
import org.vantibolli.pwi.model.ProductSize;
import org.vantibolli.pwi.service.ProductSizeService;

/**
 * The Spring web service controller class to perform Product Size related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Controller
@RequestMapping("/productSize")
public class ProductSizeWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductSizeWebController.class);
	
	@Autowired
	private ProductSizeService productSizeService;
	
	/**
	 * Get list of all Product Sizes from the database
	 * 
	 * @return list of Product Sizes
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listProductSizes() {
		try {
			logger.info("Getting list of product sizes");
			List<ProductSize> psList = productSizeService.listAllProductSizes();
			
			if (psList == null || psList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Product sizes found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(psList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all product sizes", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Product Sizes:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find Product Size object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product Size object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findProductSize(@PathVariable Integer id) {
		try {
			logger.info("Finding by product size id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product Size id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			ProductSize productSize = productSizeService.findProductSizeById(id);
			
			if (productSize == null) {
				return new ResponseEntity<>(new Response(false, "Product size not found against given product size id:" + id),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(productSize, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a product size against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Product Size:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Store given Product Size object record in the database
	 * 
	 * @param productSize
	 *            the Product Size object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created product size record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addProductSize(@RequestBody ProductSize productSize, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add product size {}", productSize);
			
			if (StringUtils.isEmpty(productSize.getSize())) {
				return new ResponseEntity<>(new Response(false, "Product.size is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productSizeService.addProductSize(productSize);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/productSize/{id}").buildAndExpand(productSize.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Product Size. id:" + productSize.getId()),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Product Size:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update given Product Size object record in the database
	 * 
	 * @param id
	 *            the id of Product Size object to be updated in the database
	 * @param productSize
	 *            the Product Size object to be updated in the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateProductSize(@PathVariable Integer id, @RequestBody ProductSize productSize) {
		try {
			logger.info("Update product size {}", productSize);
			
			if (productSize.getId() == null) {
				return new ResponseEntity<>(new Response(false, "ProductSize.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(productSize.getSize())) {
				return new ResponseEntity<>(new Response(false, "Product size is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productSizeService.updateProductSize(productSize);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Product Size. id:" + productSize.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating product size", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Product Size:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete the given Product Size object from the database
	 * 
	 * @param id
	 *            the id of Product Size object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductSize(@PathVariable Integer id) {
		try {
			logger.info("Deleting product size by id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product size id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			ProductSize productSize = productSizeService.findProductSizeById(id);
			
			if (productSize == null) {
				return new ResponseEntity<>(new Response(false, "Product size could not be found to be deleted.id=" + id),
						HttpStatus.NOT_FOUND);
			}
			
			productSizeService.deleteProductSize(productSize);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Product size id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting product size ", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Product Size:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
