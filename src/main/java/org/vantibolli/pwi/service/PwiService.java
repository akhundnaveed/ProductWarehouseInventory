/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.vantibolli.pwi.model.Country;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.model.Product;
import org.vantibolli.pwi.model.ProductSize;
import org.vantibolli.pwi.model.ProductType;
import org.vantibolli.pwi.model.Warehouse;

/**
 * @author naveed
 *
 */
public interface PwiService {
	
	public List<Product> listAllProducts();
	
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public Product findProductById(Integer id);
	
	public void deleteProduct(Integer id);
	
	public List<ProductSize> listAllProductSizes();
	
	public void addProductSize(ProductSize productSize);
	
	public void updateProductSize(ProductSize productSize);
	
	public ProductSize findProductSizeById(Integer id);
	
	public void deleteProductSize(Integer id);
	
	public List<ProductType> listAllProductTypes();
	
	public void addProductType(ProductType productType);
	
	public void updateProductType(ProductType productType);
	
	public ProductType findProductTypeById(Integer id);
	
	public void deleteProductType(Integer id);
	
	public List<Country> listCountries();
	
	public void addCountry(Country country);
	
	public Country findCountryById(Integer id);
	
	public void deleteCountry(Integer id);
	
	public List<Warehouse> listAllWarehouses();
	
	public List<Warehouse> listAllWarehousesByCountryId(Integer countryId);
	
	public void addWarehouse(Warehouse warehouse);
	
	public void updateWarehouse(Warehouse warehouse);
	
	public Warehouse findWarehouseById(Integer id);
	
	public void deleteWarehouse(Integer id);
	
	public List<Inventory> listAllInventories();
	
	public List<Inventory> listInventoriesByCountryId(Integer countryId);
	
	public List<Inventory> listInventoriesByWarehouseId(Integer warehouseId);
	
	public void addInventory(Inventory inventory);

	public void updateInventory(Inventory inventory);

	public Inventory findInventoryById(Integer id);

	public void deleteInventory(Integer id);
}
