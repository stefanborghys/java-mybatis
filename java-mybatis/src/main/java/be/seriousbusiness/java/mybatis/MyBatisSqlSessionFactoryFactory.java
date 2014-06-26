package be.seriousbusiness.java.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisSqlSessionFactoryFactory {
	private static final Logger LOGGER=LoggerFactory.getLogger(MyBatisSqlSessionFactoryFactory.class);
	
	/**
	 * Create a new {@link SqlSessionFactory} based upon MyBatis XML configuration.</br>
	 * </br>
	 * Once created, the SqlSessionFactory should exist for the duration of your application execution.</br>
	 * There should be little or no reason to ever dispose of it or recreate it.</br>
	 * It's a best practice to not rebuild the SqlSessionFactory multiple times in an application run.</br>
	 * Doing so should be considered a “bad smell”.
	 * @return
	 */
	public static final SqlSessionFactory createXMLBasedSqlSessionFactoy(){
		final String resource = "org/mybatis/example/mybatis-test-config.xml";
		try {
			return  new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
		} catch (final IOException e) {
			LOGGER.error("The resource could not be found.",e);
			return null;
		}
		
	}
	
	/**
	 * Create a new {@link SqlSessionFactory} based upon MyBatis Java configuration.</br>
	 * </br>
	 * Once created, the SqlSessionFactory should exist for the duration of your application execution.</br>
	 * There should be little or no reason to ever dispose of it or recreate it.</br>
	 * It's a best practice to not rebuild the SqlSessionFactory multiple times in an application run.</br>
	 * Doing so should be considered a “bad smell”.
	 * @return
	 */
	public static final SqlSessionFactory createJavaBasedSqlSessionFactory(){
		final DataSource dataSource = null; // retrieve the dataSource
		final TransactionFactory transactionFactory = new JdbcTransactionFactory();
		final Environment environment = new Environment("development", transactionFactory, dataSource);
		final Configuration configuration = new Configuration(environment);
		configuration.addMapper(Object.class); // add the mapper
		return new SqlSessionFactoryBuilder().build(configuration);
	}

}
