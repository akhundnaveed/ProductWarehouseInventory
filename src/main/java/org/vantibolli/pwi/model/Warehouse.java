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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Warehouse entity class mapped to warehouse table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 7753662518405257620L;
	
	/**
	 * The unique id for Warehouse entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * the name of the Warehouse entity
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * the country of the Warehouse entity
	 */
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	/**
	 * No arguments constructor
	 */
	public Warehouse() {
	}
	
	/**
	 * @param id
	 *            the unique id for Warehouse entity
	 * @param name
	 *            the name of the Warehouse entity
	 * @param country
	 *            the country of the Warehouse entity
	 */
	public Warehouse(Integer id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}
	
	/**
	 * Get the unique id for Warehouse entity
	 * 
	 * @return id the id of Warehouse entity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the unique id for Warehouse entity
	 * 
	 * @param id
	 *            the id of Warehouse entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the name of Warehouse
	 * 
	 * @return name the name of Warehouse
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of Warehouse
	 * 
	 * @param name
	 *            the name of Warehouse
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the country of Warehouse
	 * 
	 * @return country the country of Warehouse
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * Set the country of Warehouse
	 * 
	 * @param country
	 *            the country of Warehouse
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	/**
	 * Get all properties values of the Warehouse entity as string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Warehouse [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}
}
