package spring.adog.mapper;

import org.apache.ibatis.annotations.*;
import spring.adog.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{account_id}")
    User findUserByCountId(@Param("account_id") String account_id);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmt_modified},avatar_url = #{avatar_url},bio = #{bio} where id = #{id}")
    void update(User user);
}
