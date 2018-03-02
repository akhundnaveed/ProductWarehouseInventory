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
 * The Country entity class mapped with country table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 2973679793032681940L;
	
	/**
	 * The unique id for Country entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * The name of the Country
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * No arguments constructor
	 */
	public Country() {
	}

	/**
	 * @param id
	 *           the unique id for Country entity
	 * @param name
	 *            the name of the Country entity
	 */
	public Country(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Get the unique id for Country entity
	 * 
	 * @return id
	 *            the id of Country entity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the unique id for Country entity
	 * 
	 * @param id
	 *            the id of Country entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the name of Country
	 * 
	 * @return name
	 *              the name of Country
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of Country
	 * 
	 * @param name
	 *            the name of Country
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get all properties values of the Country entity as string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
