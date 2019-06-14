package net.kzn.shoppingbackend.dto;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Cart {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name="uid")
	private User user;
	@Column(name="grand_total")
	private String grandTotal;
	@Column(name="cart_lines")
	private String cartLines;
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getCartLines() {
		return cartLines;
	}
	public void setCartLines(String cartLines) {
		this.cartLines = cartLines;
	}
	//toString() for logging and debugging facility
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines + "]";
	}
}