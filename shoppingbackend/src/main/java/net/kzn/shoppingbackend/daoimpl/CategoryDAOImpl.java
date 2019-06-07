package net.kzn.shoppingbackend.daoimpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category where active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		System.out.println(query.getResultList());
		return query.getResultList();
	}
	//Getting single category based on id
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}
	@Override
	public boolean add(Category category) {
		try {
			//Add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean update(Category category) {
		try {
			//Add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			//Add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}