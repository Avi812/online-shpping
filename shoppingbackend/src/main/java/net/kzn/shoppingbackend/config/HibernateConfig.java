package net.kzn.shoppingbackend.config;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"net.kzn.shoppingbackend.dto"})
public class HibernateConfig {
	private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER = "org.h2.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME = "sa";
	private final static String DATABASE_PASSWORD = "";
	
	@Autowired
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		//Providing database connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;  
	}
	
	//SessionFactory object would be created here
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		SessionFactory factory = null;
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(getDataSource());
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("net.kzn.shoppingbackend.dto");
		try {
			factory = builder.buildSessionFactory();
		} catch(HibernateException hex) {
			hex.printStackTrace();
			return null;
		}
		return factory;
	}
    
	//All the hibernate properties will be created here
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.pool_size", 1);
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		return properties;
	}
	
	//TransactionManager bean is created in this method
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}