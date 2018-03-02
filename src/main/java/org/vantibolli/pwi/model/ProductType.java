/**
 * 
 */
package org.vantibolli.pwi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The ProductType entity class mapped to product type table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "product_type")
public class ProductType implements Serializable {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = -2059718286914668829L;
	
	/**
	 * Unique id for ProductType entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * The type of the ProductType entity
	 */
	@Column(name = "type")
	private String type;
	
	/**
	 * No arguments constructor
	 */
	public ProductType() {
	}
	
	/**
	 * @param id
	 *            Unique id for ProductType entity
	 * @param type
	 *            the type of the ProductType entity
	 */
	public ProductType(Integer id, String type) {
		this.id = id;
		this.type = type;
	}
	
	/**
	 * Get the unique id for ProductType entity
	 * 
	 * @return id the id of ProductType entity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the unique id for ProductType entity
	 * 
	 * @param id
	 *            the id of ProductType entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the type for ProductType entity
	 * 
	 * @return type the type of ProductType entity
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the type for ProductType entity
	 * 
	 * @param type
	 *            the type of ProductType entity
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get all properties values of the ProductType entity as string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductType [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
