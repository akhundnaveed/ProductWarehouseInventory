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
import org.vantibolli.pwi.dao.ProductSizeDao;
import org.vantibolli.pwi.model.ProductSize;
import org.vantibolli.pwi.service.ProductSizeService;

/**
 * The class to test Product size related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class ProductSizeTest {
	
	@Mock
	private ProductSizeDao productSizeDao;
	
	@InjectMocks
	private ProductSizeService productSizeService;
	
	@Spy
	private List<ProductSize> productSizeList = new ArrayList<>();

	/**
	 * Setup mockito mock objects and initialize test Product size objects list
	 */
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initProductSizeList();
	}

	/**
	 * Test listAllProductSizes service method
	 */
	@Test
	public void listProductSizes() {
		when(productSizeDao.findAll()).thenReturn(productSizeList);
		List<ProductSize> prodList = productSizeService.listAllProductSizes();
		assertThat(prodList).isEqualTo(productSizeList);
	}

	/**
	 * Test saveProductSize service method
	 */
	@Test
	public void saveProductSize() {
		doNothing().when(productSizeDao).save(any(ProductSize.class));
		productSizeService.addProductSize(any(ProductSize.class));
		verify(productSizeDao, atLeastOnce()).save(any(ProductSize.class));
	}

	/**
	 * Test findProductSizeById service method
	 */
	@Test
	public void findProductSizeById() {
		ProductSize productSize = productSizeList.get(0);
		when(productSizeDao.findOne(anyInt())).thenReturn(productSize);
		assertThat(productSize).isEqualTo(productSizeService.findProductSizeById(productSize.getId()));
	}

	/**
	 * Test updateProductSize service method
	 */
	@Test
    public void updateProductSize(){
		ProductSize productSize = productSizeList.get(0);
        when(productSizeDao.findOne(anyInt())).thenReturn(productSize);
        productSizeService.updateProductSize(productSize);
        verify(productSizeDao, atLeastOnce()).findOne(anyInt());
    }

	/**
	 * Test deleteProductSize service method
	 */
	@Test
    public void deleteProductSize(){
        doNothing().when(productSizeDao).delete(any(ProductSize.class));
        productSizeService.deleteProductSize(any(ProductSize.class));
        verify(productSizeDao, atLeastOnce()).delete(any(ProductSize.class));
    }

	/**
	 * Initialize a test Product size list
	 */
	private void initProductSizeList() {
		ProductSize productSize = new ProductSize();
		productSize.setId(4);
		productSize.setSize("10 ml");
		productSizeList.add(productSize);
		
		productSize = new ProductSize();
		productSize.setId(5);
		productSize.setSize("20 ml");
		productSizeList.add(productSize);
		
		productSize = new ProductSize();
		productSize.setId(6);
		productSize.setSize("30 ml");
		productSizeList.add(productSize);
	}
}
