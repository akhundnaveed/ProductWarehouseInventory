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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Spring web service controller class to perform Product Size related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Api(value = "ProductSize API", tags = { "Product Size Web Services" })
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
	@ApiOperation(value = "Get a list of all Product sizes from the database", response = ProductSize.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of product sizes fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
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
	@ApiOperation(value = "Find ProductSize object from the database against given product size id", response = ProductSize.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ProductSize record found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findProductSize(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product size id", value = "the id of Product to find", required = true) Integer id) {
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
	@ApiOperation(value = "Store given Product size object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Product size record created successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> addProductSize(
			@RequestBody(required = true) @ApiParam(name = "product size", value = "the Product Size object to be stored in the database", required = true) ProductSize productSize,
			UriComponentsBuilder ucBuilder) {
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
	@ApiOperation(value = "Update given Product size object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product size record updated successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> updateProductSize(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product size id", value = "the id of Product Size object to be updated in the database", required = true) Integer id,
			@RequestBody(required = true) @ApiParam(name = "product size", value = "the Product Size object to be updated in the database", required = true) ProductSize productSize) {
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
	@ApiOperation(value = "Delete the given Product size object from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product size record deleted successfully", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Object> deleteProductSize(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product size id", value = "the id of Product Size object to be deleted from the database", required = true) Integer id) {
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
