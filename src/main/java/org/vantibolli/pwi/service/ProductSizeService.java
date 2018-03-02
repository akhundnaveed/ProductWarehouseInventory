/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.ProductSizeDao;
import org.vantibolli.pwi.model.ProductSize;

/**
 * The Spring Service class to perform Product Size related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class ProductSizeService {
	
	@Autowired
	private ProductSizeDao productSizeDao;
	
	/**
	 * Get list of all Product Sizes from the database
	 * 
	 * @return list of Product Sizes
	 */
	public List<ProductSize> listAllProductSizes() {
		return productSizeDao.findAll();
	}
	
	/**
	 * Store given Product Size object record in the database
	 * 
	 * @param productSize
	 *            the Product Size object to be stored in the database
	 */
	public void addProductSize(ProductSize productSize) {
		productSizeDao.save(productSize);
	}
	
	/**
	 * Update given Product Size object record in the database
	 * 
	 * @param productSize
	 *            the Product Size object to be updated in the database
	 */
	public void updateProductSize(ProductSize productSize) {
		productSizeDao.update(productSize);
	}
	
	/**
	 * Find Product Size object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product Size object found from the database
	 */
	public ProductSize findProductSizeById(Integer id) {
		return productSizeDao.findOne(id);
	}
	
	/**
	 * Delete the given Product Size object from the database
	 * 
	 * @param productSize
	 *            the Product Size object to be deleted from the database
	 */
	public void deleteProductSize(ProductSize productSize) {
		productSizeDao.delete(productSize);
	}
}
