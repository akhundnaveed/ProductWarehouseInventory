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
 * @author naveed
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> listAllProducts() {
		return productDao.findAll();
	}
	
	public void addProduct(Product product) {
		productDao.save(product);
	}
	
	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
	public Product findProductById(Integer id) {
		return productDao.findOne(id);
	}
	
	public void deleteProduct(Product product) {
		productDao.delete(product);
	}
}
