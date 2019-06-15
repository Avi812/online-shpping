package net.kzn.shoppingbackend.dao;
import java.util.List;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;
import net.kzn.shoppingbackend.dto.Address;

public interface UserDAO {
	boolean addUser(User user); //Method to add user to database table
	User getByEmail(String email); //Method to get user details by email Id
	boolean addAddress(Address address); //Method to add address to database table
	Address getBillingAddress(User user); //Method to get billing address of the user
	List<Address> listShippingAddress(User user); //Method to get the list of shipping address of the user
	boolean updateCart(Cart cart); //Method to update Cart to database table
}