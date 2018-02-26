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
 * @author naveed
 *
 */
public class ProductTypeTest {
	
	@Mock
	private ProductTypeDao productTypeDao;
	
	@InjectMocks
	private ProductTypeService productTypeService;
	
	@Spy
	private List<ProductType> productTypeList = new ArrayList<>();
	
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initProductTypeList();
	}
	
	@Test
	public void listProductTypes() {
		when(productTypeDao.findAll()).thenReturn(productTypeList);
		List<ProductType> prodList = productTypeService.listAllProductTypes();
		assertThat(prodList).isEqualTo(productTypeList);
	}
	
	@Test
	public void saveProductType() {
		doNothing().when(productTypeDao).save(any(ProductType.class));
		productTypeService.addProductType(any(ProductType.class));
		verify(productTypeDao, atLeastOnce()).save(any(ProductType.class));
	}
	
	@Test
	public void findById() {
		ProductType productType = productTypeList.get(0);
		when(productTypeDao.findOne(anyInt())).thenReturn(productType);
		assertThat(productType).isEqualTo(productTypeService.findProductTypeById(productType.getId()));
	}
	
	@Test
    public void updateProductType(){
		ProductType productType = productTypeList.get(0);
        when(productTypeDao.findOne(anyInt())).thenReturn(productType);
        productTypeService.updateProductType(productType);
        verify(productTypeDao, atLeastOnce()).findOne(anyInt());
    }
	
	@Test
    public void deleteProductType(){
        doNothing().when(productTypeDao).delete(any(ProductType.class));
        productTypeService.deleteProductType(any(ProductType.class));
        verify(productTypeDao, atLeastOnce()).delete(any(ProductType.class));
    }
	
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
