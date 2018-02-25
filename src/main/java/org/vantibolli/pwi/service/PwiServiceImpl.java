/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.CountryDao;
import org.vantibolli.pwi.dao.InventoryDao;
import org.vantibolli.pwi.dao.ProductDao;
import org.vantibolli.pwi.dao.ProductSizeDao;
import org.vantibolli.pwi.dao.ProductTypeDao;
import org.vantibolli.pwi.dao.WarehouseDao;
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
@Service
public class PwiServiceImpl implements PwiService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductSizeDao productSizeDao;
	
	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private WarehouseDao warehouseDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllProducts()
	 */
	@Override
	public List<Product> listAllProducts() {
		return productDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addProduct(org.vantibolli.pwi.model.Product)
	 */
	@Override
	public void addProduct(Product product) {
		product.setId(null);
		productDao.save(product);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#updateProduct(org.vantibolli.pwi.model.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findProductById(java.lang.Integer)
	 */
	@Override
	public Product findProductById(Integer id) {
		return productDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteProduct(java.lang.Integer)
	 */
	@Override
	public void deleteProduct(Integer id) {
		productDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllProductSizes()
	 */
	@Override
	public List<ProductSize> listAllProductSizes() {
		return productSizeDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addProductSize(org.vantibolli.pwi.model.ProductSize)
	 */
	@Override
	public void addProductSize(ProductSize productSize) {
		productSizeDao.save(productSize);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#updateProductSize(org.vantibolli.pwi.model.ProductSize)
	 */
	@Override
	public void updateProductSize(ProductSize productSize) {
		productSizeDao.update(productSize);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findProductSizeById(java.lang.Integer)
	 */
	@Override
	public ProductSize findProductSizeById(Integer id) {
		return productSizeDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteProductSize(java.lang.Integer)
	 */
	@Override
	public void deleteProductSize(Integer id) {
		productSizeDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllProductTypes()
	 */
	@Override
	public List<ProductType> listAllProductTypes() {
		return productTypeDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addProductType(org.vantibolli.pwi.model.ProductType)
	 */
	@Override
	public void addProductType(ProductType productType) {
		productTypeDao.save(productType);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#updateProductType(org.vantibolli.pwi.model.ProductType)
	 */
	@Override
	public void updateProductType(ProductType productType) {
		productTypeDao.update(productType);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findProductTypeById(java.lang.Integer)
	 */
	@Override
	public ProductType findProductTypeById(Integer id) {
		return productTypeDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteProductType(java.lang.Integer)
	 */
	@Override
	public void deleteProductType(Integer id) {
		productTypeDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listCountries()
	 */
	@Override
	public List<Country> listCountries() {
		return countryDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addCountry(org.vantibolli.pwi.model.Country)
	 */
	@Override
	public void addCountry(Country country) {
		countryDao.save(country);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findCountryById(java.lang.Integer)
	 */
	@Override
	public Country findCountryById(Integer id) {
		return countryDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteCountry(java.lang.Integer)
	 */
	@Override
	public void deleteCountry(Integer id) {
		countryDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllWarehouses()
	 */
	@Override
	public List<Warehouse> listAllWarehouses() {
		return warehouseDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllWarehousesByCountryId(java.lang.Integer)
	 */
	@Override
	public List<Warehouse> listAllWarehousesByCountryId(Integer countryId) {
		return warehouseDao.findWarehousesByCountryId(countryId);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addWarehouse(org.vantibolli.pwi.model.Warehouse)
	 */
	@Override
	public void addWarehouse(Warehouse warehouse) {
		warehouseDao.save(warehouse);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#updateWarehouse(org.vantibolli.pwi.model.Warehouse)
	 */
	@Override
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDao.update(warehouse);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findWarehouseById(java.lang.Integer)
	 */
	@Override
	public Warehouse findWarehouseById(Integer id) {
		return warehouseDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteWarehouse(java.lang.Integer)
	 */
	@Override
	public void deleteWarehouse(Integer id) {
		warehouseDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listAllInventories()
	 */
	@Override
	public List<Inventory> listAllInventories() {
		return inventoryDao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listInventoriesByCountryId(java.lang.Integer)
	 */
	@Override
	public List<Inventory> listInventoriesByCountryId(Integer countryId) {
		return inventoryDao.findInventoriesByCountryId(countryId);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#listInventoriesByWarehouseId(java.lang.Integer)
	 */
	@Override
	public List<Inventory> listInventoriesByWarehouseId(Integer warehouseId) {
		return inventoryDao.findInventoriesByWarehouseId(warehouseId);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#addInventory(org.vantibolli.pwi.model.Inventory)
	 */
	@Override
	public void addInventory(Inventory inventory) {
		inventoryDao.save(inventory);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#updateInventory(org.vantibolli.pwi.model.Inventory)
	 */
	@Override
	public void updateInventory(Inventory inventory) {
		inventoryDao.update(inventory);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#findInventoryById(java.lang.Integer)
	 */
	@Override
	public Inventory findInventoryById(Integer id) {
		return inventoryDao.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see org.vantibolli.pwi.service.PwiService#deleteInventory(java.lang.Integer)
	 */
	@Override
	public void deleteInventory(Integer id) {
		inventoryDao.deleteById(id);
	}
}
