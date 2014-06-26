package be.seriousbusiness.java.mybatis.mapping;

import org.apache.ibatis.annotations.Select;

import be.seriousbusiness.java.mybatis.entity.Person;

public interface PersonMapper {
	@Select("SELECT * FROM person WHERE id = #{id}")
	Person select(final int id);
}
