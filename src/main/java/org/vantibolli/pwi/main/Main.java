/**
 * 
 */
package org.vantibolli.pwi.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vantibolli.pwi.config.HibernateConfig;
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
public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Initializing context");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		logger.info("Context initialized");
		
		/*
		logger.info("Getting ProductDao bean");
		ProductDao productDao = context.getBean(ProductDao.class);
		
		List<Product> productList = productDao.findAll();
		
		logger.info("Product list size=" + productList.size());
		
		for (Product product : productList) {
			logger.info(product.toString());
		}
		
		Product product = productDao.findOne(1);
		logger.info(product.toString());
		
		product = new Product();
		product.setCode("PROD-003");
		product.setName("Product 3");
		productDao.save(product);
		*/

		/*
		logger.info("Getting CountryDao bean");
		CountryDao countryDao = context.getBean(CountryDao.class);
		Country country = new Country();
		country.setName("USA");
		countryDao.save(country);
		
		country = new Country();
		country.setName("Ireland");
		countryDao.save(country);

		country = new Country();
		country.setName("Netherlands");
		countryDao.save(country);

		country = new Country();
		country.setName("Dubai");
		countryDao.save(country);

		country = new Country();
		country.setName("Australia");
		countryDao.save(country);

		country = new Country();
		country.setName("Italy");
		countryDao.save(country);

		country = new Country();
		country.setName("Pakistan");
		countryDao.save(country);

		country = new Country();
		country.setName("Mexico");
		countryDao.save(country);

		List<Country> countryList = countryDao.findAll();
		
		for (Country country1 : countryList) {
			logger.info(country1.toString());
		}
		*/

		/*
		logger.info("Getting ProductSizeDao bean");
		ProductSizeDao productSizeDao = context.getBean(ProductSizeDao.class);
		ProductSize ps = new ProductSize();
		ps.setSize("10 ml");
		productSizeDao.save(ps);
		
		ps = new ProductSize();
		ps.setSize("20 ml");
		productSizeDao.save(ps);
		
		ps = new ProductSize();
		ps.setSize("30 ml");
		productSizeDao.save(ps);
		
		ps = new ProductSize();
		ps.setSize("Small");
		productSizeDao.save(ps);
		
		ps = new ProductSize();
		ps.setSize("Large");
		productSizeDao.save(ps);
		
		List<ProductSize> psList = productSizeDao.findAll();
		
		for (ProductSize productSize : psList) {
			logger.info(productSize.toString());
		}
		*/

		/*
		logger.info("Getting ProductTypeDao bean");
		ProductTypeDao productTypeDao = context.getBean(ProductTypeDao.class);
		
		List<ProductType> ptList = productTypeDao.findAll();
		
		for (ProductType productType : ptList) {
			logger.info(productType.toString());
		}
		*/

		/*
		logger.info("Getting WarehouseDao and CountryDao bean");
		
		WarehouseDao warehouseDao = context.getBean(WarehouseDao.class);
		CountryDao countryDao = context.getBean(CountryDao.class);
		Country usa = countryDao.findOne(1);
		Country ire = countryDao.findOne(2);
		
		Warehouse warehouse = new Warehouse();
		warehouse.setName("Warehouse 1");
		warehouse.setCountry(usa);
		warehouseDao.save(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setName("Warehouse 2");
		warehouse.setCountry(usa);
		warehouseDao.save(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setName("Warehouse 3");
		warehouse.setCountry(usa);
		warehouseDao.save(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setName("Warehouse 4");
		warehouse.setCountry(ire);
		warehouseDao.save(warehouse);
		
		warehouse = new Warehouse();
		warehouse.setName("Warehouse 5");
		warehouse.setCountry(ire);
		warehouseDao.save(warehouse);
		
		List<Warehouse> warehouseList = warehouseDao.findAll();
		
		for (Warehouse warehouse2 : warehouseList) {
			logger.info(warehouse2.toString());
		}
		*/

		/*
		logger.info("Getting All Daos bean");

		ProductDao productDao = context.getBean(ProductDao.class);
		ProductSizeDao productSizeDao = context.getBean(ProductSizeDao.class);
		ProductTypeDao productTypeDao = context.getBean(ProductTypeDao.class);
		WarehouseDao warehouseDao = context.getBean(WarehouseDao.class);
		InventoryDao inventoryDao = context.getBean(InventoryDao.class);
		
		Product product1 = productDao.findOne(1);
		
		ProductSize ps1 = productSizeDao.findOne(1);
		ProductSize ps2 = productSizeDao.findOne(2);
		ProductSize ps3 = productSizeDao.findOne(3);
		
		ProductType pt1 = productTypeDao.findOne(1);
		ProductType pt2 = productTypeDao.findOne(2);
		ProductType pt3 = productTypeDao.findOne(3);
		
		Warehouse wh1 = warehouseDao.findOne(1);
		Warehouse wh2 = warehouseDao.findOne(2);
		Warehouse wh3 = warehouseDao.findOne(3);
		Warehouse wh4 = warehouseDao.findOne(4);
		Warehouse wh5 = warehouseDao.findOne(5);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouse(wh1);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(10);
		inventory.setAvailableQuantity(8);
		inventory.setInTransitQuantity(5);
		inventory.setMinimumOrderQuantity(50);
		inventory.setPerBoxQuantity(6);
		inventory.setReorderPoint(15);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh1);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt2);
		inventory.setInStockQuantity(10);
		inventory.setAvailableQuantity(8);
		inventory.setInTransitQuantity(5);
		inventory.setMinimumOrderQuantity(50);
		inventory.setPerBoxQuantity(6);
		inventory.setReorderPoint(15);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh1);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt3);
		inventory.setInStockQuantity(10);
		inventory.setAvailableQuantity(8);
		inventory.setInTransitQuantity(5);
		inventory.setMinimumOrderQuantity(50);
		inventory.setPerBoxQuantity(6);
		inventory.setReorderPoint(15);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh2);
		inventory.setProduct(product1);
		inventory.setProductSize(ps2);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(900);
		inventory.setAvailableQuantity(100);
		inventory.setInTransitQuantity(1000);
		inventory.setMinimumOrderQuantity(1000);
		inventory.setPerBoxQuantity(12);
		inventory.setReorderPoint(100);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh3);
		inventory.setProduct(product1);
		inventory.setProductSize(ps3);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(150);
		inventory.setAvailableQuantity(90);
		inventory.setInTransitQuantity(80);
		inventory.setMinimumOrderQuantity(50);
		inventory.setPerBoxQuantity(36);
		inventory.setReorderPoint(45);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh3);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(100);
		inventory.setAvailableQuantity(50);
		inventory.setInTransitQuantity(30);
		inventory.setMinimumOrderQuantity(45);
		inventory.setPerBoxQuantity(50);
		inventory.setReorderPoint(100);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh2);
		inventory.setProduct(product1);
		inventory.setProductSize(ps3);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(40);
		inventory.setAvailableQuantity(32);
		inventory.setInTransitQuantity(12);
		inventory.setMinimumOrderQuantity(34);
		inventory.setPerBoxQuantity(43);
		inventory.setReorderPoint(9);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh4);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(10);
		inventory.setAvailableQuantity(7);
		inventory.setInTransitQuantity(5);
		inventory.setMinimumOrderQuantity(1);
		inventory.setPerBoxQuantity(4);
		inventory.setReorderPoint(6);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh4);
		inventory.setProduct(product1);
		inventory.setProductSize(ps3);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(100);
		inventory.setAvailableQuantity(100);
		inventory.setInTransitQuantity(0);
		inventory.setMinimumOrderQuantity(5);
		inventory.setPerBoxQuantity(36);
		inventory.setReorderPoint(10);
		inventoryDao.save(inventory);
		
		inventory = new Inventory();
		inventory.setWarehouse(wh5);
		inventory.setProduct(product1);
		inventory.setProductSize(ps1);
		inventory.setProductType(pt1);
		inventory.setInStockQuantity(10);
		inventory.setAvailableQuantity(0);
		inventory.setInTransitQuantity(100);
		inventory.setMinimumOrderQuantity(10);
		inventory.setPerBoxQuantity(8);
		inventory.setReorderPoint(10);
		inventoryDao.save(inventory);
		
		List<Inventory> li = inventoryDao.findAll();
		
		for (Inventory inventory2 : li) {
			logger.info(inventory2.toString());
		}
		*/
		
		context.close();
	}
	
}
