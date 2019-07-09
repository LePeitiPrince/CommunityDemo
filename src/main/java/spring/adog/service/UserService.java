package spring.adog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.adog.mapper.UserMapper;
import spring.adog.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User userByCountId = userMapper.findUserByCountId(user.getAccount_id());
        if (userByCountId != null){
            //更新
            userByCountId.setGmt_modified(System.currentTimeMillis());
            userByCountId.setName(user.getName());
            userByCountId.setToken(user.getToken());
            userByCountId.setAvatar_url(user.getAvatar_url());
            userByCountId.setBio(user.getBio());
            userMapper.update(userByCountId);
        }else {
            //插入
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insertUser(user);
        }
    }
}
