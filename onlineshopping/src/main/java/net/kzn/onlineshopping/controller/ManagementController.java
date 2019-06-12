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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		}
		return mv;
	}
	//Handling product submission through the form 
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) {
		new ProductValidator().validate(mProduct, results);
		//Check if there are any error
		if(results.hasErrors()) {
			model.addAttribute("userClicksManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation for Product submission failed!!");
			return "page";
		}
		logger.info(mProduct.toString());
		productDAO.add(mProduct);
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			System.out.println("File name matched");
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	//Returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
}