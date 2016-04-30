package so.dun.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import so.dun.mybatis.po.User;

public interface UserDao {
	
	@Insert("insert into users(name,age) values(#{name},#{age})")
	public int addUser(User user);
	
	@Delete("delete from users where id = #{id}")
	public int deleteById(Integer id);
	
	@Update("update users set name=#{name},age=#{age} where id = #{id}")
	public int update(User user);
	
	@Select("select * from users where id=#{id}")
	public User getUserById(Integer id);
	
	@Select("select * from users")
	public List<User> getAllUser();
}
