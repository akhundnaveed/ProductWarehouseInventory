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
 * The Product entity class mapped to product table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 7936597457556875181L;
	
	/**
	 * The unique id for Product entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * the name of the Product entity
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * the code of the Product entity
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * No arguments constructor
	 */
	public Product() {
	}
	
	/**
	 * @param id
	 *            the unique id for Product entity
	 * @param name
	 *            the name of the Product entity
	 * @param code
	 *            the code of the Product entity
	 */
	public Product(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	/**
	 * Get the unique id for Product entity
	 * 
	 * @return id the id of Product entity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the unique id for Product entity
	 * 
	 * @param id
	 *            the id of Product entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the name for Product entity
	 * 
	 * @return name the name of Product entity
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name for Product entity
	 * 
	 * @param name
	 *            the name of Product entity
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the code for Product entity
	 * 
	 * @return code the code of Product entity
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Set the name for Product entity
	 * 
	 * @param code
	 *            the code of Product entity
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Get all properties values of the Product entity as string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
}
