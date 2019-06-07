package net.kzn.shoppingbackend.test;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	/* @Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Television");
		category.setDescription("Television Description");
		category.setImageURL("CAT_1.png");

		category = new Category();
		category.setName("Mobile");
		category.setDescription("Mobile Description");
		category.setImageURL("CAT_2.png");

		category = new Category();
		category.setName("Laptop");
		category.setDescription("Laptop Description");
		category.setImageURL("CAT_3.png");
		
		assertEquals("Data for category table is successfully added!", true, categoryDAO.add(category));
	} */
	
	/* @Test
	public void testGetCategory() {
		category = categoryDAO.get(3);
		assertEquals("Data for category table is successfully fetched!", "Laptop", category.getName());
	} */
	
	/* @Test
	public void testUpdateCategory() {
		category = categoryDAO.get(3);
		category.setName("Computer");
		category.setDescription("Computer Description");
		assertEquals("Data for category table is successfully updated!", true, categoryDAO.update(category));
	} */
	
	/* @Test
	public void testDeleteCategory() {
		category = categoryDAO.get(3);
		assertEquals("Data for category table is successfully deleted!", true, categoryDAO.delete(category));
	} */
		
	/* @Test
	public void testListCategory() {
		assertEquals("Data for list of categories from the table is successfully fetched!", 2, categoryDAO.list().size());
	} */
	
	@Test
	public void testCRUDCategory() {
		//Add operation
		category = new Category();
		category.setName("Stuffs for Men");
		category.setDescription("Description of stuffs for men");
		category.setImageURL("CAT_4.png");
		assertEquals("Data in category table is successfully added!", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Stuffs for Women");
		category.setDescription("Women Stuffs Description");
		category.setImageURL("CAT_5.png");
		assertEquals("Data in category table is successfully added!", true, categoryDAO.add(category));
		
		//Code for fetch and update operation
		category = categoryDAO.get(4);
		category.setName("Male Stuffs");
		category.setDescription("Male Stuffs Description");		
		assertEquals("Data from category table is successfully fetched!", "Male Stuffs", category.getName());
		
		//Code for delete operation	
		category = categoryDAO.get(5);
		assertEquals("Data for category table is successfully deleted!", true, categoryDAO.delete(category));
		
		//Code for fetching all the records from the table	
		assertEquals("Data for list of categories from the table is successfully fetched!", 3, categoryDAO.list().size());
	}
}