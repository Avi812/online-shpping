package net.kzn.shoppingbackend.dto;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity @Table(name="USER_DETAIL")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	//Private fields for User
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	@NotBlank(message="Please enter the first name!!")
	private String firstName;
	@Column(name="last_name")
	@NotBlank(message="Please enter the last name!!")
	private String lastName;
	@Email(message = "Please enter a valid email address!")
	@NotBlank(message = "Please enter email address!")
	private String email;
	@Column(name="contact_number")
	private String contactNumber;
	private String role;
	@NotBlank(message = "Please enter the password!")
	private String password;
	@Transient
	private String confirmPassword;
	private boolean enabled = true;
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Cart cart;
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	//toString() for logging and debugging facility
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
}