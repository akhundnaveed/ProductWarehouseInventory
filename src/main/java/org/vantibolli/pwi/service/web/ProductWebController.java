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
import org.vantibolli.pwi.model.Product;
import org.vantibolli.pwi.service.ProductService;

/**
 * @author naveed
 *
 */
@Controller
@RequestMapping("/product")
public class ProductWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductWebController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listProducts() {
		try {
			logger.info("Getting list of products");
			List<Product> productsList = productService.listAllProducts();
			
			if (productsList == null || productsList.isEmpty()) {
				return new ResponseEntity<>(new Response(false, "No Products found"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(productsList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while getting list of all products", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while getting list of all Products:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findProduct(@PathVariable Integer id) {
		try {
			logger.info("Finding product id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Product product = productService.findProductById(id);
			
			if (product == null) {
				return new ResponseEntity<>(new Response(false, "Product not found against given product id:" + id), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a product against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Product:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add product {}", product);
			
			if (StringUtils.isEmpty(product.getCode())) {
				return new ResponseEntity<>(new Response(false, "Product.code is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(product.getName())) {
				return new ResponseEntity<>(new Response(false, "Product.name is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productService.addProduct(product);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Product. id:" + product.getId()), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Product:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateProduct(@PathVariable Integer id, @RequestBody Product product, UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Update product {}", product);
			
			if (product.getId() == null) {
				return new ResponseEntity<>(new Response(false, "Product.id is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(product.getCode())) {
				return new ResponseEntity<>(new Response(false, "Product.code is missing"), HttpStatus.BAD_REQUEST);
			} else if (StringUtils.isEmpty(product.getName())) {
				return new ResponseEntity<>(new Response(false, "Product.name is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productService.updateProduct(product);
			
			return new ResponseEntity<>(new Response(true, "Successfully updated Product. id:" + product.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while updating Product:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
		try {
			logger.info("Deleting product id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product.id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			Product product = productService.findProductById(id);
			
			if (product == null) {
				return new ResponseEntity<>(new Response(false, "Product could not be found to be deleted.id=" + id), HttpStatus.NOT_FOUND);
			}
			
			productService.deleteProduct(product);
			
			return new ResponseEntity<>(new Response(true, "Successfully deleted Product.id=" + id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while deleting Product:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
