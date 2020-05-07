package edu.neusoft.demo.service.Impl;

import edu.neusoft.demo.entity.User;
import edu.neusoft.demo.mapper.UserMapper;
import edu.neusoft.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//service标注业务层组件
@Service
public class UserServiceImpl implements UserService {

    //Service接口的实现类
    @Autowired UserMapper userMapper;

    public User getUser(User user){
        User userInfo = userMapper.getUser(user);
        return userInfo;
    }


    //伪代码,表示重写（下面的方法名是否是你父类中所有的）
//    @Override
    //获取数据库表（USER）的数据，以list列表的形式，把查询出来的数据保存在数据对象中
    public int setUser(User userInfo){
        int result = -1;
        userMapper.setUser(userInfo);
        try {
            User status = userMapper.getUser(userInfo);
            if (status.getUserName()!=null) {
                result = 1;
            }
        } catch (IncompatibleClassChangeError error ){
            result = -1;
        }


        return result;
    }



}
