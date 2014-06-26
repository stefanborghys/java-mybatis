package be.seriousbusiness.java.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Singleton implementation managing the creation of {@link SqlSession} objects</br>
 * using only one {@link SqlSessionFactory} instance.
 * @author seriousbusiness
 *
 */
public class MyBatisSqlSessionFactory {
	private static MyBatisSqlSessionFactory myBatisSqlSessionFactory=null;
	private static final SqlSessionFactory sqlSessionFactory=MyBatisSqlSessionFactoryFactory.createXMLBasedSqlSessionFactoy();
	
	private MyBatisSqlSessionFactory(){
	}
	
	/**
	 * Enforce the use of only one {@link MyBatisSqlSessionFactory} by returning a new instance when not yet initialized</br>
	 * and reusing an existing when initialized.</br>
	 * @return
	 */
	public synchronized static final MyBatisSqlSessionFactory getInstance() {
        if (myBatisSqlSessionFactory==null){
        	myBatisSqlSessionFactory=new MyBatisSqlSessionFactory();
        }
        return myBatisSqlSessionFactory;
    }
	
	/**
	 * Create a new {@link SqlSession} object.</br>
	 * Don't forget to close it when finished!.
	 * @return
	 */
	public final SqlSession create(){
		return sqlSessionFactory.openSession();
	}

}
