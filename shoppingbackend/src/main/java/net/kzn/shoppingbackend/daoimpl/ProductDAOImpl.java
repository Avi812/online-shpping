package net.kzn.shoppingbackend.daoimpl;
import java.util.List;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dao.ProductDAO;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}
	//Getting single product based on id
	@Override
	public Product get(int id) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
	}
	@Override
	public boolean add(Product product) {
		try {
			//Add the product to the database table
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean update(Product product) {
		try {
			//Update the product in the database table
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(Product product) {
		try {
			//Delete the product from the database table
			product.setActive(false);
			return this.update(product);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product where active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product where active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession().createQuery("FROM Product where active = :active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}
}