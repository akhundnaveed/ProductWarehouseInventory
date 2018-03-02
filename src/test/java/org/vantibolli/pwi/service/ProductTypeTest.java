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
import org.vantibolli.pwi.dao.ProductTypeDao;
import org.vantibolli.pwi.model.ProductType;
import org.vantibolli.pwi.service.ProductTypeService;

/**
 * The class to test Product type related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class ProductTypeTest {
	
	@Mock
	private ProductTypeDao productTypeDao;
	
	@InjectMocks
	private ProductTypeService productTypeService;
	
	@Spy
	private List<ProductType> productTypeList = new ArrayList<>();
	
	/**
	 * Setup mockito mock objects and initialize test Product type objects list
	 */
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initProductTypeList();
	}
	
	/**
	 * Test listAllProductTypes service method
	 */
	@Test
	public void listAllProductTypes() {
		when(productTypeDao.findAll()).thenReturn(productTypeList);
		List<ProductType> prodList = productTypeService.listAllProductTypes();
		assertThat(prodList).isEqualTo(productTypeList);
	}
	
	/**
	 * Test saveProductType service method
	 */
	@Test
	public void saveProductType() {
		doNothing().when(productTypeDao).save(any(ProductType.class));
		productTypeService.addProductType(any(ProductType.class));
		verify(productTypeDao, atLeastOnce()).save(any(ProductType.class));
	}
	
	/**
	 * Test findProductTypeById service method
	 */
	@Test
	public void findProductTypeById() {
		ProductType productType = productTypeList.get(0);
		when(productTypeDao.findOne(anyInt())).thenReturn(productType);
		assertThat(productType).isEqualTo(productTypeService.findProductTypeById(productType.getId()));
	}
	
	/**
	 * Test updateProductType service method
	 */
	@Test
	public void updateProductType() {
		ProductType productType = productTypeList.get(0);
		when(productTypeDao.findOne(anyInt())).thenReturn(productType);
		productTypeService.updateProductType(productType);
		verify(productTypeDao, atLeastOnce()).findOne(anyInt());
	}
	
	/**
	 * Test deleteProductType service method
	 */
	@Test
	public void deleteProductType() {
		doNothing().when(productTypeDao).delete(any(ProductType.class));
		productTypeService.deleteProductType(any(ProductType.class));
		verify(productTypeDao, atLeastOnce()).delete(any(ProductType.class));
	}
	
	/**
	 * Initialize a test Product type list
	 */
	private void initProductTypeList() {
		ProductType productType = new ProductType();
		productType.setId(1);
		productType.setType("Finished Product");
		productTypeList.add(productType);
		
		productType = new ProductType();
		productType.setId(2);
		productType.setType("Component");
		productTypeList.add(productType);
		
		productType = new ProductType();
		productType.setId(3);
		productType.setType("Packaging Material");
		productTypeList.add(productType);
	}
}
