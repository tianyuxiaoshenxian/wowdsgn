package edu.neusoft.demo.mapper;

import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//用于标注数据访问组件，即DAO组件。
@Repository
//添加接口
@Mapper
//数据库访问接口类
public interface UserMapper {

    //注册新User方法
    public int setUser(User user);

    public  User getUser(User userInfo);


}
