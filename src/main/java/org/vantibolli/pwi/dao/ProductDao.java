/**
 * 
 */
package org.vantibolli.pwi.dao;

import org.springframework.stereotype.Repository;
import org.vantibolli.pwi.model.Product;

/**
 * DAO class to perform database CRUD operations for Product entity
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Repository
public class ProductDao extends AbstractDao<Product, Integer> {
	
}
