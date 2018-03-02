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
 * The ProductSize entity class mapped to product size table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "product_size")
public class ProductSize implements Serializable {

	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = -6018748678509297452L;

	/**
	 * Unique id for ProductSize entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;

	/**
	 * Size of the ProductSize entity
	 */
	@Column(name="size")
	private String size;

	/**
	 * No arguments constructor
	 */
	public ProductSize() {
	}
	
	/**
	 * @param id
	 *            Unique id for ProductSize entity
	 * @param size
	 *            the size of the ProductSize entity
	 */
	public ProductSize(Integer id, String size) {
		this.id = id;
		this.size = size;
	}

	/**
	 * Get the unique id for ProductSize entity
	 * 
	 * @return id the id of ProductSize entity
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the unique id for ProductSize entity
	 * 
	 * @param id
	 *            the id of ProductSize entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the size for ProductSize entity
	 * 
	 * @return size the size of ProductSize entity
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Set the size for ProductSize entity
	 * 
	 * @param size
	 *            the size of ProductSize entity
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Get all properties values of the ProductSize entity as string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductSize [id=");
		builder.append(id);
		builder.append(", size=");
		builder.append(size);
		builder.append("]");
		return builder.toString();
	}
}
