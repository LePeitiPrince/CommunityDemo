package spring.adog.service;

import spring.adog.mapper.UserMapper;
import spring.adog.model.User;
import spring.adog.model.UserExample;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapUtil {

    public static Map<Long,User> getUserMap(List<Long> userIds, UserMapper userMapper){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIDIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getID(), u -> u));

        return userMap;
    }
}
