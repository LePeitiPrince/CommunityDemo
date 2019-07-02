package spring.adog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import spring.adog.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insertUser(User user);
}
