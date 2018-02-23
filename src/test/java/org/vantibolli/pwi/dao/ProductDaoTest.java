/**
 * 
 */
package org.vantibolli.pwi.dao;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vantibolli.model.Product;

/**
 * @author naveed
 *
 */
public class ProductDaoTest {
	
	private static Logger logger = LoggerFactory.getLogger(ProductDaoTest.class);
	
	@Mock
	private ProductDao productDao;
	
	@Spy
	private List<Product> productList = new ArrayList<>();
	
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        initProductList();
    }
	
	@Test
	public void listProducts() {
		when(productDao.findAll()).thenReturn(productList);
		List<Product> prodList = productDao.findAll();
		
		logger.info("Product list size=" + productList.size());
		
		for (Product product : productList) {
			logger.info(product.toString());
		}
		assertThat(prodList).isEqualTo(productList);
	}
	
	private void initProductList() {
		Product product = new Product();
		product.setCode("PROD-004");
		product.setName("Product 4");
		productList.add(product);
		
		product = new Product();
		product.setCode("PROD-005");
		product.setName("Product 5");
		productList.add(product);
		
		product = new Product();
		product.setCode("PROD-006");
		product.setName("Product 6");
		productList.add(product);
	}
}
