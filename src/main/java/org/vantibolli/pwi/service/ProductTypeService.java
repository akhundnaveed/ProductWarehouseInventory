/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.ProductTypeDao;
import org.vantibolli.pwi.model.ProductType;

/**
 * The Spring Service class to perform Product Type related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class ProductTypeService {
	
	@Autowired
	private ProductTypeDao productTypeDao;
	
	/**
	 * Get list of all Product Types from the database
	 * 
	 * @return list of Product Types
	 */
	public List<ProductType> listAllProductTypes() {
		return productTypeDao.findAll();
	}
	
	/**
	 * Store given Product Type object record in the database
	 * 
	 * @param productType
	 *            the Product Type object to be stored in the database
	 */
	public void addProductType(ProductType productType) {
		productTypeDao.save(productType);
	}
	
	/**
	 * Update given Product Type object record in the database
	 * 
	 * @param productType
	 *            the Product Type object to be updated in the database
	 */
	public void updateProductType(ProductType productType) {
		productTypeDao.update(productType);
	}
	
	/**
	 * Find Product Type object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product Type object found from the database
	 */
	public ProductType findProductTypeById(Integer id) {
		return productTypeDao.findOne(id);
	}
	
	/**
	 * Delete the given Product Type object from the database
	 * 
	 * @param productType
	 *            the Product Type object to be deleted from the database
	 */
	public void deleteProductType(ProductType productType) {
		productTypeDao.delete(productType);
	}
}
