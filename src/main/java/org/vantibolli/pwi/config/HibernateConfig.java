/**
 * 
 */
package org.vantibolli.pwi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.vantibolli.pwi.model.Country;
import org.vantibolli.pwi.model.Inventory;
import org.vantibolli.pwi.model.Product;
import org.vantibolli.pwi.model.ProductSize;
import org.vantibolli.pwi.model.ProductType;
import org.vantibolli.pwi.model.Warehouse;

/**
 * <h1>HibernateConfig</h1>
 * This class contains Spring configurations to user Hibernate ORM to connect to database. The hibernate sessionFactory bean is created to be used in DAO classes.
 * The hibernate transaction manager instance is also created. Tomcat dbcp BasicDataSource is also created which uses database connections pool.
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan({ "org.vantibolli.pwi.dao", "org.vantibolli.pwi.model" })
public class HibernateConfig {
	
	@Autowired
	private Environment env;
	
	/**
	 * This method creates a Spring Bean and returns the {@code DataSource} of specified database
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource getDataSrouce() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		
		return dataSource;
	}
	
	/**
	 * This method creates a Spring Bean and returns the Hibernate {@code SessionFactory} object
	 * 
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSrouce());
		
		Properties props = new Properties();
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan(new String[] { "org.vantibolli.pwi.model"});
		
		// TODO Add All Entity classes here
		factoryBean.setAnnotatedClasses(Product.class);
		factoryBean.setAnnotatedClasses(ProductSize.class);
		factoryBean.setAnnotatedClasses(ProductType.class);
		factoryBean.setAnnotatedClasses(Country.class);
		factoryBean.setAnnotatedClasses(Warehouse.class);
		factoryBean.setAnnotatedClasses(Inventory.class);
		
		return factoryBean;
	}
	
	/**
	 * This method creates a Spring Bean and returns the Hibernate {@code HibernateTransactionManager} object to make all Hibernate operations as transactional
	 * 
	 * @return HibernateTransactionManager
	 */
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
