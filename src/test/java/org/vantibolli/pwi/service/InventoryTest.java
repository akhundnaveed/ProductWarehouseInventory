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
 * @author naveed
 *
 */
public class InventoryTest {
	
	@Mock
	private InventoryDao inventoryDao;
	
	@InjectMocks
	private InventoryService inventoryService;
	
	@Spy
	private List<Inventory> inventoryList = new ArrayList<>();
	
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initInventoryList();
	}
	
	@Test
	public void listInventories() {
		when(inventoryDao.findAll()).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listAllInventories();
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	@Test
	public void listInventoriesByCountryId() {
		when(inventoryDao.findInventoriesByCountryId(anyInt())).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listInventoriesByCountryId(anyInt());
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	@Test
	public void listInventoriesByWarehouseId() {
		when(inventoryDao.findInventoriesByWarehouseId(anyInt())).thenReturn(inventoryList);
		List<Inventory> prodList = inventoryService.listInventoriesByWarehouseId(anyInt());
		assertThat(prodList).isEqualTo(inventoryList);
	}
	
	@Test
	public void saveInventory() {
		doNothing().when(inventoryDao).save(any(Inventory.class));
		inventoryService.addInventory(any(Inventory.class));
		verify(inventoryDao, atLeastOnce()).save(any(Inventory.class));
	}
	
	@Test
	public void findById() {
		Inventory inventory = inventoryList.get(0);
		when(inventoryDao.findOne(anyInt())).thenReturn(inventory);
		assertThat(inventory).isEqualTo(inventoryService.findInventoryById(inventory.getId()));
	}
	
	@Test
    public void updateInventory(){
		Inventory inventory = inventoryList.get(0);
        when(inventoryDao.findOne(anyInt())).thenReturn(inventory);
        inventoryService.updateInventory(inventory);
        verify(inventoryDao, atLeastOnce()).findOne(anyInt());
    }
	
	@Test
    public void deleteInventory(){
        doNothing().when(inventoryDao).delete(any(Inventory.class));
        inventoryService.deleteInventory(any(Inventory.class));
        verify(inventoryDao, atLeastOnce()).delete(any(Inventory.class));
    }
	
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
