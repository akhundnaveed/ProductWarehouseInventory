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
 * @author naveed
 *
 */
@Entity
@Table(name = "product_size")
public class ProductSize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6018748678509297452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;
	
	@Column(name="size")
	private String size;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/* (non-Javadoc)
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
