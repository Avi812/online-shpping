package net.kzn.shoppingbackend.dao;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;
import net.kzn.shoppingbackend.dto.Address;

public interface UserDAO {
	boolean addUser(User user); //Method to add user to database table
	boolean addAddress(Address address); //Method to add address to database table
	boolean addCart(Cart cart); //Method to add Cart to database table
}