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
import org.vantibolli.pwi.dao.InventoryDao;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.service.InventoryService;

/**
 * The class to test Inventory related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class InventoryTest {
	
	@Mock
	private InventoryDao inventoryDao;
	
	@InjectMocks
	private InventoryService inventoryService;
	
	@Spy
	private List<Inventory> inventoryList = new ArrayList<>();
	
	/**
	 * Setup mockito mock objects and initialize test Inventory objects list
	 */
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initInventoryList();
	}
	
	/**
	 * Test listAllInventories service method
	 */
	@Test
	public void listInventories() {
		when(inventoryDao.findAll()).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listAllInventories();
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	/**
	 * Test listAllInventoriesByCountryId service method
	 */
	@Test
	public void listInventoriesByCountryId() {
		when(inventoryDao.findInventoriesByCountryId(anyInt())).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listInventoriesByCountryId(anyInt());
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	/**
	 * Test listAllInventoriesByWarehouseId service method
	 */
	@Test
	public void listInventoriesByWarehouseId() {
		when(inventoryDao.findInventoriesByWarehouseId(anyInt())).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listInventoriesByWarehouseId(anyInt());
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	/**
	 * Test saveInventory service method
	 */
	@Test
	public void saveInventory() {
		doNothing().when(inventoryDao).save(any(Inventory.class));
		inventoryService.addInventory(any(Inventory.class));
		verify(inventoryDao, atLeastOnce()).save(any(Inventory.class));
	}
	
	/**
	 * Test findInventoryById service method
	 */
	@Test
	public void findInventoryById() {
		Inventory inventory = inventoryList.get(0);
		when(inventoryDao.findOne(anyInt())).thenReturn(inventory);
		assertThat(inventory).isEqualTo(inventoryService.findInventoryById(inventory.getId()));
	}
	
	/**
	 * Test updateInventory service method
	 */
	@Test
	public void updateInventory() {
		Inventory inventory = inventoryList.get(0);
		when(inventoryDao.findOne(anyInt())).thenReturn(inventory);
		inventoryService.updateInventory(inventory);
		verify(inventoryDao, atLeastOnce()).findOne(anyInt());
	}
	
	/**
	 * Test deleteInventory service method
	 */
	@Test
	public void deleteInventory() {
		doNothing().when(inventoryDao).delete(any(Inventory.class));
		inventoryService.deleteInventory(any(Inventory.class));
		verify(inventoryDao, atLeastOnce()).delete(any(Inventory.class));
	}
	
	/**
	 * Initialize a test Inventory list
	 */
	private void initInventoryList() {
		Inventory inventory = new Inventory();
		inventory.setId(11);
		inventory.setInStockQuantity(1000);
		inventory.setAvailableQuantity(2000);
		inventory.setInTransitQuantity(3000);
		inventory.setMinimumOrderQuantity(4000);
		inventory.setPerBoxQuantity(500);
		inventory.setReorderPoint(2500);
		inventoryList.add(inventory);
		
		inventory = new Inventory();
		inventory.setId(12);
		inventory.setInStockQuantity(1000);
		inventory.setAvailableQuantity(2000);
		inventory.setInTransitQuantity(3000);
		inventory.setMinimumOrderQuantity(4000);
		inventory.setPerBoxQuantity(500);
		inventory.setReorderPoint(2500);
		inventoryList.add(inventory);
		
		inventory = new Inventory();
		inventory.setId(11);
		inventory.setInStockQuantity(1000);
		inventory.setAvailableQuantity(2000);
		inventory.setInTransitQuantity(3000);
		inventory.setMinimumOrderQuantity(4000);
		inventory.setPerBoxQuantity(500);
		inventory.setReorderPoint(2500);
		inventoryList.add(inventory);
	}
}
