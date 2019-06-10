package net.kzn.shoppingbackend.dao;
import java.util.List;
import net.kzn.shoppingbackend.dto.Product;

public interface ProductDAO {
	Product get(int id);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	//Business methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}