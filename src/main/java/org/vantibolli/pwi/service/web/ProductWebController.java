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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Spring web service controller class to perform Product related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Api(value = "Product API", tags = { "Product Web Services" })
@Controller
@RequestMapping("/product")
public class ProductWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductWebController.class);
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Get list of all Products from the database
	 * 
	 * @return list of Products
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of all Products from the database", response = Product.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of products fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
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
	
	/**
	 * Find Product object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find Product object from the database against given product id", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product record found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findProduct(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product id", value = "the id of Product to find", required = true) Integer id) {
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
	
	/**
	 * Store given Product object record in the database
	 * 
	 * @param product
	 *            the Product object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created Product record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Store given Product object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Product record created successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> addProduct(@RequestBody(required = true) @ApiParam(name = "product", value = "the Product object to be stored in the database", required = true) Product product,
			UriComponentsBuilder ucBuilder) {
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
	
	/**
	 * Update given Product object record in the database
	 * 
	 * @param id
	 *            the id of the Product object to be updated
	 * @param product
	 *            the Product object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update given Product object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product record updated successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> updateProduct(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product id", value = "the id of the Product object to be updated", required = true) Integer id,
			@RequestBody(required = true) @ApiParam(name = "product", value = "the Product object to be deleted from the database", required = true) Product product) {
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
	
	/**
	 * Delete the given Product object from the database
	 * 
	 * @param id
	 *            the id of Product to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the given Product object from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product record deleted successfully", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Object> deleteProduct(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product id", value = "the id of Product to be deleted from the database", required = true) Integer id) {
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
