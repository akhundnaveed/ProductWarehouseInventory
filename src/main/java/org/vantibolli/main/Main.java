/**
 * 
 */
package org.vantibolli.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vantibolli.model.Product;
import org.vantibolli.pwi.config.HibernateConfig;
import org.vantibolli.pwi.dao.ProductDao;

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
		
		
		context.close();
	}
	
}
