package net.kzn.shoppingbackend.test;
import org.junit.Test;
import org.junit.BeforeClass;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import static org.junit.Assert.assertEquals;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase {
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	private static UserDAO userDAO;
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	/* @Test
	public void testAdd() {
		//Code to add User records to the table
		user = new User();
		user.setFirstName("Kapil");
		user.setLastName("Kumar");
		user.setEmail("kapil.public@gmail.com");
		user.setContactNumber("1357924680");
		user.setRole("User");
		user.setPassword("P13579");
		assertEquals("Failed to add the user record to the table!", true, userDAO.addUser(user));

		//Code to add address records to the table
		address = new Address();
		address.setAddressLineOne("A-2, Sparx Building");
		address.setAddressLineTwo("Sector 63");
		address.setCity("Noida");
		address.setState("Uttar Pradesh");
		address.setCountry("India");
		address.setPostalCode("201301");
		address.setBilling(true);
		address.setUserId(user.getId());   //Linking user with the address using user Id
		assertEquals("Failed to add the address record to the table!", true, userDAO.addAddress(address));
		
		if(user.getRole().equalsIgnoreCase("User")) {
			//Creating a cart for this user
			cart = new Cart();
			cart.setUser(user);
			assertEquals("Failed to add the cart to the table!", true, userDAO.addCart(cart)); //Adding the cart to the user
			
			address = new Address();
			address.setAddressLineOne("A-22, HCL Building");
			address.setAddressLineTwo("Sector 60");
			address.setCity("Noida");
			address.setState("Uttar Pradesh");
			address.setCountry("India");
			address.setPostalCode("201501");
			address.setShipping(true);
			address.setUserId(user.getId());   //Linking user with the address using user Id
			assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address)); //Adding the shipping address for the user
		}
	} */
	@Test
	public void testAdd() {
		//Code to add User records to the table
		user = new User();
		user.setFirstName("Deepak");
		user.setLastName("Kumar");
		user.setEmail("deepak@gmail.com");
		user.setContactNumber("9015077882");
		user.setRole("User");
		user.setPassword("D77882");
		
		if(user.getRole().equalsIgnoreCase("User")) {
			//Creating a cart for this user
			cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);  //Attaching cart with the user
		}
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	}
}