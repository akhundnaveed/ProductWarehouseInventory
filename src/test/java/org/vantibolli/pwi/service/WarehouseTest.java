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
import org.vantibolli.pwi.dao.WarehouseDao;
import org.vantibolli.pwi.model.Warehouse;
import org.vantibolli.pwi.service.WarehouseService;

/**
 * The class to test Warehouse related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class WarehouseTest {
	
	@Mock
	private WarehouseDao warehouseDao;
	
	@InjectMocks
	private WarehouseService warehouseService;
	
	@Spy
	private List<Warehouse> warehouseList = new ArrayList<>();
	
	/**
	 * Setup mockito mock objects and initialize test Warehouse objects list
	 */
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initWarehouseList();
	}
	
	/**
	 * Test listAllWarehouses service method
	 */
	@Test
	public void listWarehouses() {
		when(warehouseDao.findAll()).thenReturn(warehouseList);
		List<Warehouse> prodList = warehouseService.listAllWarehouses();
		assertThat(prodList).isEqualTo(warehouseList);
	}
	
	/**
	 * Test listWarehousesByCountryId service method
	 */
	@Test
	public void listWarehousesByCountryId() {
		when(warehouseDao.findWarehousesByCountryId(anyInt())).thenReturn(warehouseList);
		List<Warehouse> prodList = warehouseService.listAllWarehousesByCountryId(anyInt());
		assertThat(prodList).isEqualTo(warehouseList);
	}
	
	/**
	 * Test saveWarehouse service method
	 */
	@Test
	public void saveWarehouse() {
		doNothing().when(warehouseDao).save(any(Warehouse.class));
		warehouseService.addWarehouse(any(Warehouse.class));
		verify(warehouseDao, atLeastOnce()).save(any(Warehouse.class));
	}
	
	/**
	 * Test findWarehouseById service method
	 */
	@Test
	public void findWarehouseById() {
		Warehouse warehouse = warehouseList.get(0);
		when(warehouseDao.findOne(anyInt())).thenReturn(warehouse);
		assertThat(warehouse).isEqualTo(warehouseService.findWarehouseById(warehouse.getId()));
	}
	
	/**
	 * Test updateWarehouse service method
	 */
	@Test
	public void updateWarehouse() {
		Warehouse warehouse = warehouseList.get(0);
		when(warehouseDao.findOne(anyInt())).thenReturn(warehouse);
		warehouseService.updateWarehouse(warehouse);
		verify(warehouseDao, atLeastOnce()).findOne(anyInt());
	}
	
	/**
	 * Test deleteWarehouse service method
	 */
	@Test
	public void deleteWarehouse() {
		doNothing().when(warehouseDao).delete(any(Warehouse.class));
		warehouseService.deleteWarehouse(any(Warehouse.class));
		verify(warehouseDao, atLeastOnce()).delete(any(Warehouse.class));
	}
	
	/**
	 * Initialize a test Warehouse list
	 */
	private void initWarehouseList() {
		Warehouse warehouse = new Warehouse();
		warehouse.setId(101);
		warehouse.setName("Warehouse 101");
		warehouseList.add(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setId(102);
		warehouse.setName("Warehouse 102");
		warehouseList.add(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setId(103);
		warehouse.setName("Warehouse 103");
		warehouseList.add(warehouse);
	}
}
