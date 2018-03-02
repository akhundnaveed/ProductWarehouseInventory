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
 * The Inventory entity class mapped to inventory table
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
	
	/**
	 * The serial version id
	 */
	private static final long serialVersionUID = 234836039579056477L;
	
	/**
	 * Unique id for Inventory entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * The product for inventory
	 */
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	/**
	 * The product size for inventory
	 */
	@ManyToOne
	@JoinColumn(name = "product_size_id", nullable = false)
	private ProductSize productSize;
	
	/**
	 * The product type for inventory
	 */
	@ManyToOne
	@JoinColumn(name = "product_type_id", nullable = false)
	private ProductType productType;
	
	/**
	 * The warehouse for inventory
	 */
	@ManyToOne
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;
	
	/**
	 * The in stock quantity of inventory
	 */
	@Column(name = "in_stock_quantity")
	private Integer inStockQuantity;
	
	/**
	 * The available quantity of inventory
	 */
	@Column(name = "available_quantity")
	private Integer availableQuantity;
	
	/**
	 * The in transit quantity of inventory
	 */
	@Column(name = "in_transit_quantity")
	private Integer inTransitQuantity;
	
	/**
	 * The minimum order quantity of inventory
	 */
	@Column(name = "minimum_order_quantity")
	private Integer minimumOrderQuantity;
	
	/**
	 * The per box quantity of inventory
	 */
	@Column(name = "per_box_quantity")
	private Integer perBoxQuantity;
	
	/**
	 * The re-order quantity of inventory
	 */
	@Column(name = "reorder_point")
	private Integer reorderPoint;
	
	/**
	 * No arguments constructor
	 */
	public Inventory() {
	}
	
	/**
	 * @param id
	 *            Unique id for Inventory entity
	 * @param product
	 *            The product for Inventory entity
	 * @param productSize
	 *            The product size for Inventory entity
	 * @param productType
	 *            The product type for Inventory entity
	 * @param warehouse
	 *            The warehouse for Inventory entity
	 * @param inStockQuantity
	 *            The in stock quantity of Inventory entity
	 * @param availableQuantity
	 *            The available quantity of Inventory entity
	 * @param inTransitQuantity
	 *            The in transit quantity of Inventory entity
	 * @param minimumOrderQuantity
	 *            The minimum order quantity of Inventory entity
	 * @param perBoxQuantity
	 *            The per box quantity of Inventory entity
	 * @param reorderPoint
	 *            The re-order quantity of Inventory entity
	 */
	public Inventory(Integer id, Product product, ProductSize productSize, ProductType productType, Warehouse warehouse,
			Integer inStockQuantity, Integer availableQuantity, Integer inTransitQuantity, Integer minimumOrderQuantity,
			Integer perBoxQuantity, Integer reorderPoint) {
		this.id = id;
		this.product = product;
		this.productSize = productSize;
		this.productType = productType;
		this.warehouse = warehouse;
		this.inStockQuantity = inStockQuantity;
		this.availableQuantity = availableQuantity;
		this.inTransitQuantity = inTransitQuantity;
		this.minimumOrderQuantity = minimumOrderQuantity;
		this.perBoxQuantity = perBoxQuantity;
		this.reorderPoint = reorderPoint;
	}
	
	/**
	 * Get the unique id for Inventory entity
	 * 
	 * @return id the id of Inventory entity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the unique id for Inventory entity
	 * 
	 * @param id
	 *            the id of Inventory entity
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the product for Inventory entity
	 * 
	 * @return Product the product of Inventory entity
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Set the product for Inventory entity
	 * 
	 * @param Product
	 *            the product of Inventory entity
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Get the product size for Inventory entity
	 * 
	 * @return ProductSize the product size of Inventory entity
	 */
	public ProductSize getProductSize() {
		return productSize;
	}
	
	/**
	 * Set the product size for Inventory entity
	 * 
	 * @param ProductSize
	 *            the product size of Inventory entity
	 */
	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}
	
	/**
	 * Get the product type for Inventory entity
	 * 
	 * @return ProductType the product type of Inventory entity
	 */
	public ProductType getProductType() {
		return productType;
	}
	
	/**
	 * Set the product type for Inventory entity
	 * 
	 * @param ProductType
	 *            the product type of Inventory entity
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	/**
	 * Get the warehouse for Inventory entity
	 * 
	 * @return Warehouse the warehouse of Inventory entity
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	/**
	 * Set the warehouse for Inventory entity
	 * 
	 * @param Warehouse
	 *            the warehouse of Inventory entity
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	/**
	 * Get the in stock quantity for Inventory entity
	 * 
	 * @return inStockQuantity the inStockQuantity of Inventory entity
	 */
	public Integer getInStockQuantity() {
		return inStockQuantity;
	}
	
	/**
	 * Set the in stock quantity for Inventory entity
	 * 
	 * @param inStockQuantity
	 *            the inStockQuantity of Inventory entity
	 */
	public void setInStockQuantity(Integer inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}
	
	/**
	 * Get the available quantity for Inventory entity
	 * 
	 * @return availableQuantity the availableQuantity of Inventory entity
	 */
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	
	/**
	 * Set the available quantity for Inventory entity
	 * 
	 * @param availableQuantity
	 *            the availableQuantity of Inventory entity
	 */
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	/**
	 * Get the in transit quantity for Inventory entity
	 * 
	 * @return inTransitQuantity the inTransitQuantity of Inventory entity
	 */
	public Integer getInTransitQuantity() {
		return inTransitQuantity;
	}
	
	/**
	 * Set the in transit quantity for Inventory entity
	 * 
	 * @param inTransitQuantity
	 *            the inTransitQuantity of Inventory entity
	 */
	public void setInTransitQuantity(Integer inTransitQuantity) {
		this.inTransitQuantity = inTransitQuantity;
	}
	
	/**
	 * Get the minimum order quantity for Inventory entity
	 * 
	 * @return minimumOrderQuantity the minimumOrderQuantity of Inventory entity
	 */
	public Integer getMinimumOrderQuantity() {
		return minimumOrderQuantity;
	}
	
	/**
	 * Set the minimum order quantity for Inventory entity
	 * 
	 * @param minimumOrderQuantity
	 *            the minimumOrderQuantity of Inventory entity
	 */
	public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}
	
	/**
	 * Get the per box quantity for Inventory entity
	 * 
	 * @return perBoxQuantity the perBoxQuantity of Inventory entity
	 */
	public Integer getPerBoxQuantity() {
		return perBoxQuantity;
	}
	
	/**
	 * Set the per box quantity for Inventory entity
	 * 
	 * @param perBoxQuantity
	 *            the perBoxQuantity of Inventory entity
	 */
	public void setPerBoxQuantity(Integer perBoxQuantity) {
		this.perBoxQuantity = perBoxQuantity;
	}
	
	/**
	 * Get the reorder quantity for Inventory entity
	 * 
	 * @return reorderPoint the reorderPoint of Inventory entity
	 */
	public Integer getReorderPoint() {
		return reorderPoint;
	}
	
	/**
	 * Set the reorder quantity for Inventory entity
	 * 
	 * @param reorderPoint
	 *            the reorderPoint of Inventory entity
	 */
	public void setReorderPoint(Integer reorderPoint) {
		this.reorderPoint = reorderPoint;
	}
	
	/**
	 * Get all properties values of the Inventory entity as string
	 * 
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
