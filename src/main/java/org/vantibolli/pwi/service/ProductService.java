/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.ProductDao;
import org.vantibolli.pwi.model.Product;

/**
 * The Spring Service class to perform Product related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	/**
	 * Get list of all Products from the database
	 * 
	 * @return list of Products
	 */
	public List<Product> listAllProducts() {
		return productDao.findAll();
	}
	
	/**
	 * Store given Product object record in the database
	 * 
	 * @param product
	 *            the Product object to be stored in the database
	 */
	public void addProduct(Product product) {
		productDao.save(product);
	}
	
	/**
	 * Update given Product object record in the database
	 * 
	 * @param product
	 *            the Product object to be updated in the database
	 */
	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
	/**
	 * Find Product object from database against given product id
	 * 
	 * @param id
	 *            the id of Product to find
	 * @return Product object found from the database
	 */
	public Product findProductById(Integer id) {
		return productDao.findOne(id);
	}
	
	/**
	 * Delete the given Product object from the database
	 * 
	 * @param product
	 *            the Product object to be deleted from the database
	 */
	public void deleteProduct(Product product) {
		productDao.delete(product);
	}
}
