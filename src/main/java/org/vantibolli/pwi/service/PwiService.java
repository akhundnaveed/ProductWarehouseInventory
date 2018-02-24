/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.vantibolli.pwi.model.Product;

/**
 * @author naveed
 *
 */
public interface PwiService {
	
	public List<Product> listAllProducts();
	
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public Product findProductById(Integer id);
}
