package net.kzn.shoppingbackend.dao;
import java.util.List;
import org.springframework.stereotype.Service;
import net.kzn.shoppingbackend.dto.Category;

@Service
public interface CategoryDAO {
	List<Category> list();
	Category get(int id);
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}