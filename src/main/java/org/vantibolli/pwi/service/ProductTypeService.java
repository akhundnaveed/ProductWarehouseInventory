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
 * @author naveed
 *
 */
@Service
public class ProductTypeService {
	
	@Autowired
	private ProductTypeDao productTypeDao;
	
	public List<ProductType> listAllProductTypes() {
		return productTypeDao.findAll();
	}
	
	public void addProductType(ProductType productType) {
		productTypeDao.save(productType);
	}
	
	public void updateProductType(ProductType productType) {
		productTypeDao.update(productType);
	}
	
	public ProductType findProductTypeById(Integer id) {
		return productTypeDao.findOne(id);
	}
	
	public void deleteProductType(ProductType productType) {
		productTypeDao.delete(productType);
	}
}
