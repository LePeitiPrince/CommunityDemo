package spring.adog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.adog.mapper.UserMapper;
import spring.adog.model.User;
import spring.adog.model.UserExample;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andACCOUNT_IDEqualTo(user.getACCOUNT_ID());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0){
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGMT_MODIFIED(System.currentTimeMillis());
            updateUser.setNAME(user.getNAME());
            updateUser.setTOKEN(user.getTOKEN());
            updateUser.setAVATAR_URL(user.getAVATAR_URL());
            updateUser.setBIO(user.getBIO());
            UserExample updateExample = new UserExample();
            updateExample.createCriteria()
                    .andIDEqualTo(dbUser.getID());
            userMapper.updateByExampleSelective(updateUser, updateExample);
        }else {
            //插入
            user.setGMT_CREATE(System.currentTimeMillis());
            user.setGMT_MODIFIED(user.getGMT_CREATE());
            userMapper.insertSelective(user);
        }
    }
}
