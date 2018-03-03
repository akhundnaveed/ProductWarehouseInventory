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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Spring web service controller class to perform Product Type related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Api(value = "Product Type API", tags = { "Product Type Web Services" })
@Controller
@RequestMapping("/productType")
public class ProductTypeWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductTypeWebController.class);
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * Get list of all Product Types from the database
	 * 
	 * @return list of Product Types
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of all Product types from the database", response = ProductType.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of product types fetched successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
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
	
	/**
	 * Find Product Type object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product Type object found from the database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find Product type object from the database against given country id", response = ProductType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product type record found"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Response.class),
			@ApiResponse(code = 404, message = "Not Found", response = Response.class) })
	public ResponseEntity<Object> findProductType(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product type id", value = "the id of Product to find", required = true) Integer id) {
		try {
			logger.info("Finding by product type id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product Type id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			ProductType productType = productTypeService.findProductTypeById(id);
			
			if (productType == null) {
				return new ResponseEntity<>(new Response(false, "Product type not found against given product type id:" + id),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(productType, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while finding a product type against given id", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while finding Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Store given Product Type object record in the database
	 * 
	 * @param productType
	 *            the Product Type object to be stored in the database
	 * @param ucBuilder
	 *            the builder object to expose a URI for newly created Product Type record
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Store given Product type object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Product type record created successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> addProductType(
			@RequestBody(required = true) @ApiParam(name = "product type", value = "the Product Type object to be stored in the database", required = true) ProductType productType,
			UriComponentsBuilder ucBuilder) {
		try {
			logger.info("Add product type {}", productType);
			
			if (StringUtils.isEmpty(productType.getType())) {
				return new ResponseEntity<>(new Response(false, "Product.type is missing"), HttpStatus.BAD_REQUEST);
			}
			
			productTypeService.addProductType(productType);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/productType/{id}").buildAndExpand(productType.getId()).toUri());
			
			return new ResponseEntity<>(new Response(true, "Successfully added Product Type. id:" + productType.getId()),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding product", e);
			return new ResponseEntity<>(new Response(false, "Error occurred while adding Product Type:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update given Product Type object record in the database
	 * 
	 * @param id
	 *            the id of Product Type object to be updated in the database
	 * @param product
	 *            the Product Type object to be updated in the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update given Product type object record in the database", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product type record updated successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request") })
	public ResponseEntity<Response> updateProductType(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product type id", value = "the id of Product Type object to be updated in the database", required = true) Integer id,
			@RequestBody(required = true) @ApiParam(name = "product type", value = "the Product Type object to be updated in the database", required = true) ProductType productType) {
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
	
	/**
	 * Delete the given Product Type object from the database
	 * 
	 * @param id
	 *            the id of Product Type object to be deleted from the database
	 * @return the response object with success true/false and message about the operation performed successfully or not
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete the given Product type object from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product type record deleted successfully", response = Response.class),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	public ResponseEntity<Object> deleteProductType(
			@PathVariable(name = "id", required = true) @ApiParam(name = "product type id", value = "the id of Product Type object to be deleted from the database", required = true) Integer id) {
		try {
			logger.info("Deleting product type by id: {}", id);
			if (id == null) {
				return new ResponseEntity<>(new Response(false, "Product type id is missing"), HttpStatus.BAD_REQUEST);
			}
			
			ProductType productType = productTypeService.findProductTypeById(id);
			
			if (productType == null) {
				return new ResponseEntity<>(new Response(false, "Product type could not be found to be deleted.id=" + id),
						HttpStatus.NOT_FOUND);
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
