package so.dun.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import so.dun.mybatis.dao.UserDao;
import so.dun.mybatis.po.Classes;
import so.dun.mybatis.po.ConditionUser;
import so.dun.mybatis.po.Order;
import so.dun.mybatis.po.User;

public class MybatisTest {
	
	public  SqlSession getSession(){
		//String resource = "conf.xml"; 
		//加载mybatis的配置文件（它也加载关联的映射文件）
		//Reader reader = Resources.getResourceAsReader(resource); 
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream("conf.xml");
		//构建sqlSession的工厂
		//SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Reader);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//创建能执行映射文件中sql的sqlSession
		return sessionFactory.openSession();
	}
	
	@Test
	public void xmlTestAddUser(){
		SqlSession session = getSession();
		String statement = "userMapper.addUser";
		int insertRows = session.insert(statement, new User("AA",23));
		session.commit();
		session.close();
		System.out.println(insertRows);
	}
	
	@Test
	public void testAddUser(){
		SqlSession session = getSession();
		UserDao userDao = session.getMapper(UserDao.class);
		int insertRows = userDao.addUser(new User("AA",23));
		session.commit();
		session.close();
		System.out.println(insertRows);
	}
	
	@Test
	public void testDeleteUser(){
		SqlSession session = getSession();
		String statement = "userMapper.deleteUser";
		int delete = session.delete(statement, 5);
		session.commit();
		session.close();
		System.out.println(delete);
	}
	
	@Test
	public void testUpdateUser(){
		SqlSession session = getSession();
		String statement = "userMapper.updateUser";
		int update = session.update(statement, new User(5,"BB",25));
		session.commit();
		session.close();
		System.out.println(update);
	}

	@Test
	public void testGetUser(){
		SqlSession session = getSession();
		//映射sql的标识字符串
		String statement = "userMapper.getUser";
		//执行查询返回一个唯一user对象的sql
		User user = session.selectOne(statement, 2);
		session.close();
		System.out.println(user);
	}

	@Test
	public void testGetAllUser(){
		SqlSession session = getSession();
		String statement = "userMapper.getAllUsers";
		List<User> users = session.selectList(statement);
		session.close();
		System.out.println(users);
	}
	
	@Test
	public void testGetOrder(){
		SqlSession session = getSession();
		//映射sql的标识字符串
		String statement = "orderMapper.getOrder";
		//String statement = "orderMapper.getOrder2";
		//执行查询返回一个唯一user对象的sql
		Order order= session.selectOne(statement, 1);
		session.close();
		System.out.println(order);
	}
	
	@Test
	public void testGetClass(){
		SqlSession session = getSession();
		//测试一对一关联关系（连接方案）
		//String statement = "classMapper.getClass";
		//测试一对一关联关系（嵌套方案）
		//String statement = "classMapper.getClass2";
		//测试一对多关联关系（连接方案）
		//String statement = "classMapper.getClass3";
		//测试一对多关联关系（嵌套方案）
		String statement = "classMapper.getClass4";
		Classes classes  = session.selectOne(statement, 1);
		session.close();
		System.out.println(classes);
	}
	
	
	@Test
	public void testGetUser2(){
		//实现多条件查询用户(姓名模糊匹配, 年龄在指定的最小值到最大值之间)
		SqlSession session = getSession();
		String statement = "userMapper.getUser2";
		List<User> users = session.selectList(statement, new ConditionUser("%null%",11,18));
		session.close();
		System.out.println(users);
	}

}
