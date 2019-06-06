package net.kzn.onlineshopping.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Controller
public class PageController {
	@Autowired
	private CategoryDAO categoryDAO;
	@RequestMapping(value= {"/", "/home", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome to the world of Spring MVC!!!");
		mv.addObject("title", "Home");
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
}