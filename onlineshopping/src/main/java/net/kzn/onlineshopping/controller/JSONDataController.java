package net.kzn.onlineshopping.controller;
import java.util.List;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dao.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/json/data")
public class JSONDataController {
	@Autowired
	private ProductDAO productDAO;
	@ResponseBody
	@RequestMapping(value = "/all/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productDAO.listActiveProducts();
	}
	@ResponseBody
	@RequestMapping(value = "/admin/all/products", method = RequestMethod.GET)
	public List<Product> getAllProductsForAdmin() {
		return productDAO.list();
	}
	@ResponseBody
	@RequestMapping(value = "/category/{id}/products", method = RequestMethod.GET)
	public List<Product> getProductsByCategory(@PathVariable int id) {
		return productDAO.listActiveProductsByCategory(id);
	}
}