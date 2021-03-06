package net.kzn.onlineshopping.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import net.kzn.onlineshopping.util.FileUploadUtility;
import net.kzn.onlineshopping.validator.ProductValidator;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		//Setting few of the fields of the product
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		if(operation != null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product data is successfully submitted!!");
			}
			if(operation.equals("category")) {
				mv.addObject("message", "Category data is successfully submitted!!");
			}
		}
		return mv;
	}
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = productDAO.get(id);  //Fetching the product from the database
		mv.addObject("product", nProduct);      //Setting the product fetched from the database
		return mv;
	}	
	//Handling product submission through the form 
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) {
		//Handling image validation for new product
		if(mProduct.getId() == 0)
			new ProductValidator().validate(mProduct, results);
		else {
			if(!mProduct.getFile().getOriginalFilename().equals(""))
				new ProductValidator().validate(mProduct, results);
		}
		//Check if there are any error
		if(results.hasErrors()) {
			model.addAttribute("userClicksManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation for Product submission failed!!");
			return "page";
		}
		logger.info(mProduct.toString());
		//Create a new product record
		if(mProduct.getId() == 0)
			productDAO.add(mProduct);
		else
			productDAO.update(mProduct); //Updating the product if Id isn't 0
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			System.out.println("File name matched");
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}	
	//Handling category submission through the modal form 
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute("category") Category category) {
		categoryDAO.add(category);   //Adding a new category to the database table
		return "redirect:/manage/products?operation=category";
	}
	//Handling product activation and deactivation 
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST) @ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		//Fetches the requested product from DB
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		//Activating and Deactivating based on active field value
		product.setActive(!product.isActive());
		productDAO.update(product); //Up[dating the product record in the product table
		return (isActive) ?
				"You've successfully deactivated the product with id " + product.getId()
		        : "You've successfully activated the product with id " + product.getId();
	}
	//Returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	//Returns a new category
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}