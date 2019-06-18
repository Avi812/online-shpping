package net.kzn.onlineshopping.handler;
import net.kzn.shoppingbackend.dto.User;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import org.springframework.stereotype.Component;
import net.kzn.onlineshopping.model.RegisterModel;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class RegisterHandler {
	@Autowired
	private UserDAO userDAO;
	public RegisterModel init() {
		return new RegisterModel();
	}
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		//Checking if password and confirm password matches
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage((MessageResolver) new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password doesn't match the confirm password!!")
					.build()
					);
			transitionValue = "failure";
		}
		//Checking the uniqueness of the email id
		if(userDAO.getByEmail(user.getEmail()) != null) {
			error.addMessage((MessageResolver) new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email Id is already registered. Kindly chooese a different one!")
					.build()
					);
			transitionValue = "failure";
		}
		return transitionValue;
	}
	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		//Fetch the user
		User user = registerModel.getUser();
		if(user.getRole().equalsIgnoreCase("User")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		userDAO.addUser(user); //Saving the user
		//Fetch the user
		Address billing = registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		userDAO.addAddress(billing); //Saving the address
		return transitionValue;
	}
}