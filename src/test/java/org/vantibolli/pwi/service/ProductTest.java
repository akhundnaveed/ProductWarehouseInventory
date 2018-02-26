/**
 * 
 */
package org.vantibolli.pwi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vantibolli.pwi.dao.ProductDao;
import org.vantibolli.pwi.model.Product;
import org.vantibolli.pwi.service.ProductService;

/**
 * @author naveed
 *
 */
public class ProductTest {
	
	@Mock
	private ProductDao productDao;
	
	@InjectMocks
	private ProductService productService;
	
	@Spy
	private List<Product> productList = new ArrayList<>();
	
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initProductList();
	}
	
	@Test
	public void listProducts() {
		when(productDao.findAll()).thenReturn(productList);
		List<Product> prodList = productService.listAllProducts();
		assertThat(prodList).isEqualTo(productList);
	}
	
	@Test
	public void saveProduct() {
		doNothing().when(productDao).save(any(Product.class));
		productService.addProduct(any(Product.class));
		verify(productDao, atLeastOnce()).save(any(Product.class));
	}
	
	@Test
	public void findById() {
		Product product = productList.get(0);
		when(productDao.findOne(anyInt())).thenReturn(product);
		assertThat(product).isEqualTo(productService.findProductById(product.getId()));
	}
	
	@Test
    public void updateProduct(){
		Product product = productList.get(0);
        when(productDao.findOne(anyInt())).thenReturn(product);
        productService.updateProduct(product);
        verify(productDao, atLeastOnce()).findOne(anyInt());
    }
	
	@Test
    public void deleteProduct(){
        doNothing().when(productDao).delete(any(Product.class));
        productService.deleteProduct(any(Product.class));
        verify(productDao, atLeastOnce()).delete(any(Product.class));
    }
	
	private void initProductList() {
		Product product = new Product();
		product.setId(4);
		product.setCode("PROD-004");
		product.setName("Product 4");
		productList.add(product);
		
		product = new Product();
		product.setId(5);
		product.setCode("PROD-005");
		product.setName("Product 5");
		productList.add(product);
		
		product = new Product();
		product.setId(6);
		product.setCode("PROD-006");
		product.setName("Product 6");
		productList.add(product);
	}
}
