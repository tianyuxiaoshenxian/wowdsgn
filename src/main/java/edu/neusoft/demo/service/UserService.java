package edu.neusoft.demo.service;

import edu.neusoft.demo.common.util.Result;
import edu.neusoft.demo.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//Service层业务接口类编写
public interface UserService{

    //添加新User
    public int setUser(User userInfo);

    public  User  getUser(User user);

    //伪代码,表示重写（下面的方法名是否是你父类中所有的）
    //获取数据库表（USER）的数据，以list列表的形式，把查询出来的数据保存在数据对象中
//    List<User> getUser();
}
