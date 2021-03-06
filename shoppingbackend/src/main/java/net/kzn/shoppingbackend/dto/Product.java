package net.kzn.shoppingbackend.dto;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Entity @SuppressWarnings("deprecation")
public class Product {
	//Private fields
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	@NotBlank(message="Please enter the Name of the Product!")
	private String name;
	@NotBlank(message="Please enter the Name of the Brand!")
	private String brand;
	@JsonIgnore
	@NotBlank(message="Please enter the description of the Product!")
	private String description;
	@Column(name="unit_price") @Min(value=1, message="Price can't be less than 1")
	private double unitPrice;
	//@JsonIgnore
	@Column(name="is_active")
	private boolean active;
	@Column(name="category_id")
	private int categoryId;
	@JsonIgnore
	@Column(name="supplier_id")
	private int supplierId;
	private int quantity, purchases, views;
	@Transient
	private MultipartFile file;
	//Default Constructor
	public Product() {
		this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
	}
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getPurchases() {
		return purchases;
	}
	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}