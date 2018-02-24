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
 * @author naveed
 *
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234836039579056477L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private Product product;

	@ManyToOne
    @JoinColumn(name="product_size_id", nullable=false)
	private ProductSize productSize;

	@ManyToOne
    @JoinColumn(name="product_type_id", nullable=false)
	private ProductType productType;

	@ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
	private Warehouse warehouse;
	
	@Column(name="in_stock_quantity")
	private Integer inStockQuantity;
	
	@Column(name="available_quantity")
	private Integer availableQuantity;
	
	@Column(name="in_transit_quantity")
	private Integer inTransitQuantity;
	
	@Column(name="minimum_order_quantity")
	private Integer minimumOrderQuantity;
	
	@Column(name="per_box_quantity")
	private Integer perBoxQuantity;
	
	@Column(name="reorder_point")
	private Integer reorderPoint;

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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the productSize
	 */
	public ProductSize getProductSize() {
		return productSize;
	}

	/**
	 * @param productSize the productSize to set
	 */
	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	/**
	 * @return the productType
	 */
	public ProductType getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @return the inStockQuantity
	 */
	public Integer getInStockQuantity() {
		return inStockQuantity;
	}

	/**
	 * @param inStockQuantity the inStockQuantity to set
	 */
	public void setInStockQuantity(Integer inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}

	/**
	 * @return the availableQuantity
	 */
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return the inTransitQuantity
	 */
	public Integer getInTransitQuantity() {
		return inTransitQuantity;
	}

	/**
	 * @param inTransitQuantity the inTransitQuantity to set
	 */
	public void setInTransitQuantity(Integer inTransitQuantity) {
		this.inTransitQuantity = inTransitQuantity;
	}

	/**
	 * @return the minimumOrderQuantity
	 */
	public Integer getMinimumOrderQuantity() {
		return minimumOrderQuantity;
	}

	/**
	 * @param minimumOrderQuantity the minimumOrderQuantity to set
	 */
	public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}

	/**
	 * @return the perBoxQuantity
	 */
	public Integer getPerBoxQuantity() {
		return perBoxQuantity;
	}

	/**
	 * @param perBoxQuantity the perBoxQuantity to set
	 */
	public void setPerBoxQuantity(Integer perBoxQuantity) {
		this.perBoxQuantity = perBoxQuantity;
	}

	/**
	 * @return the reorderPoint
	 */
	public Integer getReorderPoint() {
		return reorderPoint;
	}

	/**
	 * @param reorderPoint the reorderPoint to set
	 */
	public void setReorderPoint(Integer reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory [id=");
		builder.append(id);
		builder.append(", product=");
		builder.append(product);
		builder.append(", productSize=");
		builder.append(productSize);
		builder.append(", productType=");
		builder.append(productType);
		builder.append(", warehouse=");
		builder.append(warehouse);
		builder.append(", inStockQuantity=");
		builder.append(inStockQuantity);
		builder.append(", availableQuantity=");
		builder.append(availableQuantity);
		builder.append(", inTransitQuantity=");
		builder.append(inTransitQuantity);
		builder.append(", minimumOrderQuantity=");
		builder.append(minimumOrderQuantity);
		builder.append(", perBoxQuantity=");
		builder.append(perBoxQuantity);
		builder.append(", reorderPoint=");
		builder.append(reorderPoint);
		builder.append("]");
		return builder.toString();
	}
}
