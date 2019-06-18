package net.kzn.onlineshopping.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import net.kzn.onlineshopping.exception.ProductNotFoundException;

@Controller
public class PageController {
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@RequestMapping(value= {"/", "/home", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome to the world of Spring MVC!!!");
		mv.addObject("title", "Home");
		logger.info("Logger present inside page controller index method - INFO");
		logger.debug("Logger present inside page controller index method - DEBUG");
		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClicksHome", true);
		return mv;
	}
	@RequestMapping(value= "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClicksAbout", true);
		return mv;
	}
	@RequestMapping(value= "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClicksContact", true);
		return mv;
	}
	@RequestMapping(value= "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClicksAllProducts", true);
		return mv;
	}
	//Example of PathVariable annotation
	@RequestMapping(value= "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Category Products");
		//CategoryDAO to fetch a single category based on id
		Category category = null;
		category = categoryDAO.get(id);

		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		//Passing the single category object
		mv.addObject("category", category);
		mv.addObject("userClicksCategoryProducts", true);
		return mv;
	}
	//Code to view a single category product
	@RequestMapping(value= "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if(product == null) throw new ProductNotFoundException();
		//Code to update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClicksShowProduct", true);
		return mv;
	}
	//Example of RequestParam annotation
	@RequestMapping(value= "/test")
	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting) {
		if(greeting == null) {
			greeting = "Hello Avi!!!";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}
	/* Having similar mapping to our flow Id */
	@RequestMapping(value= "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Registration Page");
		return mv;
	}
}