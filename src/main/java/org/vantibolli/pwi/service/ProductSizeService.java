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
 * @author naveed
 *
 */
@Service
public class ProductSizeService {
	
	@Autowired
	private ProductSizeDao productSizeDao;
	
	public List<ProductSize> listAllProductSizes() {
		return productSizeDao.findAll();
	}
	
	public void addProductSize(ProductSize productSize) {
		productSizeDao.save(productSize);
	}
	
	public void updateProductSize(ProductSize productSize) {
		productSizeDao.update(productSize);
	}
	
	public ProductSize findProductSizeById(Integer id) {
		return productSizeDao.findOne(id);
	}
	
	public void deleteProductSize(ProductSize productSize) {
		productSizeDao.delete(productSize);
	}
}
