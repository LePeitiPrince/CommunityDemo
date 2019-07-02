package spring.adog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.adog.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,bio) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{bio})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);
}
